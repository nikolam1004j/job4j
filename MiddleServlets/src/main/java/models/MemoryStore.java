package models;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class MemoryStore implements Store {
    private static final MemoryStore MEMORY_STORE = new MemoryStore();
    @GuardedBy("this")
    private Connection connection;

    private MemoryStore() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/servlets",
                    "postgres",
                    "pass");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static MemoryStore getInstance() {
        return MEMORY_STORE;
    }

    private synchronized boolean insertOrUpdate(User user, boolean result, String query) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setTimestamp(4,
                    Timestamp.valueOf(simpleDateFormat.format(user.getCreateDate().getTime())));
            result = preparedStatement.executeUpdate() != 0;
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean add(User user) {
        boolean result = false;
        result = insertOrUpdate(user, result,
                "insert into crudsrvlet.users(name,login,email,create_date) values (?,?,?,?)"
        );
        return result;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        StringBuilder query = new StringBuilder("update crudsrvlet.users set ")
                .append("name = ?, ")
                .append("login = ?, ")
                .append("email = ?, ")
                .append("create_date = ? ")
                .append("where id = ")
                .append(user.getId());
        result = insertOrUpdate(user, result, query.toString());
        return result;
    }

    @Override
    public synchronized boolean delete(User user) {
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from crudsrvlet.users where id = ?"
            );
            preparedStatement.setInt(1, user.getId());
            result = preparedStatement.executeUpdate() != 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public synchronized List<User> findAll() {
        List<User> resultList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from crudsrvlet.users order by id"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5));
                user.setId(resultSet.getInt(1));
                resultList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultList;
    }

    @Override
    public synchronized User findById(User user) {
        User result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from crudsrvlet.users where id = ?"
            );
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = new User(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDate(5));
                result.setId(user.getId());
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}