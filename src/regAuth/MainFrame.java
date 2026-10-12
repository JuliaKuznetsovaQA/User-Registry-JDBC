package regAuth;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel containerPanel;
    private AuthPanel authPanel;
    private RegPanel regPanel;

    public MainFrame() {
        setTitle("Авторизация / Регистрация");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);

        authPanel = new AuthPanel(this);
        containerPanel.add(authPanel, "AUTH");

        regPanel = new RegPanel(this);
        containerPanel.add(regPanel, "REG");

        getContentPane().add(containerPanel);
        cardLayout.show(containerPanel, "AUTH");

        pack();
        setSize(400, 450);
        setLocationRelativeTo(null);

        getRootPane().setDefaultButton(authPanel.getLoginButton());
    }

    public void showScreen(String screenName) {
        authPanel.clearFields();
        regPanel.clearFields();
        cardLayout.show(containerPanel, screenName);
    }
}
