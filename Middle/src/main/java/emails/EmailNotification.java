package emails;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final String username = "user";
    private final String password = "pass";
    private Properties properties = new Properties() {{
        put("mail.smtp.host", "smtp.yandex.ru");
        put("mail.smtp.ssl.enable", "true");
        put("mail.smtp.auth", "true");
        put("mail.smtp.port", "465");
        put("mail.imaps.ssl.trust", "*");
        put("mail.smtp.ssl.trust", "*");
    }};

    private Session session = Session.getDefaultInstance(properties, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    public void emailTo(User user) {
        pool.submit(new Thread(() -> {
            try {
                send(String.format("Notification %s to email %s.", user.getUsername(), user.getEmail()),
                        String.format("Add a new event to %s", user.getUsername()),
                        user.getEmail());
            } catch (MessagingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }));
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void send(String subject, String body, String email) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("nickraz@yandex.ru"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);

        Transport.send(message);
    }
}