package models;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DbStore implements Store {
    private DbStore(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/servlets");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("pass");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore DB_STORE = new DbStore();

    public static DbStore getInstance() {
        return DB_STORE;
    }

    private boolean insertOrUpdate(User user, boolean result, String query) {
        try (Connection connection = SOURCE.getConnection()){
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
    public boolean delete(User user) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection()){
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
    public List<User> findAll() {
        List<User> resultList = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection()){
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
    public User findById(User user) {
        User result = null;
        try (Connection connection = SOURCE.getConnection()){
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
