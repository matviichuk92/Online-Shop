package online.shop.service.impl;

import java.util.List;
import online.shop.dao.ProductDao;
import online.shop.lib.Inject;
import online.shop.lib.Service;
import online.shop.model.Product;
import online.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product getById(Long productId) {
        return productDao.getById(productId).get();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean deleteById(Long productId) {
        return productDao.deleteById(productId);
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.getAll();
    }
}
