package servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class UserUpdateServletTest {

    @Test
    public void whenUpdateUser() throws ServletException, IOException {
        UserUpdateServlet servlet = new UserUpdateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("admin");
        when(request.getParameter("id")).thenReturn("2");
        when(request.getRequestDispatcher("/WEB-INF/views/UserUpdateServlet.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(dispatcher, only()).forward(request, response);
    }
}