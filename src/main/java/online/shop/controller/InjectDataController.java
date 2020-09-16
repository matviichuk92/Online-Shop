package online.shop.controller;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import online.shop.lib.Injector;
import online.shop.model.Product;
import online.shop.model.Role;
import online.shop.model.ShoppingCart;
import online.shop.model.User;
import online.shop.service.OrderService;
import online.shop.service.ProductService;
import online.shop.service.ShoppingCartService;
import online.shop.service.UserService;

public class InjectDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("online.shop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);
    private final OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User admin = new User("Admin", "Admin", "123");
        User roma = new User("Roma", "PapaCat", "123");
        User mila = new User("Mila", "MamaCat", "123");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(admin);
        roma.setRoles(Set.of(Role.of("USER")));
        userService.create(roma);
        mila.setRoles(Set.of(Role.of("USER")));
        userService.create(mila);
        Product banana = new Product("banana", 12.3);
        Product melon = new Product("melon", 12.0);
        productService.create(banana);
        productService.create(melon);
        ShoppingCart romaCart = new ShoppingCart(roma.getId());
        ShoppingCart milaCart = new ShoppingCart(mila.getId());
        shoppingCartService.create(romaCart);
        shoppingCartService.create(milaCart);
        shoppingCartService.addProduct(romaCart, banana);
        shoppingCartService.addProduct(milaCart, melon);
        orderService.completeOrder(romaCart);
        orderService.completeOrder(milaCart);
        req.getRequestDispatcher("/WEB-INF/views/inject-data.jsp").forward(req, resp);
    }
}
