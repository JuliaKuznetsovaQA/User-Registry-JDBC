import regAuth.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDaoImplBD db = new UserDaoImplBD();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите логин: ");
        String inputLogin = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String inputPassword = scanner.nextLine();

        User user = db.getUser(inputLogin);

        if (user == null) {
            System.out.println("Авторизация не удалась: пользователя с таким логином не существует.");
        } else if (!user.getPassword().equals(inputPassword)) {
            System.out.println("Авторизация не удалась: введен неверный пароль.");
        } else {
            System.out.println("Добро пожаловать!");
        }

        scanner.close();
    }
}