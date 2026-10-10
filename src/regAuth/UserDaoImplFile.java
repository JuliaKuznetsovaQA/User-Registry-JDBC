package regAuth;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.LinkedList;

public class UserDaoImplFile implements UserDao{
    private final String fileName = "user.json";
    private static LinkedList<User> users = new LinkedList<>();
    Gson gson = new Gson();

    public void saveUsersToJsonFile() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(gson.toJson(users));
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Override
    public void create(User user) {
        getAll();
        boolean exist = false;
        for(User u: users) {
            if(u.getLogin().equals(user.getLogin())) {
                exist = true;
                System.out.println("Такой пользователь уже есть в списке");
                break;
            }
        }
        if(!exist) {
            users.add(user);
            System.out.println("Пользователь " + user.getLogin() + " успешно зарегистрирован.");
        }
        saveUsersToJsonFile();
    }

    @Override
    public User getUser(String login) {
        for(User user: users) {
            if(user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public LinkedList<User> getAll() {
        TypeToken<LinkedList<User>> linkedListType = new TypeToken<>() {
        };
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            users = gson.fromJson(br.readLine(), linkedListType);
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
        return users;
    }

    @Override
    public void update(User user) {
        getAll();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(user.getLogin())) {
                users.set(i, user);
                System.out.println("Пользователь " + user.getLogin() + " успешно обновлен.");
                saveUsersToJsonFile();
                return;
            }
        }
        System.out.println("Пользователь не найден.");
    }

    @Override
    public void delete(String login) {
        getAll();
        boolean removed = users.removeIf(user -> user.getLogin().equals(login));

        if (removed) {
            System.out.println("Пользователь " + login + " успешно удален.");
            saveUsersToJsonFile();
        } else {
            System.out.println("Пользователь не найден.");
        }
    }
}
