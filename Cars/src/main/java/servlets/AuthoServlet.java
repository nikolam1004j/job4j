package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        PrintWriter writer = resp.getWriter();
        if (login != null && !login.isEmpty()) {
            req.getSession().setAttribute("login", login);
            writer.println("OK");
        } else {
            writer.println("ERROR");
        }
        writer.flush();
    }
}