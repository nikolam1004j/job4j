package storages;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import models.User;

import java.sql.*;

@Getter
@Setter
public class JdbcStorage implements Storage {
    @Getter(AccessLevel.PRIVATE)
    private Connection connection;

    private String url;
    private String driver;
    private String user;
    private String pass;

    /**
     * init метод. Выполнится после конструктора для инициализации подключения.
     */
    public void init() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            System.out.printf("Соединение %s%n", connection.isClosed() ? "закрыто" : "открыто");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * destroy метод. Выполнится при закрытии контекста во время уничтожения объекта.
     * @throws SQLException Выбрасывает при возникновении ошибки во время закрытия подключения.
     */
    public void destroy() throws SQLException {
        if(connection!=null) {
            if(!connection.isClosed()) {
                connection.close();
            }
            System.out.printf("Соединение %s%n", connection.isClosed() ? "закрыто" : "открыто");
        }
    }

    /**
     * Добавляет пользователя в БД.
     * @param user Добавляемый пользователь.
     */
    @Override
    public void add(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement("insert into users(name, created) values (?, ?)");
            stmt.setString(1, user.getName());
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
