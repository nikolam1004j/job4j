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
 * Сервлет для создания новых записей.
 */
public class UserCreateServlet extends HttpServlet {
    private final ValidateService validateService = ValidateService.newInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        PrintWriter pw = new PrintWriter(resp.getWriter());
        pw.print("    <form action='"+req.getContextPath()+"/create' method=\"post\">\n" +
                "        Добавление новой записи:<br>\n" +
                "        <label for=\"name\">Имя:</label>\n" +
                "        <input id=\"name\" type=\"text\" name=\"name\"><br>\n" +
                "        <label for=\"login\">Логин:</label>\n" +
                "        <input id=\"login\" type=\"text\" name=\"login\"><br>\n" +
                "        <label for=\"email\">Email:</label>\n" +
                "        <input id=\"email\" type=\"text\" name=\"email\"><br>\n" +
                "        <input type=\"submit\" value=\"Send\">\n" +
                "    </form>");
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        PrintWriter pw = new PrintWriter(resp.getWriter());
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email, new Date());
        boolean add = validateService.add(user);
        if(add) {
            pw.append("Запись успешно добавлена.");
        } else {
            pw.append("Ошибка добавления записи.");
        }
        pw.flush();
    }
}
