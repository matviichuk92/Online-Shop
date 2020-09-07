package online.shop.dao;

import java.util.List;
import java.util.Optional;
import online.shop.model.Order;

public interface OrderDao {
    Order create(Order order);

    Optional<Order> getById(Long id);

    List<Order> getAll();

    List<Order> getUserOrders(Long userId);

    Order update(Order order);

    boolean deleteById(Long id);

}
