package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class JsonWorkingServlet extends HttpServlet {
    private AtomicInteger id = new AtomicInteger(1);
    private final Map<Integer, User> map = new ConcurrentHashMap<>();
    private final String sep = System.getProperty("line.separator");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = req.getParameter("obj");
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(json, User.class);
        map.put(id.getAndIncrement(), user);
        System.out.println("---------------");
        map.forEach((id, u) -> System.out.printf("%d : %s%s", id, u, sep));
    }
}
