package regAuth.ui;

import regAuth.service.UserService;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("unused")
public class EditPanel extends JPanel {
    private MainFrame mainFrame;
    private UserService userService = new UserService();
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JLabel titleLabel; // Будем менять эту надпись динамически
    private JLabel statusLabel;

    public EditPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension fieldSize = new Dimension(250, 30);

        add(Box.createVerticalStrut(20));

        // Декоратор: при открытии экрана этот текст будет меняться на "Редактирование: логин"
        titleLabel = new JLabel("СМЕНА ПАРОЛЯ");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);
        add(Box.createVerticalStrut(20));

        JLabel passwordLabel = new JLabel("Введите новый пароль:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(passwordLabel);
        add(Box.createVerticalStrut(5));

        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(fieldSize);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(passwordField);
        add(Box.createVerticalStrut(15));

        JLabel confirmLabel = new JLabel("Повторите новый пароль:");
        confirmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(confirmLabel);
        add(Box.createVerticalStrut(5));

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setMaximumSize(fieldSize);
        confirmPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(confirmPasswordField);
        add(Box.createVerticalStrut(20));

        JButton saveButton = new JButton("Сохранить изменения");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(e -> performUpdate());
        add(saveButton);
        add(Box.createVerticalStrut(15));

        statusLabel = new JLabel(" ");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(statusLabel);
        add(Box.createVerticalStrut(15));

        JButton toListButton = new JButton("Назад к списку");
        toListButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        toListButton.addActionListener(e -> mainFrame.showScreen(Screen.LIST));
        add(toListButton);
        add(Box.createVerticalStrut(20));
    }

    public void setupEditScreen() {
        String targetUser = mainFrame.getTargetEditingUser();
        titleLabel.setText("СМЕНА ПАРОЛЯ ДЛЯ ПОЛЬЗОВАТЕЛЯ " + targetUser);
        clearFields();
    }

    private void performUpdate() {
        String newPassword = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (newPassword.isEmpty()) {
            statusLabel.setText("Поле ввода пароля не заполнено!");
            statusLabel.setForeground(Color.RED);
            return;
        }
        if (confirmPassword.isEmpty()) {
            statusLabel.setText("Подтвердите пароль!");
            statusLabel.setForeground(Color.RED);
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            statusLabel.setText("Пароли не совпадают!");
            statusLabel.setForeground(Color.RED);
            return;
        }

        userService.updatePassword(mainFrame.getTargetEditingUser(), newPassword);

        statusLabel.setText("Пароль успешно обновлен!");
        statusLabel.setForeground(new Color(0, 128, 0));
    }

    public void clearFields() {
        if (passwordField != null && confirmPasswordField != null) {
            passwordField.setText("");
            confirmPasswordField.setText("");
            statusLabel.setText(" ");
        }
    }
}
