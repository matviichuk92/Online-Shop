package online.shop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import online.shop.dao.OrderDao;
import online.shop.db.Storage;
import online.shop.lib.Dao;
import online.shop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Storage.addOrder(order);
        return order;
    }

    @Override
    public Optional<Order> getById(Long id) {
        return Storage.orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return Storage.orders.stream()
                .filter(order -> order.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Order update(Order order) {
        IntStream.range(0, Storage.orders.size())
                .filter(i -> Storage.orders.get(i).getId().equals(order.getId()))
                .forEach(i -> Storage.orders.set(i, order));
        return order;
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.orders.removeIf(order -> order.getId().equals(id));
    }
}
