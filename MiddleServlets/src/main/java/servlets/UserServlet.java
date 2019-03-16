package servlets;

import models.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class UserServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.newInstance();

    private final Map<String, Function<User, Boolean>> map =
            new ConcurrentHashMap<String, Function<User, Boolean>>() {{
                put("add", logic::add);
                put("update", logic::update);
                put("delete", logic::delete);
            }};

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter pw = new PrintWriter(resp.getWriter());
        logic.findAll().forEach(pw::println);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter pw = new PrintWriter(resp.getWriter());
        resp.setContentType("text/html; charset=UTF-8");
        String name = req.getParameter("name");
        String action = req.getParameter("action");
        int id = action.equals("add") ? -1 : Integer.parseInt(req.getParameter("id"));

        //Dispatcher
        boolean result = map.getOrDefault(action, user ->
                {
                    pw.printf("Операция %s не поддерживается", action);
                    return false;
                }
        ).apply(new User(name, "", "", new Date()) {{
            setId(id);
        }});

        if (result) {
            pw.printf("Операция %s прошла успешно", action);
        } else {
            pw.printf("Операция %s не удалась", action);
        }
    }
}