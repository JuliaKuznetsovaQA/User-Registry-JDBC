package regAuth.ui;

import javax.swing.*;
import java.awt.*;

public class BaseFormPanel extends JPanel {
    protected MainFrame mainFrame;
    protected JPasswordField passwordField;
    protected JPasswordField confirmPasswordField;
    protected JLabel titleLabel;
    protected JLabel statusLabel;
    protected JButton actionButton;

    public BaseFormPanel(MainFrame mainFrame, String buttonText) {
        this.mainFrame = mainFrame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Dimension fieldSize = new Dimension(250, 30);

        // 1. Отступ сверху и заголовок (наследники сами изменят текст)
        add(Box.createVerticalStrut(20));
        titleLabel = new JLabel("");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);
        add(Box.createVerticalStrut(15));

        // 2. Блок основного пароля
        JLabel passwordLabel = new JLabel("Придумайте пароль:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(passwordLabel);
        add(Box.createVerticalStrut(5));

        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(fieldSize);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(passwordField);
        add(Box.createVerticalStrut(15));

        // 3. Блок подтверждения пароля
        JLabel confirmLabel = new JLabel("Повторите пароль:");
        confirmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(confirmLabel);
        add(Box.createVerticalStrut(5));

        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setMaximumSize(fieldSize);
        confirmPasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(confirmPasswordField);
        add(Box.createVerticalStrut(20));

        // 4. Главная кнопка действия
        actionButton = new JButton(buttonText);
        actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(actionButton);
        add(Box.createVerticalStrut(15));

        // 5. Метка статуса для ошибок/успеха
        statusLabel = new JLabel(" ");
        statusLabel.setMinimumSize(new Dimension(320, 25));
        statusLabel.setPreferredSize(new Dimension(320, 25));
        statusLabel.setMaximumSize(new Dimension(320, 25));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(statusLabel);
        add(Box.createVerticalStrut(15));
    }

    // Общий метод очистки полей
    public void clearFields() {
        if (passwordField != null && confirmPasswordField != null) {
            passwordField.setText("");
            confirmPasswordField.setText("");
            statusLabel.setText(" ");
        }
    }
}
