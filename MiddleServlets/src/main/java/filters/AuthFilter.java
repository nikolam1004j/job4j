package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (!req.getRequestURI().contains("signin")) {
            if (session == null || session.getAttribute("login") == null) {
                resp.sendRedirect(req.getContextPath() + "/signin");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            if (session == null || session.getAttribute("login") == null) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(req.getContextPath());
            }
        }
    }

    @Override
    public void destroy() {

    }
}