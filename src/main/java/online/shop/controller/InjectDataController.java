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
import online.shop.model.User;
import online.shop.service.ProductService;
import online.shop.service.UserService;

public class InjectDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("online.shop");
    private final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User admin = new User("Boss","boss","123");
        userService.create(admin);
        admin.setRoles(Set.of(Role.of("ADMIN")));
        Product apple = new Product("apple",12.45);
        productService.create(apple);
        Product cherry = new Product("cherry", 60.00);
        productService.create(cherry);
        Product melon = new Product("melon", 16.75);
        productService.create(melon);
        req.getRequestDispatcher("/WEB-INF/views/inject-data.jsp").forward(req, resp);
    }
}
