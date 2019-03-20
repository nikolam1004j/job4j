package servlets;

import models.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final DbStore store = DbStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/SignInServlet.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String correct = store.getInfoIfLoginAndPassCorrect(login, pass);
        if(correct == null) {
            req.setAttribute("error", "Ошибка авторизации");
            req.getRequestDispatcher("/WEB-INF/views/SignInServlet.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            String[] userParts = correct.split(";");
            synchronized (session) {
                session.setAttribute("id", userParts[0]);
                session.setAttribute("user", userParts[1]);
                session.setAttribute("login", userParts[2]);
                session.setAttribute("email", userParts[3]);
                session.setAttribute("role", userParts[4]);
            }
            resp.sendRedirect(req.getContextPath());
        }
    }
}
