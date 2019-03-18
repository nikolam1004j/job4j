package servlets;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EchoServlet extends HttpServlet {
    private final List<String> list = new CopyOnWriteArrayList<>();
    private final ValidateService validateService = ValidateService.newInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = validateService.findAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/EchoServlet.jsp").forward(req, resp);
    }
}