package online.shop.dao.impl;

import java.util.List;
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
    public Optional<ShoppingCart> getById(Long id) {
        return Storage.shoppingCarts.stream()
                .filter(cart -> cart.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        return Storage.shoppingCarts.stream()
                .filter(shoppingCart -> shoppingCart.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.shoppingCarts.size())
                .filter(i -> Storage.shoppingCarts.get(i).getId().equals(shoppingCart.getId()))
                .forEach(i -> Storage.shoppingCarts.set(i, shoppingCart));
        return shoppingCart;
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.shoppingCarts.removeIf(cart -> cart.getId().equals(id));
    }
}
