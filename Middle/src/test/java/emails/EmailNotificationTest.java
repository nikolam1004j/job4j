package emails;

import org.junit.Test;

public class EmailNotificationTest {

    @Test
    public void test() throws InterruptedException {
        EmailNotification emailNotification = new EmailNotification();
        User user = new User("Nick", "nickraz@yandex.ru");
        for (int i = 0; i < 40; i++) {
            emailNotification.emailTo(user);
            Thread.sleep(1000);
        }
        emailNotification.close();
    }
}