package servlets;

import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Сервлет для выдачи списка записей и удаления записей
 */
public class UsersServlet extends HttpServlet {
    private final ValidateService validateService = ValidateService.newInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> all = validateService.findAll();
        StringBuilder table = new StringBuilder("<table border='1'>")
                .append("<tr><th>id</th><th>name</th><th>login</th><th>email</th><th>create_date</th>")
                .append("<th>Edit</th><th>Delete</th></tr>");

        all.forEach(user -> table.append("<tr><td>").append(user.getId()).append("</td>")
                .append("<td>").append(user.getName()).append("</td>")
                .append("<td>").append(user.getLogin()).append("</td>")
                .append("<td>").append(user.getEmail()).append("</td>")
                .append("<td>").append(user.getCreateDate()).append("</td>")
                .append("<td>").append("<form action='").append(req.getContextPath())
                .append("/edit'").append(" method='get'>").append("<input type='hidden' name='id' value='")
                .append(user.getId()).append("'>").append("<input type='submit' value='Edit'></form>")
                .append("</td>")
                .append("<td>").append("<form action='").append(req.getContextPath()).append("/list' method='post'>")
                .append("<input type='hidden' name='id' value='").append(user.getId()).append("' />")
                .append("<input type='submit' value='Delete'>")
                .append("</form>")
                .append("</td>"));
        table.append("</table>");

        PrintWriter pw = new PrintWriter(resp.getWriter());
        pw.append(table);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        boolean delete = validateService.delete(new User(id));
        PrintWriter pw = new PrintWriter(resp.getWriter());
        if(delete) {
            pw.append("Запись удалена");
        } else {
            pw.append("Ошибка удаления записи");
        }
        pw.flush();
    }
}
