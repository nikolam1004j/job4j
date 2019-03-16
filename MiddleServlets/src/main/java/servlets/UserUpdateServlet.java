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
        resp.setContentType("text/html; charset=UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        User user = validateService.findById(new User(id));
        PrintWriter pw = new PrintWriter(resp.getWriter());
        pw.append("    <form action='"+req.getContextPath()+"/edit' method=\"post\">\n" +
                "        id:    " + user.getId() + "<br>" +
                "        <input type=\"hidden\" name=\"id\" value='"+user.getId()+"'>\n" +
                "        <label for=\"name\">Имя:</label>\n" +
                "        <input id=\"name\" type=\"text\" value='"+user.getName()+"' name=\"name\"><br>\n" +
                "        <label for=\"login\">Логин:</label>\n" +
                "        <input id=\"login\" type=\"text\" value='"+user.getLogin()+"' name=\"login\"><br>\n" +
                "        <label for=\"email\">Email:</label>\n" +
                "        <input id=\"email\" type=\"text\" value='"+user.getEmail()+"' name=\"email\"><br>\n" +
                "        <input type=\"submit\" value=\"Send\">\n" +
                "    </form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        User user = new User(name, login, email, new Date()) {{
            setId(id);
        }};
        boolean update = validateService.update(user);
        PrintWriter pw = new PrintWriter(resp.getWriter());
        if(update) {
            pw.append("Запись изменена.");
        } else {
            pw.append("Ошибка обновления записи.");
        }
        pw.flush();
    }
}
