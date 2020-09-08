package online.shop.dao;

import java.util.List;
import online.shop.model.Order;

public interface OrderDao extends GenericDao<Order, Long> {

    List<Order> getUserOrders(Long userId);

}
