package servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class SignInServletTest {

    @Test
    public void whenDoGet() throws ServletException, IOException {
        SignInServlet servlet = new SignInServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/WEB-INF/views/SignInServlet.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(dispatcher, only()).forward(request, response);
    }

    @Test
    public void whenDoPost() throws ServletException, IOException {
        SignInServlet servlet = new SignInServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/WEB-INF/views/SignInServlet.jsp")).thenReturn(dispatcher);
        servlet.doPost(request, response);
        verify(dispatcher, atLeastOnce()).forward(request, response);
    }
}