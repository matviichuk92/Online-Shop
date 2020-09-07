package online.shop.dao;

import java.util.Optional;
import online.shop.model.ShoppingCart;

public interface ShoppingCartDao {
    ShoppingCart create(ShoppingCart shoppingCart);

    Optional<ShoppingCart> getByUserById(Long userId);

    ShoppingCart update(ShoppingCart shoppingCart);

    boolean deleteById(ShoppingCart shoppingCart);

}
