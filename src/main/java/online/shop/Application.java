package online.shop;

import online.shop.lib.Injector;
import online.shop.service.OrderService;
import online.shop.service.ProductService;
import online.shop.service.ShoppingCartService;
import online.shop.service.UserService;

public class Application {
    private static Injector injector = Injector.getInstance("online.shop");
    private static ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    private static UserService userService = (UserService) injector.getInstance(UserService.class);
    private static ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);
    private static OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    public static void main(String[] args) {
    }
}
