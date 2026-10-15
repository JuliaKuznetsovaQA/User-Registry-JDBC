package regAuth.ui;

import regAuth.command.Command;
import regAuth.command.DeleteUserCommand;
import regAuth.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class ListPanel extends JPanel {
    private MainFrame mainFrame;
    private UserService userService = new UserService();
    private JTable userTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;

    public ListPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(20));

        JLabel titleLabel = new JLabel("СПИСОК ПОЛЬЗОВАТЕЛЕЙ");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);
        add(Box.createVerticalStrut(15));

        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Логин пользователя");

        userTable = new JTable(tableModel);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setMaximumSize(new Dimension(320, 200));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(scrollPane);
        add(Box.createVerticalStrut(15));

        JButton deleteButton = getDeleteButton();
        add(deleteButton);
        add(Box.createRigidArea(new Dimension(0, 15)));

        statusLabel = new JLabel(" ");
        statusLabel.setMinimumSize(new Dimension(320, 25));
        statusLabel.setPreferredSize(new Dimension(320, 25));
        statusLabel.setMaximumSize(new Dimension(320, 25));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(statusLabel);
        add(Box.createRigidArea(new Dimension(0, 15)));

        JButton logoutButton = new JButton("Выход");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.addActionListener(_ -> mainFrame.showScreen(Screen.AUTH));
        add(logoutButton);
        add(Box.createVerticalStrut(20));
    }

    private JButton getDeleteButton() {
        JButton deleteButton = new JButton("Удалить пользователя");
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        deleteButton.addActionListener(_ -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                statusLabel.setText("Выберите пользователя для удаления");
                statusLabel.setForeground(Color.RED);
                return;
            }

            String selectedLogin = (String) tableModel.getValueAt(selectedRow, 0);

            String currentLoggedInUser = mainFrame.getCurrentUser();
            if (selectedLogin.equals(currentLoggedInUser)) {
                JOptionPane.showMessageDialog(this,
                        "Вы не можете удалить самого себя из системы",
                        "Ошибка удаления",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Вы уверены, что хотите удалить пользователя '" + selectedLogin + "'?",
                    "Подтверждение удаления",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                Command deleteCmd = new DeleteUserCommand(userService, this, selectedLogin);
                deleteCmd.execute();

                statusLabel.setText("Пользователь '" + selectedLogin + "' успешно удален.");
                statusLabel.setForeground(new Color(0, 128, 0));
            } else {
                statusLabel.setText("Удаление отменено.");
                statusLabel.setForeground(Color.BLACK);
            }

        });
        return deleteButton;
    }

    public void refreshList() {
        tableModel.setRowCount(0);

        LinkedList<String> logins = userService.getAllLogins();
        for (String login : logins) {
            tableModel.addRow(new Object[]{login});
        }
    }

    public void clearStatus() {
        if (statusLabel != null) {
            statusLabel.setText(" ");
        }
    }
}
