package online.shop.service.impl;

import online.shop.dao.ShoppingCartDao;
import online.shop.lib.Inject;
import online.shop.lib.Service;
import online.shop.model.Product;
import online.shop.model.ShoppingCart;
import online.shop.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getProducts().add(product);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        boolean result = shoppingCart.getProducts().remove(product);
        shoppingCartDao.update(shoppingCart);
        return result;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().clear();
        shoppingCartDao.update(shoppingCart);

    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getByUserId(userId).get();
    }

    @Override
    public boolean deleteById(Long id) {
        return shoppingCartDao.deleteById(id);
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {
        return shoppingCartDao.deleteById(shoppingCart.getId());
    }
}
