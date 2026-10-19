package regAuth.ui;

import regAuth.service.UserService;
import javax.swing.*;
import java.awt.*;

public class EditPanel extends BaseFormPanel {
    private UserService userService = new UserService();

    public EditPanel(MainFrame mainFrame) {
        super(mainFrame, "Сохранить изменения");

        actionButton.addActionListener(e -> performUpdate());

        JButton toListButton = new JButton("Назад к списку");
        toListButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        toListButton.addActionListener(e -> mainFrame.showScreen(Screen.LIST));
        add(toListButton);
        add(Box.createVerticalStrut(20));
    }

    public void setupEditScreen() {
        titleLabel.setText("СМЕНА ПАРОЛЯ ДЛЯ ПОЛЬЗОВАТЕЛЯ " + mainFrame.getTargetEditingUser());
        clearFields();
    }

    private void performUpdate() {
        String newPassword = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            statusLabel.setText("Заполните все поля ввода");
            statusLabel.setForeground(Color.RED);
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            statusLabel.setText("Пароли не совпадают");
            statusLabel.setForeground(Color.RED);
            return;
        }

        userService.updatePassword(mainFrame.getTargetEditingUser(), newPassword);

        statusLabel.setText("Пароль успешно обновлен");
        statusLabel.setForeground(new Color(0, 128, 0));
    }
}
