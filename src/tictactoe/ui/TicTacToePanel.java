package tictactoe.ui;

import javax.swing.*;
import java.awt.*;
import regAuth.ui.MainFrame;
import regAuth.ui.Screen;

public class TicTacToePanel extends JPanel {
    private MainFrame mainFrame;
    private JButton[][] buttons = new JButton[3][3];
    private JLabel infoLabel;
    private boolean isXTurn = true;
    private boolean isGameActive = true;

    public TicTacToePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(20));

        infoLabel = new JLabel("Ход: Крестики (Х)");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(infoLabel);
        add(Box.createVerticalStrut(20));

        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        boardPanel.setMaximumSize(new Dimension(300, 300));
        boardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[row][col].setFocusPainted(false);

                JButton currentButton = buttons[row][col];
                currentButton.addActionListener(e -> handleCellClick(currentButton));
                boardPanel.add(buttons[row][col]);
            }
        }
        add(boardPanel);
        add(Box.createVerticalStrut(25));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setMaximumSize(new Dimension(300, 40));

        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(e -> resetGame());
        buttonPanel.add(newGameButton);

        JButton toListButton = new JButton("Назад к списку");
        toListButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        toListButton.addActionListener(e -> mainFrame.showScreen(Screen.LIST));
        buttonPanel.add(toListButton);
        add(buttonPanel);
        add(Box.createVerticalStrut(20));
    }

    public void clearFields() {
    }

    private void handleCellClick(JButton clickedButton) {
        if (!isGameActive || !clickedButton.getText().isEmpty()) {
            return;
        }

        // Определяем текущий символ
        String currentSymbol = isXTurn ? "X" : "O";

        clickedButton.setText(currentSymbol);
        clickedButton.setForeground(isXTurn ? Color.BLUE : Color.RED);

        // 1. Проверяем, не победил ли текущий игрок
        if (checkWinner(currentSymbol)) {
            String winnerName = isXTurn ? "Крестики (Х)" : "Нолики (О)";
            infoLabel.setText("Победили " + winnerName + "!");
            infoLabel.setForeground(new Color(0, 128, 0));
            isGameActive = false;
            setBoardEnabled(false);
            return;
        }

        // 2. Проверяем, не наступила ли ничья
        if (isBoardFull()) {
            infoLabel.setText("Ничья!");
            infoLabel.setForeground(Color.GRAY);
            isGameActive = false;
            setBoardEnabled(false);
            return;
        }

        // 3. Если никто не победил и место есть — передаем ход
        isXTurn = !isXTurn;
        infoLabel.setText("Ход: " + (isXTurn ? "Крестики (Х)" : "Нолики (О)"));
        infoLabel.setForeground(Color.BLACK);
    }


    public void resetGame() {
        isGameActive = true;
        isXTurn = true;
        infoLabel.setText("Ход: Крестики (Х)");

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        setBoardEnabled(true);
    }

    private boolean checkWinner(String symbol) {
        // 1. Проверяем горизонтали (строки)
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(symbol) &&
                    buttons[row][1].getText().equals(symbol) &&
                    buttons[row][2].getText().equals(symbol)) {
                return true;
            }
        }

        // 2. Проверяем вертикали (столбцы)
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(symbol) &&
                    buttons[1][col].getText().equals(symbol) &&
                    buttons[2][col].getText().equals(symbol)) {
                return true;
            }
        }

        // 3. Проверяем диагонали
        if (buttons[0][0].getText().equals(symbol) &&
                buttons[1][1].getText().equals(symbol) &&
                buttons[2][2].getText().equals(symbol)) {
            return true;
        }

        if (buttons[0][2].getText().equals(symbol) &&
                buttons[1][1].getText().equals(symbol) &&
                buttons[2][0].getText().equals(symbol)) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setBoardEnabled(boolean enabled) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setEnabled(enabled);
            }
        }
    }

}

