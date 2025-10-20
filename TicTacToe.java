/**
 * Classe TicTacToe - Implementação do jogo da velha
 * Permite dois jogadores humanos ou um jogador contra o computador
 * 
 * @author Copilot
 * @version 1.0
 */
public class TicTacToe {
    
    // Enum para representar os valores das células
    public enum Cell {
        X, O, EMPTY
    }
    
    private Cell[][] board; // Tabuleiro 3x3
    private Cell currentPlayer; // Jogador atual
    private boolean gameWon; // Indica se o jogo foi ganho
    private boolean gameTied; // Indica se houve empate
    private boolean computerMode; // Modo contra computador
    private Cell humanPlayer; // Símbolo do jogador humano (quando joga contra computador)
    
    /**
     * Construtor - Inicializa o tabuleiro com células vazias
     */
    public TicTacToe() {
        board = new Cell[3][3];
        initializeBoard();
        currentPlayer = Cell.X; // X sempre começa
        gameWon = false;
        gameTied = false;
        computerMode = false;
    }
    
    /**
     * Construtor para modo contra computador
     * @param humanGoesFirst true se o humano joga primeiro, false se o computador joga primeiro
     */
    public TicTacToe(boolean humanGoesFirst) {
        this();
        computerMode = true;
        if (humanGoesFirst) {
            humanPlayer = Cell.X;
            currentPlayer = Cell.X;
        } else {
            humanPlayer = Cell.O;
            currentPlayer = Cell.X; // Computador joga primeiro como X
            makeComputerMove(); // Faz a primeira jogada do computador imediatamente
        }
    }
    
