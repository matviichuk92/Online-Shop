package online.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import online.shop.lib.Injector;
import online.shop.model.Product;
import online.shop.model.ShoppingCart;
import online.shop.service.ProductService;
import online.shop.service.ShoppingCartService;

public class AddProductToShoppingCartController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("online.shop");
    private final ShoppingCartService shoppingCartService = (ShoppingCartService) injector
            .getInstance(ShoppingCartService.class);
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("userId");
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        String productId = req.getParameter("id");
        Long id = Long.valueOf(productId);
        Product product = productService.get(id);
        shoppingCartService.addProduct(shoppingCart, product);
        resp.sendRedirect(req.getContextPath() + "/product/all");
    }
}
