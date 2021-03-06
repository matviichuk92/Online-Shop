package online.shop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import online.shop.lib.Injector;
import online.shop.model.Product;
import online.shop.service.ShoppingCartService;

public class DisplayShoppingCartController extends HttpServlet {
    private static final String USER_ID = "userId";
    private static final Injector injector = Injector.getInstance("online.shop");
    private final ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        List<Product> products = shoppingCartService.getByUserId(userId).getProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/shopping-cart/shopping-cart-user.jsp")
                .forward(req, resp);
    }
}
