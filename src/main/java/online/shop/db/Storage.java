package online.shop.db;

import java.util.ArrayList;
import java.util.List;
import online.shop.model.Product;

public class Storage {
    public static Long productId = 0L;
    public static final List<Product> products = new ArrayList<>();

    public static void addProduct(Product product) {
        productId++;
        product.setId(productId);
        products.add(product);
    }
}
