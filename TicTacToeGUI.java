import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGUI extends JFrame {
    private TicTacToe game;
    private JButton[][] buttons = new JButton[3][3];
    private JLabel statusLabel;
    private boolean vsComputer = false;

    public TicTacToeGUI() {
        setTitle("Jogo da Velha");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(e -> handleMove(row, col));
                boardPanel.add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel("Escolha o modo de jogo", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton resetButton = new JButton("Reiniciar Jogo");
        resetButton.addActionListener(e -> showModeSelection());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(resetButton, BorderLayout.SOUTH);

        add(boardPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null); // Centraliza a janela
        
        // Inicia o diálogo de seleção de modo
        showModeSelection();
        
        setVisible(true);
    }

    private void showModeSelection() {
        String[] options = {"Jogador vs Jogador", "Jogador vs Computador"};
        int choice = JOptionPane.showOptionDialog(this, "Escolha o modo de jogo:", "Jogo da Velha",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        vsComputer = (choice == 1);
        resetGame();
    }

    private void handleMove(int row, int col) {
        if (game == null || !buttons[row][col].getText().equals("") || game.isGameOver()) {
            return;
        }

        if (game.makeMove(row, col)) {
            updateBoard();
            if (game.isGameOver()) {
                displayWinner();
            } else if (vsComputer) {
                // A lógica em makeMove já chama o computador e troca o jogador
                updateBoard(); // Atualiza o tabuleiro após a jogada do computador
                if (game.isGameOver()) {
                    displayWinner();
                }
            }
            updateStatus();
        }
    }

    private void updateBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                TicTacToe.Cell cell = game.getCell(i, j);
                if (cell != TicTacToe.Cell.EMPTY) {
                    buttons[i][j].setText(cell.toString());
                } else {
                    buttons[i][j].setText("");
                }
            }
        }
    }

    private void displayWinner() {
        if (game.isGameWon()) {
            statusLabel.setText("Jogador " + game.getWinner() + " venceu!");
        } else if (game.isGameTied()) {
            statusLabel.setText("Deu velha! (Empate)");
        }
        // Desabilita os botões
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
    
    private void updateStatus() {
        if (!game.isGameOver()) {
            statusLabel.setText("Vez do Jogador " + game.getCurrentPlayer());
        }
    }

    private void resetGame() {
        if (vsComputer) {
            // O jogador humano será 'X' e começa primeiro
            game = new TicTacToe(true); 
        } else {
            game = new TicTacToe();
        }
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        statusLabel.setText("Jogador X começa");
    }

    public static void main(String[] args) {
        // Garante que a GUI seja criada na thread de eventos da AWT
        SwingUtilities.invokeLater(TicTacToeGUI::new);
    }
}
