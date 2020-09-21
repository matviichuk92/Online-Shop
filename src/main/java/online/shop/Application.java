package online.shop;

import online.shop.dao.ProductDao;
import online.shop.lib.Injector;
import online.shop.model.Product;

public class Application {
    private static final Injector injector = Injector.getInstance("online.shop");

    public static void main(String[] args) {
        ProductDao productDao = (ProductDao) injector.getInstance(ProductDao.class);
        Product product = new Product("melon", 35);
        product.setId(1L);
        productDao.create(product);
        System.out.println(productDao.getAll());
        product.setPrice(23);
        System.out.println(productDao.update(product));
        System.out.println(productDao.getById(1L));
        System.out.println(productDao.deleteById(15L));
    }
}
