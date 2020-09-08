package online.shop.dao;

import java.util.Optional;
import online.shop.model.ShoppingCart;

public interface ShoppingCartDao extends GenericDao<ShoppingCart, Long> {
    Optional<ShoppingCart> getByUserId(Long userId);
}
