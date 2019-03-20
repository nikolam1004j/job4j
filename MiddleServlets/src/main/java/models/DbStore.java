package models;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DbStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore DB_STORE = new DbStore();
    private List<Role> roles = new CopyOnWriteArrayList<>();

    private DbStore() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/servlets");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("pass");
        SOURCE.setDefaultSchema("crudsrvlet");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DbStore getInstance() {
        return DB_STORE;
    }

    private boolean insertOrUpdate(User user, String query) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection()) {
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
        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into users(name,login,email,create_date) values (?,?,?,?) returning id"
            );
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setTimestamp(4,
                    Timestamp.valueOf(simpleDateFormat.format(user.getCreateDate().getTime())));
            ResultSet returningId = preparedStatement.executeQuery();
            result = returningId.next();
            if(result) {
                int addedUserId = returningId.getInt(1);
                PreparedStatement statement = connection.prepareStatement(
                        "insert into user_roles(user_id, role_id) values(?, ?)"
                );
                statement.setInt(1, addedUserId);
                statement.setInt(2, 3);
                result = statement.executeUpdate() != 0;
                connection.commit();
                statement.close();
                preparedStatement.close();
            } else {
                connection.rollback();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        StringBuilder query = new StringBuilder("update users set ")
                .append("name = ?, ")
                .append("login = ?, ")
                .append("email = ?, ")
                .append("create_date = ? ")
                .append("where id = ")
                .append(user.getId());
        result = insertOrUpdate(user, query.toString());
        return result;
    }

    @Override
    public boolean delete(User user) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection()) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "delete from user_roles where user_id = ?;"
                );
                preparedStatement.setInt(1, user.getId());
                result = preparedStatement.executeUpdate() != 0;
                PreparedStatement statement = connection.prepareStatement("delete from users where id = ?");
                statement.setInt(1, user.getId());
                result = result && statement.executeUpdate() != 0;
                if(result) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                connection.rollback();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> resultList = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection()) {
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
        try (Connection connection = SOURCE.getConnection()) {
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

    public String getInfoIfLoginAndPassCorrect(String login, String pass) {
        StringBuilder result = new StringBuilder();
        try (Connection con = SOURCE.getConnection()) {
            StringBuilder query = new StringBuilder("select u.id, u.name, u.login, u.email, r.name from ")
                    .append("users u, roles r, ")
                    .append("user_roles ur, passwords p ")
                    .append("where u.id = ur.user_id ")
                    .append("and ur.role_id = r.id ")
                    .append("and p.user_id = u.id ")
                    .append("and u.login = ? ")
                    .append("and p.pass = ? ")
                    .append("and p.expired > date_trunc('day', now())");
            PreparedStatement statement = con.prepareStatement(query.toString());
            statement.setString(1, login);
            statement.setString(2, pass);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    result.append(rs.getString(i + 1)).append(";");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.length() == 0 ? null : result.toString();
    }

    public boolean updateRole(User user, int roleId) {
        boolean result = false;
        try (Connection con = SOURCE.getConnection()) {
            PreparedStatement statement = con.prepareStatement(
                    "update user_roles set role_id = ? where user_id = ?"
            );
            statement.setInt(1, roleId);
            statement.setInt(2, user.getId());
            int update = statement.executeUpdate();
            result = update != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private boolean addNewUserRole(User user) {
        boolean result = false;
        try (Connection con = SOURCE.getConnection()) {
            PreparedStatement statement = con.prepareStatement(
                    "insert into user_roles(id_user, role_id) values(?, ?)"
            );
            statement.setInt(1, user.getId());
            statement.setInt(2, 3);
            result = statement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Role> getRoles() {
        if(roles.size() == 0) {
            try (Connection con = SOURCE.getConnection()) {
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery("select id, name from crudsrvlet.roles");
                while (rs.next()) {
                    roles.add(new Role(rs.getInt(1), rs.getString(2)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return roles;
    }
}