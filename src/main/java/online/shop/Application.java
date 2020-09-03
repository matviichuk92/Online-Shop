package online.shop;

import online.shop.model.Product;
import online.shop.service.ProductService;
import online.shop.service.lib.Injector;

public class Application {
    public static Injector injector = Injector.getInstance("online.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product whiskas = productService.create(new Product("Whiskas", 12.50));
        Product gourmet = productService.create(new Product("Gourmet", 23.50));
        Product friskies = productService.create(new Product("Friskies", 9.50));
        Product vitamin = productService.create(new Product("Cheeze wheels", 7.0));
        System.out.println("All product: " + "\n" + productService.getAllProduct());
        productService.update(vitamin).setName("Cheese wheels");
        System.out.println("After update: " + "\n" + productService.getAllProduct());
        System.out.println("Get by Id: " + "\n" + productService.getById(2L));
        System.out.println("Delete by id: " + "\n" + productService.deleteById(2L));
        System.out.println("After delete: " + "\n" + productService.getAllProduct());
    }
}
