package regAuth.ui;

import regAuth.model.User;
import regAuth.dao.UserDaoImplBD;

import javax.swing.*;
import java.awt.*;

public class AuthPanel extends JPanel {
    private MainFrame mainFrame;
    private JButton loginButton;
    private UserDaoImplBD db = new UserDaoImplBD();
    private JTextField loginTextField;
    private JPasswordField passwordField;

    private JLabel test;

    public AuthPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension fieldSize = new Dimension(250, 30);
        add(Box.createVerticalStrut(20));

        JLabel loginLabel = new JLabel("Введите логин");
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(loginLabel);
        add(Box.createVerticalStrut(5));

        loginTextField = new JTextField(20);
        loginTextField.setMaximumSize(fieldSize);
        loginTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(loginTextField);
        add(Box.createVerticalStrut(15));

        JLabel passwordLabel = new JLabel("Введите пароль");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(passwordLabel);
        add(Box.createVerticalStrut(5));

        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(fieldSize);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(passwordField);
        add(Box.createVerticalStrut(20));

        loginButton = new JButton("Войти");
        loginButton.addActionListener(e -> performAuth());
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(loginButton);
        add(Box.createVerticalStrut(15));

        test = new JLabel("");
        test.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(test);

        add(Box.createVerticalStrut(15));

        JButton toRegButton = new JButton("Нет аккаунта? Зарегистрироваться");
        toRegButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        toRegButton.addActionListener(e -> mainFrame.showScreen(Screen.REG));
        add(toRegButton);

    }

    private void performAuth() {
        String inputLogin = loginTextField.getText();
        String inputPassword = new String(passwordField.getPassword());

        if (inputLogin.isEmpty()) {
            test.setText("Не заполнено поле ввода логина");
            return;
        }
        if (inputPassword.isEmpty()) {
            test.setText("Не заполнено поле ввода пароля");
            return;
        }

        User user = db.getUser(inputLogin);
        if (user==null || !user.getPassword().equals(inputPassword)) {
            test.setText("Неверный логин или пароль!");
        } else {
            mainFrame.setCurrentUser(inputLogin);
            mainFrame.showScreen(Screen.LIST);
        }
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void clearFields() {
        if (loginTextField != null && passwordField != null) {
            loginTextField.setText("");
            passwordField.setText("");
            test.setText("");
        }
    }

    public String getCurrentLogin() {
        return loginTextField.getText().trim();
    }
}
