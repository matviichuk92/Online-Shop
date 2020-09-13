package online.shop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import online.shop.lib.Injector;
import online.shop.model.Order;
import online.shop.service.OrderService;

public class GetOrderDetailsController extends HttpServlet {
    private static final Long USER_ID = 1L;
    private static final Injector injector = Injector.getInstance("online.shop");
    private final OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Order> order = orderService.getUserOrders(USER_ID);
        req.setAttribute("order",order);
        req.getRequestDispatcher("WEB-INF/views/order/details.jsp").forward(req, resp);
    }
}
