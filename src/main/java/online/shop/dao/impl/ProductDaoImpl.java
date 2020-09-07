package online.shop.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import online.shop.dao.ProductDao;
import online.shop.db.Storage;
import online.shop.lib.Dao;
import online.shop.model.Product;

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
        IntStream.range(0, Storage.products.size())
                .filter(i -> Storage.products.get(i).getId().equals(product.getId()))
                .forEach(i -> Storage.products.set(i, product));
        return product;
    }

    @Override
    public boolean deleteById(Long productId) {
        return Storage.products.removeIf(product -> product.getId().equals(productId));
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }
}
