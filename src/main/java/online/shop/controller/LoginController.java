package online.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import online.shop.exception.AuthenticationException;
import online.shop.lib.Injector;
import online.shop.model.User;
import online.shop.security.AuthenticationService;

public class LoginController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("online.shop");
    private final AuthenticationService authenticationService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/users/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pwd");
        try {
            User user = authenticationService.login(login, password);
            HttpSession session = req.getSession();
            session.setAttribute("userId", user.getId());
        } catch (AuthenticationException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/users/login.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
