package online.shop.service;

import java.util.List;
import online.shop.model.Order;
import online.shop.model.ShoppingCart;

public interface OrderService extends GenericService<Order, Long> {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getUserOrders(Long userId);
}