    /**
     * Inicializa todas as células do tabuleiro como EMPTY
     */
    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = Cell.EMPTY;
            }
        }
    }
    
    /**
     * Faz uma jogada no tabuleiro
     * @param row linha (0-2)
     * @param col coluna (0-2)
     * @return true se a jogada foi válida, false caso contrário
     */
    public boolean makeMove(int row, int col) {
        // Verifica se a posição é válida e está vazia
        if (row < 0 || row > 2 || col < 0 || col > 2 || 
            board[row][col] != Cell.EMPTY || gameWon || gameTied) {
            return false;
        }
        
        // Faz a jogada
        board[row][col] = currentPlayer;
        
        // Verifica se o jogo terminou
        checkGameStatus();
        
        // Troca o jogador se o jogo não terminou
        if (!gameWon && !gameTied) {
            currentPlayer = (currentPlayer == Cell.X) ? Cell.O : Cell.X;
            
            // Se estiver no modo computador e for a vez do computador, faz a jogada
            if (computerMode && currentPlayer != humanPlayer) {
                makeComputerMove();
            }
        }
        
        return true;
    }
    
    /**
     * Faz a jogada do computador usando estratégia básica
     */
    private void makeComputerMove() {
        if (gameWon || gameTied) return;
        
        // Estratégia do computador:
        // 1. Tenta ganhar
        // 2. Tenta bloquear o oponente
        // 3. Joga no centro se disponível
        // 4. Joga nos cantos
        // 5. Joga nas bordas
        
        Cell computer = (humanPlayer == Cell.X) ? Cell.O : Cell.X;
        Cell human = humanPlayer;
        
        // 1. Tenta ganhar
        if (tryToWin(computer)) return;
        
        // 2. Tenta bloquear o oponente
        if (tryToWin(human)) return;
        
        // 3. Joga no centro se disponível
        if (board[1][1] == Cell.EMPTY) {
            board[1][1] = computer;
            checkGameStatus();
            if (!gameWon && !gameTied) {
                currentPlayer = humanPlayer;
            }
            return;
        }
        
        // 4. Joga nos cantos
        int[][] corners = {{0,0}, {0,2}, {2,0}, {2,2}};
        for (int[] corner : corners) {
            if (board[corner[0]][corner[1]] == Cell.EMPTY) {
                board[corner[0]][corner[1]] = computer;
                checkGameStatus();
                if (!gameWon && !gameTied) {
                    currentPlayer = humanPlayer;
                }
                return;
            }
        }
        
        // 5. Joga nas bordas
        int[][] edges = {{0,1}, {1,0}, {1,2}, {2,1}};
        for (int[] edge : edges) {
            if (board[edge[0]][edge[1]] == Cell.EMPTY) {
                board[edge[0]][edge[1]] = computer;
                checkGameStatus();
                if (!gameWon && !gameTied) {
                    currentPlayer = humanPlayer;
                }
                return;
            }
        }
    }
    
    /**
     * Tenta fazer uma jogada vencedora ou bloqueadora
     * @param player o jogador para o qual tentar a jogada
     * @return true se conseguiu fazer a jogada, false caso contrário
     */
    private boolean tryToWin(Cell player) {
        // Verifica linhas, colunas e diagonais
        for (int i = 0; i < 3; i++) {
            // Verifica linhas
            if (canWinInLine(i, 0, i, 1, i, 2, player)) return true;
            // Verifica colunas
            if (canWinInLine(0, i, 1, i, 2, i, player)) return true;
        }
        
        // Verifica diagonais
        if (canWinInLine(0, 0, 1, 1, 2, 2, player)) return true;
        if (canWinInLine(0, 2, 1, 1, 2, 0, player)) return true;
        
        return false;
    }
    
    /**
     * Verifica se é possível ganhar em uma linha específica
     */
    private boolean canWinInLine(int r1, int c1, int r2, int c2, int r3, int c3, Cell player) {
        Cell[] line = {board[r1][c1], board[r2][c2], board[r3][c3]};
        
        // Conta quantas células o jogador tem nesta linha
        int playerCount = 0;
        int emptyCount = 0;
        int emptyIndex = -1;
        
        for (int i = 0; i < 3; i++) {
            if (line[i] == player) {
                playerCount++;
            } else if (line[i] == Cell.EMPTY) {
                emptyCount++;
                emptyIndex = i;
            }
        }
        
        // Se tem 2 do jogador e 1 vazia, pode ganhar/bloquear
        if (playerCount == 2 && emptyCount == 1) {
            Cell computer = (humanPlayer == Cell.X) ? Cell.O : Cell.X;
            
            // Faz a jogada na posição vazia
            if (emptyIndex == 0) board[r1][c1] = computer;
            else if (emptyIndex == 1) board[r2][c2] = computer;
            else board[r3][c3] = computer;
            
            checkGameStatus();
            if (!gameWon && !gameTied) {
                currentPlayer = humanPlayer;
            }
            return true;
        }
        
        return false;
    }
    
    /**
     * Verifica o status do jogo (vitória ou empate)
     */
    private void checkGameStatus() {
        // Verifica vitória
        gameWon = checkForWin();
        
        // Verifica empate (se não houve vitória e o tabuleiro está cheio)
        if (!gameWon) {
            gameTied = isBoardFull();
        }
    }
    
    /**
     * Verifica se há uma vitória no tabuleiro
     * @return true se alguém ganhou, false caso contrário
     */
    private boolean checkForWin() {
        // Verifica linhas
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != Cell.EMPTY && 
                board[row][0] == board[row][1] && 
                board[row][1] == board[row][2]) {
                return true;
            }
        }
        
        // Verifica colunas
        for (int col = 0; col < 3; col++) {
            if (board[0][col] != Cell.EMPTY && 
                board[0][col] == board[1][col] && 
                board[1][col] == board[2][col]) {
                return true;
            }
        }
        
        // Verifica diagonal principal
        if (board[0][0] != Cell.EMPTY && 
            board[0][0] == board[1][1] && 
            board[1][1] == board[2][2]) {
            return true;
        }
        
        // Verifica diagonal secundária
        if (board[0][2] != Cell.EMPTY && 
            board[0][2] == board[1][1] && 
            board[1][1] == board[2][0]) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Verifica se o tabuleiro está cheio
     * @return true se está cheio, false caso contrário
     */
    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == Cell.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Retorna uma representação em string do tabuleiro
     * @return string representando o tabuleiro
     */
    public String displayBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n  0   1   2\n");
        
        for (int row = 0; row < 3; row++) {
            sb.append(row).append(" ");
            for (int col = 0; col < 3; col++) {
                char symbol;
                switch (board[row][col]) {
                    case X: symbol = 'X'; break;
                    case O: symbol = 'O'; break;
                    default: symbol = ' '; break;
                }
                sb.append(symbol);
                if (col < 2) sb.append(" | ");
            }
            sb.append("\n");
            if (row < 2) sb.append("  ---------\n");
        }
        
        return sb.toString();
    }
    
    // Getters
    public Cell getCurrentPlayer() { return currentPlayer; }
    public boolean isGameWon() { return gameWon; }
    public boolean isGameTied() { return gameTied; }
    public boolean isGameOver() { return gameWon || gameTied; }
    public boolean isComputerMode() { return computerMode; }
    public Cell getHumanPlayer() { return humanPlayer; }
    
    /**
     * Obtém o símbolo do vencedor
     * @return o símbolo do vencedor ou EMPTY se não há vencedor
     */
    public Cell getWinner() {
        if (!gameWon) return Cell.EMPTY;
        
        // Verifica todas as condições de vitória para determinar quem ganhou
        // Verifica linhas
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != Cell.EMPTY && 
                board[row][0] == board[row][1] && 
                board[row][1] == board[row][2]) {
                return board[row][0];
            }
        }
        
        // Verifica colunas
        for (int col = 0; col < 3; col++) {
            if (board[0][col] != Cell.EMPTY && 
                board[0][col] == board[1][col] && 
                board[1][col] == board[2][col]) {
                return board[0][col];
            }
        }
        
        // Verifica diagonal principal
        if (board[0][0] != Cell.EMPTY && 
            board[0][0] == board[1][1] && 
            board[1][1] == board[2][2]) {
            return board[0][0];
        }
        
        // Verifica diagonal secundária
        if (board[0][2] != Cell.EMPTY && 
            board[0][2] == board[1][1] && 
            board[1][1] == board[2][0]) {
            return board[0][2];
        }
        
        return Cell.EMPTY; // Não deveria chegar aqui se gameWon for true
    }
    
    /**
     * Reinicia o jogo
     */
    public void resetGame() {
        initializeBoard();
        currentPlayer = Cell.X;
        gameWon = false;
        gameTied = false;
    }
}