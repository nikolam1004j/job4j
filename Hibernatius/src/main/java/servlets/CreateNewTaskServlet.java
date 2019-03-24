package servlets;

import logic.DbStorage;
import models.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class CreateNewTaskServlet extends HttpServlet {
    private final DbStorage storage = new DbStorage();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        String task = req.getParameter("task");
        Item item = new Item(task, new Date(), false);
        boolean result = storage.addNewTask(item);
        if(result) {
            writer.println("OK");
        } else {
            writer.println("ERROR");
        }
        writer.flush();

        List allTasks = storage.getAllTasks();
        allTasks.forEach(System.out::println);
    }
}
