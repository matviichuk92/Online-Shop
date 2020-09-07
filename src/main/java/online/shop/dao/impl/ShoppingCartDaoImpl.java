package online.shop.dao.impl;

import java.util.Optional;
import java.util.stream.IntStream;
import online.shop.dao.ShoppingCartDao;
import online.shop.db.Storage;
import online.shop.lib.Dao;
import online.shop.model.ShoppingCart;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Storage.addShoppingCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> getByUser(Long userId) {
        return Storage.shoppingCarts.stream()
                .filter(shoppingCart -> shoppingCart.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.shoppingCarts.size())
                .filter(i -> Storage.shoppingCarts.get(i).getId().equals(shoppingCart.getId()))
                .forEach(i -> Storage.shoppingCarts.set(i, shoppingCart));
        return shoppingCart;
    }

    @Override
    public boolean deleteById(ShoppingCart shoppingCart) {
        return Storage.shoppingCarts.removeIf(cart -> cart.getId().equals(shoppingCart.getId()));
    }
}
