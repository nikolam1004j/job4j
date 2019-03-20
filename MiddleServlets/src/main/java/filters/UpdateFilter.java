package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (req.getMethod().equals("GET") || req.getMethod().equals("POST")) {
            if (session.getAttribute("role").equals("admin")
                    || session.getAttribute("id").equals(req.getParameter("id"))) {
                chain.doFilter(request, response);
            } else {
                req.setAttribute("error", "Нет прав для данной операции");
                req.getRequestDispatcher("/WEB-INF/views/UserUpdateServlet.jsp").forward(req, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
}