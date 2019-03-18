package servlets;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Сервлет для обновления записей
 */
public class UserUpdateServlet extends HttpServlet {
    private final ValidateService validateService = ValidateService.newInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = validateService.findById(new User(id));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/UserUpdateServlet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email, new Date()) { {
            setId(id);
        } };
        boolean update = validateService.update(user);
        PrintWriter pw = new PrintWriter(resp.getWriter());
        if (update) {
            pw.append("Запись изменена.");
        } else {
            pw.append("Ошибка обновления записи.");
        }
        pw.flush();
    }
}