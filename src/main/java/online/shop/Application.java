package online.shop;

import online.shop.dao.jdbc.ProductDaoJdbsImpl;
import online.shop.model.Product;

public class Application {
    public static void main(String[] args) {
        ProductDaoJdbsImpl productDaoJdbs = new ProductDaoJdbsImpl();
        Product product = new Product("melon", 35);
        product.setId(1L);
        productDaoJdbs.create(product);
        System.out.println(productDaoJdbs.getAll());
        product.setPrice(23);
        System.out.println(productDaoJdbs.update(product));
        System.out.println(productDaoJdbs.getById(1L));
        System.out.println(productDaoJdbs.deleteById(15L));
    }
}
