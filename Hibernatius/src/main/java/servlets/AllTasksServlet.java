package servlets;

import com.google.gson.Gson;
import logic.DbStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllTasksServlet extends HttpServlet {
    private final DbStorage storage = new DbStorage();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        boolean all = Boolean.parseBoolean(req.getParameter("all"));
        List allTasks = all ? storage.getAllTasks() : storage.getNotDone();
        Gson gson = new Gson();
        String toJson = gson.toJson(allTasks);
        allTasks.forEach(System.out::println);
        System.out.println(toJson);
        resp.getWriter().println(toJson);
    }
}
