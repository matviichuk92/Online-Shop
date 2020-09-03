package online.shop.dao.impl;

import java.util.List;
import java.util.Optional;
import online.shop.dao.ProductDao;
import online.shop.db.Storage;
import online.shop.model.Product;
import online.shop.service.lib.Dao;

@Dao
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        Storage.addProduct(product);
        return product;
    }

    @Override
    public Optional<Product> getById(Long productId) {
        return Storage.products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
    }

    @Override
    public Product update(Product product) {
        List<Product> products = getAllProduct();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())) {
                products.set(i, product);
            }
        }
        return product;
    }

    @Override
    public boolean deleteById(Long productId) {
        return Storage.products.removeIf(product -> product.getId().equals(productId));
    }

    @Override
    public List<Product> getAllProduct() {
        return Storage.products;
    }
}
