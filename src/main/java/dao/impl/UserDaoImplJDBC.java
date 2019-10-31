package dao.impl;

import dao.UserDao;
import model.User;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImplJDBC implements UserDao {

    private Connection connection;

    public UserDaoImplJDBC(DBHelper helper) {
        this.connection = helper.getConnection();
    }

    public User getById(int id) {
        User user = null;

        try (PreparedStatement statement = connection.prepareStatement("select * from user where id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();

            user = new User(
                    resultSet.getString("role"),
                    resultSet.getString("name"),
                    resultSet.getString("password"),
                    resultSet.getString("login")
            );
            user.setId(resultSet.getInt("id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> getAll() {
        List<User> list = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("select * from user")) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("role"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getString("login")
                );
                user.setId(resultSet.getInt("id"));

                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean remove(int id) {
        int rowsUpdated = 0;

        try (PreparedStatement statement = connection.prepareStatement("delete from user where id = ?")) {
            statement.setInt(1, id);
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsUpdated == 1;
    }

    public boolean add(User user) {
        int rowsAdded = 0;

        try (PreparedStatement statement = connection.prepareStatement("insert into user (login, name, password) values (?, ?, ?)")) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPassword());
            rowsAdded = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsAdded == 1;
    }

    public boolean update(User user) {
        int rowsUpdated = 0;

        try (PreparedStatement statement = connection.prepareStatement("update user set name = ?, password = ? where id = ?")) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getId());
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsUpdated == 1;
    }

    @Override
    public boolean hasId(int id) {
        boolean found = false;

        try (PreparedStatement statement = connection.prepareStatement("select id from user where id = ?")) {
            statement.setInt(1, id);
            found = statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return found;
    }

    @Override
    public User getUser(String login, String password) {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement("select * from user where login = ? and password = ?")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            user = new User(
                    resultSet.getString("role"),
                    resultSet.getString("name"),
                    resultSet.getString("password"),
                    resultSet.getString("login")
            );
            user.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}