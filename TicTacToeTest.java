/**
 * Classe de teste para demonstrar as funcionalidades do jogo da velha
 * 
 * @author Copilot
 * @version 1.0
 */
public class TicTacToeTest {
    
    public static void main(String[] args) {
        System.out.println("=== TESTES DO JOGO DA VELHA ===\n");
        
        testBasicGameplay();
        testWinConditions();
        testTieGame();
        testComputerMode();
        
        System.out.println("Todos os testes foram executados!");
    }
    
    /**
     * Teste básico de jogabilidade
     */
    private static void testBasicGameplay() {
        System.out.println("1. Testando jogabilidade básica:");
        TicTacToe game = new TicTacToe();
        
        System.out.println("Tabuleiro inicial:");
        System.out.println(game.displayBoard());
        
        // Algumas jogadas
        game.makeMove(1, 1); // X no centro
        game.makeMove(0, 0); // O no canto
        game.makeMove(0, 2); // X no canto
        
        System.out.println("Após algumas jogadas:");
        System.out.println(game.displayBoard());
        System.out.println("Jogador atual: " + game.getCurrentPlayer());
        System.out.println("Jogo terminado: " + game.isGameOver());
        System.out.println();
    }
    
    /**
     * Testa condições de vitória
     */
    private static void testWinConditions() {
        System.out.println("2. Testando condições de vitória:");
        
        // Teste vitória horizontal
        System.out.println("Vitória horizontal (linha 0):");
        TicTacToe game1 = new TicTacToe();
        game1.makeMove(0, 0); // X
        game1.makeMove(1, 0); // O
        game1.makeMove(0, 1); // X
        game1.makeMove(1, 1); // O
        game1.makeMove(0, 2); // X ganha
        
        System.out.println(game1.displayBoard());
        System.out.println("Jogo ganho: " + game1.isGameWon());
        System.out.println("Vencedor: " + game1.getWinner());
        System.out.println();
        
        // Teste vitória diagonal
        System.out.println("Vitória diagonal:");
        TicTacToe game2 = new TicTacToe();
        game2.makeMove(0, 0); // X
        game2.makeMove(0, 1); // O
        game2.makeMove(1, 1); // X
        game2.makeMove(0, 2); // O
        game2.makeMove(2, 2); // X ganha
        
        System.out.println(game2.displayBoard());
        System.out.println("Jogo ganho: " + game2.isGameWon());
        System.out.println("Vencedor: " + game2.getWinner());
        System.out.println();
    }
    
    /**
     * Testa jogo empatado
     */
    private static void testTieGame() {
        System.out.println("3. Testando jogo empatado:");
        TicTacToe game = new TicTacToe();
        
        // Sequência que leva ao empate
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(0, 2); // X
        game.makeMove(1, 0); // O
        game.makeMove(1, 2); // X
        game.makeMove(1, 1); // O
        game.makeMove(2, 0); // X
        game.makeMove(2, 2); // O
        game.makeMove(2, 1); // X
        
        System.out.println(game.displayBoard());
        System.out.println("Jogo empatado: " + game.isGameTied());
        System.out.println("Jogo terminado: " + game.isGameOver());
        System.out.println();
    }
    
    /**
     * Testa modo contra computador
     */
    private static void testComputerMode() {
        System.out.println("4. Testando modo contra computador:");
        
        // Humano joga primeiro
        System.out.println("Humano joga primeiro (X):");
        TicTacToe game1 = new TicTacToe(true);
        System.out.println("Modo computador: " + game1.isComputerMode());
        System.out.println("Jogador humano: " + game1.getHumanPlayer());
        
        game1.makeMove(1, 1); // Humano no centro
        System.out.println("Após jogada humana no centro:");
        System.out.println(game1.displayBoard());
        System.out.println();
        
        // Computador joga primeiro
        System.out.println("Computador joga primeiro (X):");
        TicTacToe game2 = new TicTacToe(false);
        System.out.println("Modo computador: " + game2.isComputerMode());
        System.out.println("Jogador humano: " + game2.getHumanPlayer());
        
        // O computador faz a primeira jogada automaticamente
        System.out.println("Após primeira jogada do computador:");
        System.out.println(game2.displayBoard());
        System.out.println();
    }
}