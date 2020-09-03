package online.shop.service;

import java.util.List;
import java.util.Optional;
import online.shop.model.Product;
import online.shop.service.lib.Service;

@Service
public interface ProductService {
    Product create(Product product);

    Optional<Product> getById(Long productId);

    Product update(Product product);

    boolean deleteById(Long productId);

    List<Product> getAllProduct();
}
