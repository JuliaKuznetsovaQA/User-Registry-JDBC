package regAuth.ui;

import regAuth.dao.UserDaoImplBD;
import regAuth.model.User;
import javax.swing.*;
import java.awt.*;

public class RegPanel extends BaseFormPanel {
    private UserDaoImplBD db = new UserDaoImplBD();
    private JTextField loginTextField;

    public RegPanel(MainFrame mainFrame) {
        super(mainFrame, "Зарегистрироваться");
        titleLabel.setText("РЕГИСТРАЦИЯ");

        Dimension fieldSize = new Dimension(250, 30);

        JLabel loginLabel = new JLabel("Придумайте логин:");
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginTextField = new JTextField(20);
        loginTextField.setMaximumSize(fieldSize);
        loginTextField.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(loginLabel, 2);
        add(Box.createVerticalStrut(5), 3);
        add(loginTextField, 4);
        add(Box.createVerticalStrut(15), 5);

        actionButton.addActionListener(e -> performRegister());

        JButton toAuthButton = new JButton("Назад к авторизации");
        toAuthButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        toAuthButton.addActionListener(e -> mainFrame.showScreen(Screen.AUTH));
        add(toAuthButton);
        add(Box.createVerticalStrut(20));
    }

    private void performRegister() {
        String inputLogin = loginTextField.getText().trim();
        String inputPassword = new String(passwordField.getPassword());
        String inputConfirm = new String(confirmPasswordField.getPassword());

        if (inputLogin.isEmpty()) {
            statusLabel.setText("Не заполнено поле ввода логина");
            statusLabel.setForeground(Color.RED);
            return;
        }
        if (inputPassword.isEmpty()) {
            statusLabel.setText("Не заполнено поле ввода пароля");
            statusLabel.setForeground(Color.RED);
            return;
        }
        if (inputConfirm.isEmpty()) {
            statusLabel.setText("Подтвердите пароль");
            statusLabel.setForeground(Color.RED);
            return;
        }
        if (!inputPassword.equals(inputConfirm)) {
            statusLabel.setText("Пароли не совпадают!");
            statusLabel.setForeground(Color.RED);
            return;
        }

        User existingUser = db.getUser(inputLogin);
        if (existingUser != null) {
            statusLabel.setText("Логин '" + inputLogin + "' уже занят!");
            statusLabel.setForeground(Color.RED);
            return;
        }

        db.create(new User(inputLogin, inputPassword));
        statusLabel.setText("Пользователь успешно зарегистрирован!");
        statusLabel.setForeground(new Color(0, 128, 0));

        loginTextField.setText("");
        clearFields();
    }

    @Override
    public void clearFields() {
        super.clearFields();
        if (loginTextField != null) {
            loginTextField.setText("");
        }
    }
}
