package regAuth.ui;

import regAuth.model.User;
import regAuth.dao.UserDaoImplBD;

import javax.swing.*;
import java.awt.*;

public class RegPanel extends JPanel {
    private MainFrame mainFrame;
    private UserDaoImplBD db = new UserDaoImplBD();
    private JTextField loginTextField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JLabel statusLabel;

    public RegPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension fieldSize = new Dimension(250, 30);

        add(Box.createVerticalStrut(20));

        JLabel loginLabel = new JLabel("Логин:");
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(loginLabel);
        add(Box.createVerticalStrut(5));

        loginTextField = new JTextField(20);
        loginTextField.setMaximumSize(fieldSize);
        loginTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(loginTextField);
        add(Box.createVerticalStrut(15));

        JLabel passwordLabel = new JLabel("Пароль:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(passwordLabel);
        add(Box.createVerticalStrut(5));

        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(fieldSize);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(passwordField);
        add(Box.createVerticalStrut(15));

        JLabel confirmLabel = new JLabel("Повторите пароль:");
        confirmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(confirmLabel);
        add(Box.createVerticalStrut(5));

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setMaximumSize(fieldSize);
        confirmPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(confirmPasswordField);
        add(Box.createVerticalStrut(20));

        JButton regButton = new JButton("Зарегистрироваться");
        regButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        regButton.addActionListener(_ -> performRegister());
        add(regButton);
        add(Box.createVerticalStrut(15));

        statusLabel = new JLabel("");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(statusLabel);
        add(Box.createVerticalStrut(15));

        JButton toAuthButton = new JButton("Назад к авторизации");
        toAuthButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        toAuthButton.addActionListener(_ -> mainFrame.showScreen(Screen.AUTH));
        add(toAuthButton);
    }

    private void performRegister() {
        String inputLogin = loginTextField.getText().trim(); // .trim() убирает случайные пробелы
        String inputPassword = new String(passwordField.getPassword());
        String inputConfirm = new String(confirmPasswordField.getPassword());

        if (inputLogin.isEmpty()) {
            statusLabel.setText("Не заполнено поле ввода логина");
            return;
        }
        if (inputPassword.isEmpty()) {
            statusLabel.setText("Не заполнено поле ввода пароля");
            return;
        }
        if (inputConfirm.isEmpty()) {
            statusLabel.setText("Подтвердите пароль");
            return;
        }

        if (!inputPassword.equals(inputConfirm)) {
            statusLabel.setText("Пароли не совпадают!");
            return;
        }

        User existingUser = db.getUser(inputLogin);
        if (existingUser != null) {
            statusLabel.setText("Логин '" + inputLogin + "' уже занят!");
            return;
        }

        db.create(new User(inputLogin, inputPassword));
        statusLabel.setText("Пользователь успешно зарегистрирован!");

        loginTextField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    public void clearFields() {
        if (loginTextField != null && passwordField != null && confirmPasswordField != null) {
            loginTextField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
            statusLabel.setText("");
        }
    }

}
