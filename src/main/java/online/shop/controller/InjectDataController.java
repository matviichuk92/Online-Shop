package online.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import online.shop.lib.Injector;

public class InjectDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("online.shop");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }
}
