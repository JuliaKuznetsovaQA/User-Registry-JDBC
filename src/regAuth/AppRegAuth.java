package regAuth;

import regAuth.model.User;

import java.util.LinkedList;

public class AppRegAuth {

    private static LinkedList<User> users;

    public static LinkedList<User> getUsers() {
        return users;
    }

    public AppRegAuth() {
        users = new LinkedList<>();
    }

    public void register(String login, String password) {
        boolean existed = false;
        for(User user: users) {
            if(user.getLogin().equals(login)) {
                existed = true;
                System.out.println("Пользователь с таким логином уже существует!");
                break;
            }
        }
        if(!existed) {
            users.add(new User(login, password));
            System.out.println("Пользователь " + login + " успешно зарегистрирован.");
        }
    }

    public boolean auth(String login, String password) {
        for (User user: users) {
            if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
                System.out.println("Успешная авторизация");
                return true;
            }
        }
        System.out.println("Неверный логин или пароль");
        return false;
    }
}
