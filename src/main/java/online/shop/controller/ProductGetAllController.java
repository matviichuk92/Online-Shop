package online.shop.controller;

import online.shop.lib.Injector;
import online.shop.model.Product;
import online.shop.service.ProductService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductGetAllController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("online.shop");
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> allProducts = productService.getAll();
        req.setAttribute("products", allProducts);
        req.getRequestDispatcher("/WEB-INF/views/product/get-all.jsp").forward(req, resp);
    }
}
