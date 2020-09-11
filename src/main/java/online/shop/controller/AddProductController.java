package online.shop.controller;

import online.shop.lib.Injector;
import online.shop.model.Product;
import online.shop.service.ProductService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProductController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("online.shop");
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/product/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        Product product = new Product(name, Double.parseDouble(price));
        productService.create(product);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
