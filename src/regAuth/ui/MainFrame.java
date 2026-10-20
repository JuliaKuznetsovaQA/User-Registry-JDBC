package regAuth.ui;

import javax.swing.*;
import java.awt.*;
import tictactoe.ui.TicTacToePanel;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel containerPanel;
    private AuthPanel authPanel;
    private RegPanel regPanel;
    private ListPanel listPanel;
    private String currentUser;
    private String targetEditingUser;
    private EditPanel editPanel;
    private TicTacToePanel ticTacToePanel;

    public void setTargetEditingUser(String login) {
        this.targetEditingUser = login;
    }

    public String getTargetEditingUser() {
        return targetEditingUser;
    }

    public void setCurrentUser(String user) {
        this.currentUser = user;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public MainFrame() {
        setTitle("Авторизация / Регистрация");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);

        authPanel = new AuthPanel(this);
        containerPanel.add(authPanel, Screen.AUTH.name());

        regPanel = new RegPanel(this);
        containerPanel.add(regPanel, Screen.REG.name());

        listPanel = new ListPanel(this);
        containerPanel.add(listPanel, Screen.LIST.name());

        editPanel = new EditPanel(this);
        containerPanel.add(editPanel, Screen.EDIT.name());

        ticTacToePanel = new TicTacToePanel(this);
        containerPanel.add(ticTacToePanel, Screen.TIC_TAC_TOE.name());

        getContentPane().add(containerPanel);
        cardLayout.show(containerPanel, Screen.AUTH.name());

        setSize(400, 450);
        setLocationRelativeTo(null);

        getRootPane().setDefaultButton(authPanel.getLoginButton());
    }

    public void showScreen(Screen screen) {
        authPanel.clearFields();
        if (regPanel != null) regPanel.clearFields();
        if (listPanel != null) listPanel.clearStatus();
        if (editPanel != null) editPanel.clearFields();
        if (editPanel != null) editPanel.clearFields();
        if (ticTacToePanel != null) ticTacToePanel.clearFields();

        if (screen == Screen.TIC_TAC_TOE) {
            setTitle("Крестики-Нолики");
            ticTacToePanel.resetGame();
        } else if (screen == Screen.LIST) {
            setTitle("Список пользователей");
        } else {
            setTitle("Авторизация / Регистрация");
        }
        
        if (screen == Screen.LIST) {
            assert listPanel != null;
            listPanel.refreshList();
        }
        if (screen == Screen.EDIT) {
            assert editPanel != null;
            editPanel.setupEditScreen();
        }
        if (screen == Screen.TIC_TAC_TOE) {
            ticTacToePanel.resetGame();
        }
        cardLayout.show(containerPanel, screen.name());
    }

    public AuthPanel getAuthPanel() {
        return authPanel;
    }
}
