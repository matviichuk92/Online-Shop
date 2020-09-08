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
    public Product get(Long id) {
        return productDao.getById(id).get();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean delete(Long id) {
        return productDao.deleteById(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }
}
