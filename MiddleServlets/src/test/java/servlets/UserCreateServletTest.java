package servlets;

import models.User;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserCreateServletTest {

    @Test
    public void addUser() throws ServletException, IOException {
        final ValidateService validate = ValidateService.newInstance();
        List<User> usersBefore = validate.findAll();
        UserCreateServlet servlet = new UserCreateServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));
        when(request.getParameter("name")).thenReturn("Mockito1515");
        when(request.getParameter("login")).thenReturn("mock");
        when(request.getParameter("email")).thenReturn("Mockito@mock.ru");
        servlet.doPost(request, response);
        List<User> usersAfter = validate.findAll();
        User user = usersAfter.stream()
                .filter(u -> u.getLogin().equals(request.getParameter("login")))
                .findFirst()
                .orElse(null);
        assertThat(user.getLogin(), is(request.getParameter("login")));
        assertThat(usersBefore.size(), is(usersAfter.size() - 1));
    }
}