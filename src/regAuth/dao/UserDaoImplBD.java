package regAuth.dao;

import regAuth.model.User;
import regAuth.utils.DbManager;

import java.sql.*;
import java.util.LinkedList;

public class UserDaoImplBD implements UserDao {

    private Connection getConnection() {
        return DbManager.getInstance().getConnection();
    }

    @Override
    public void create(User user) {
        if (getUser(user.getLogin()) != null) {
            System.out.println("Ошибка: Пользователь с логином '" + user.getLogin() + "' уже есть в БД!");
            return;
        }
        String query = "INSERT INTO users (login, password) VALUES (?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            System.out.println("Пользователь " + user.getLogin() + " сохранен в БД.");
        } catch (SQLException e) {
            System.out.println("Ошибка сохранения в БД: " + e.getMessage());
        }
    }

    @Override
    public User getUser(String login) {
        String query = "SELECT * FROM users WHERE login = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getString("login"), rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка запроса в БД: " + e.getMessage());
        }
        return null;
    }

    @Override
    public LinkedList<User> getAll() {
        LinkedList<User> allUsers = new LinkedList<>();
        String query = "SELECT * FROM users";
        try (Statement s = getConnection().createStatement(); ResultSet rs = s.executeQuery(query)) {
            while (rs.next()) {
                allUsers.add(new User(rs.getString("login"), rs.getString("password")));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка запроса в БД: " + e.getMessage());
        }
        return allUsers;
    }

    @Override
    public void update(User user) {
        String query = "UPDATE users SET password = ? WHERE login = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getLogin());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Пароль пользователя " + user.getLogin() + " обновлен в БД!");
            } else {
                System.out.println("Ошибка обновления: Пользователь " + user.getLogin() + " не найден в БД.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка обновления БД: " + e.getMessage());
        }
    }

    @Override
    public void delete(String login) {
        String query = "DELETE FROM users WHERE login = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setString(1, login);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Пользователь " + login + " успешно удален.");
            } else {
                System.out.println("Ошибка удаления: Пользователь '" + login + "' не найден в БД.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка удаления из БД: " + e.getMessage());
        }
    }
}
