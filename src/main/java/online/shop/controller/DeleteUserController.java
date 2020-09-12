package online.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import online.shop.lib.Injector;
import online.shop.service.ShoppingCartService;
import online.shop.service.UserService;

public class DeleteUserController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("online.shop");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String userId = req.getParameter("id");
        Long id = Long.valueOf(userId);
        userService.delete(id);
        shoppingCartService.delete(shoppingCartService.getByUserId(id));
        resp.sendRedirect(req.getContextPath() + "/users/all");
    }
}
