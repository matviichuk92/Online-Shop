package online.shop;

import online.shop.lib.Injector;
import online.shop.model.ShoppingCart;
import online.shop.model.User;
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
        User roman = new User("roma","roma","123");
        User mila = new User("mila","mila","123");
        userService.create(roman);
        userService.create(mila);
        ShoppingCart shoppingCart = new ShoppingCart(roman.getId());
        ShoppingCart shoppingCart1 = new ShoppingCart(mila.getId());
        shoppingCartService.create(shoppingCart1);
        shoppingCartService.create(shoppingCart);

        System.out.println(shoppingCartService.getByUserId(mila.getId()));
    }
}
