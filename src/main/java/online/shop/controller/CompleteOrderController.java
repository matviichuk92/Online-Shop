package online.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import online.shop.lib.Injector;
import online.shop.model.ShoppingCart;
import online.shop.service.OrderService;
import online.shop.service.ShoppingCartService;

public class CompleteOrderController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("online.shop");
    private final ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);
    private final OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        orderService.completeOrder(shoppingCart);
        resp.sendRedirect(req.getContextPath() + "/shopping-cart");
    }
}
