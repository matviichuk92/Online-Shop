package online.shop.service;

import java.util.List;
import java.util.Optional;
import online.shop.dao.ProductDao;
import online.shop.model.Product;
import online.shop.service.lib.Inject;
import online.shop.service.lib.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Optional<Product> getById(Long productId) {
        return productDao.getById(productId);
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
        return productDao.getAllProduct();
    }
}
