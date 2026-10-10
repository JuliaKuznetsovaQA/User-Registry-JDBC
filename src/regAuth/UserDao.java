package regAuth;

import java.util.LinkedList;

public interface UserDao {

    void create(User user);

    User getUser(String login);

    LinkedList<User> getAll();

    void update(User user);
    void delete(String login);
}
