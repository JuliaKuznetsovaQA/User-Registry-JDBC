package regAuth.service;

import regAuth.dao.UserDaoImplBD;
import regAuth.model.User;

import java.util.LinkedList;

public class UserService {
    private UserDaoImplBD db = new UserDaoImplBD();

    public LinkedList<String> getAllLogins() {
        LinkedList<User> users = db.getAll();
        LinkedList<String> logins = new LinkedList<>();

        for (User user : users) {
            logins.add(user.getLogin());
        }
        return logins;
    }

    public void deleteUser(String login) {
        db.delete(login);
    }

}
