package servlets;

import models.User;
import org.junit.Assert;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UsersDeleteServletTest {

    @Test
    public void whenDeleteUser() throws ServletException, IOException {
        final ValidateService validate = ValidateService.newInstance();
        int id = 9;
        List<User> usersBefore = validate.findAll();
        UsersDeleteServlet servlet = new UsersDeleteServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        servlet.doPost(request, response);
        List<User> usersAfter = validate.findAll();
        User userBefore = usersBefore.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
        User userAfter = usersAfter.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
        Assert.assertNotNull(userBefore);
        Assert.assertNull(userAfter);
        assertThat(usersBefore.size(), is(usersAfter.size() + 1));
    }
}