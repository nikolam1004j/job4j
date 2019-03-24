package servlets;

import logic.DbStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SetTaskStatusDoneServlet extends HttpServlet {
    private final DbStorage storage = new DbStorage();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("taskId"));
        boolean result = storage.setStatusDoneToItemById(id);
        if(result) {
            writer.println("OK");
        } else {
            writer.println("ERROR");
        }

        List allTasks = storage.getAllTasks();
        allTasks.forEach(System.out::println);
    }
}
