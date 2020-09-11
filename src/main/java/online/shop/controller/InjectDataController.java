package online.shop.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import online.shop.lib.Injector;
import online.shop.model.Product;
import online.shop.model.ShoppingCart;
import online.shop.model.User;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User roma = new User("Roma", "PapaCat", "123");
        User mila = new User("Mila", "MamaCat", "123");
        userService.create(roma);
        userService.create(mila);
        productService.create(new Product("banana", 12.3));
        productService.create(new Product("melon", 12.0));
        shoppingCartService.create(new ShoppingCart(roma.getId()));
        shoppingCartService.create(new ShoppingCart(mila.getId()));
        req.getRequestDispatcher("/WEB-INF/views/inject-data.jsp").forward(req, resp);
    }
}
