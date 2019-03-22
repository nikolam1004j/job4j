package servlets;

import models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserCreateServletTestMock {
    @Test
    public void test() throws IOException, ServletException {
        ValidateService validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.newInstance()).thenReturn(validate);
        UserCreateServlet servlet = new UserCreateServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter pw = new PrintWriter(System.out);
        when(resp.getWriter()).thenReturn(pw);
        when(req.getParameter("name")).thenReturn("Mock");
        when(req.getParameter("login")).thenReturn("MockaMockaMocka");
        when(req.getParameter("email")).thenReturn("Mock@mock.com");
        servlet.doPost(req, resp);
        when(req.getParameter("name")).thenReturn("Nick and Mock");
        when(req.getParameter("login")).thenReturn("NikaMoka");
        when(req.getParameter("email")).thenReturn("NikaMoka@mocka.com");
        servlet.doPost(req, resp);

        List<User> all = validate.findAll();
        assertThat(all.size(), is(2));
        assertThat(all.get(0).getName(), is("Mock"));
        assertThat(all.get(1).getName(), is("Nick and Mock"));
    }
}