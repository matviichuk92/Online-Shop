package online.shop;

import online.shop.lib.Injector;
import online.shop.model.Product;
import online.shop.service.ProductService;

public class Application {
    private static Injector injector = Injector.getInstance("online.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product whiskas = productService.create(new Product("Whiskas", 12.50));
        Product gourmet = productService.create(new Product("Gourmet", 23.50));
        System.out.println("Get by Id: " + "\n" + productService.getById(gourmet.getId()));
        productService.deleteById(gourmet.getId());
        whiskas.setPrice(10.00);
        productService.update(whiskas);
        System.out.println("After update and delete: " + "\n" + productService.getAllProducts());
    }
}
