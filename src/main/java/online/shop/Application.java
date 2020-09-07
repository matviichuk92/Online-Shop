package online.shop;

import online.shop.lib.Injector;
import online.shop.model.Product;
import online.shop.model.ShoppingCart;
import online.shop.model.User;
import online.shop.service.OrderService;
import online.shop.service.ProductService;
import online.shop.service.ShoppingCartService;
import online.shop.service.UserService;

public class Application {
    private static Injector injector = Injector.getInstance("online.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        Product whiskas = productService.create(new Product("Whiskas", 12.50));
        Product gourmet = productService.create(new Product("Gourmet", 23.50));

        UserService userService = (UserService) injector.getInstance(UserService.class);
        User roman = new User("Roman", "PapaCats", "cat123");
        User mila = new User("Mila", "MamaCats", "123");
        userService.create(roman);
        userService.create(mila);
        System.out.println("All users : " + userService.getAll());
        mila.setPassword("Mila4TheBest");
        userService.update(mila);
        System.out.println("All users after update : " + userService.getAll());

        ShoppingCartService shoppingCartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        ShoppingCart cart = new ShoppingCart(roman.getId());
        shoppingCartService.create(cart);
        shoppingCartService.addProduct(cart, whiskas);
        shoppingCartService.addProduct(cart, gourmet);
        System.out.println("Shopping cart after add : "
                + shoppingCartService.getByUserId(roman.getId()));
        shoppingCartService.deleteProduct(cart, whiskas);
        System.out.println("Shopping cart after delete : "
                + shoppingCartService.getByUserId(roman.getId()));

        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        orderService.completeOrder(cart);
        System.out.println("Roman's order : " + orderService.getUserOrders(roman.getId()));
        orderService.delete(1L);
        System.out.println("Roman's order after delete : "
                + orderService.getUserOrders(roman.getId()));
    }
}
