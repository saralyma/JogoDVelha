/**
 * Demonstração avançada das funcionalidades do Jogo da Velha
 * Mostra diferentes cenários e estratégias
 * 
 * @author Copilot
 * @version 1.0
 */
public class TicTacToeDemo {
    
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO AVANÇADA DO JOGO DA VELHA ===\n");
        
        demonstrateComputerStrategy();
        demonstrateAllWinConditions();
        demonstrateErrorHandling();
        
        System.out.println("Demonstração concluída!");
    }
    
    /**
     * Demonstra a estratégia do computador
     */
    private static void demonstrateComputerStrategy() {
        System.out.println("🤖 ESTRATÉGIA DO COMPUTADOR:");
        System.out.println("1. Tenta ganhar");
        System.out.println("2. Bloqueia o oponente");
        System.out.println("3. Prioriza centro");
        System.out.println("4. Escolhe cantos");
        System.out.println("5. Usa bordas como última opção\n");
        
        // Demonstração: Computador tenta ganhar
        System.out.println("Cenário 1: Computador tentando ganhar");
        TicTacToe game1 = new TicTacToe(true); // Humano primeiro
        
        // Simula jogadas onde o computador pode ganhar
        game1.makeMove(0, 0); // Humano (X)
        // Computador jogou automaticamente (O)
        game1.makeMove(0, 1); // Humano (X)  
        // Computador jogou automaticamente (O)
        game1.makeMove(1, 0); // Humano (X)
        // Computador deveria ganhar agora (O)
        
        System.out.println("Estado final:");
        System.out.println(game1.displayBoard());
        System.out.println("Computador ganhou: " + (game1.isGameWon() && game1.getWinner() == TicTacToe.Cell.O));
        System.out.println();
        
        // Demonstração: Computador bloqueando
        System.out.println("Cenário 2: Computador bloqueando vitória do humano");
        TicTacToe game2 = new TicTacToe(true); // Humano primeiro
        
        game2.makeMove(0, 0); // Humano (X)
        // Computador jogou automaticamente (O) 
        game2.makeMove(0, 1); // Humano (X) - duas em linha!
        // Computador deveria bloquear em (0,2)
        
        System.out.println("Estado após tentativa de bloquear:");
        System.out.println(game2.displayBoard());
        System.out.println();
    }
    
    /**
     * Demonstra todas as condições de vitória possíveis
     */
    private static void demonstrateAllWinConditions() {
        System.out.println("🏆 TODAS AS CONDIÇÕES DE VITÓRIA:\n");
        
        String[] descriptions = {
            "Linha 0 (superior)",
            "Linha 1 (meio)", 
            "Linha 2 (inferior)",
            "Coluna 0 (esquerda)",
            "Coluna 1 (meio)",
            "Coluna 2 (direita)",
            "Diagonal principal",
            "Diagonal secundária"
        };
        
        int[][][] winPositions = {
            {{0,0}, {0,1}, {0,2}}, // Linha 0
            {{1,0}, {1,1}, {1,2}}, // Linha 1
            {{2,0}, {2,1}, {2,2}}, // Linha 2
            {{0,0}, {1,0}, {2,0}}, // Coluna 0
            {{0,1}, {1,1}, {2,1}}, // Coluna 1
            {{0,2}, {1,2}, {2,2}}, // Coluna 2
            {{0,0}, {1,1}, {2,2}}, // Diagonal principal
            {{0,2}, {1,1}, {2,0}}  // Diagonal secundária
        };
        
        for (int i = 0; i < descriptions.length; i++) {
            System.out.println((i + 1) + ". " + descriptions[i] + ":");
            TicTacToe game = new TicTacToe();
            
            // Faz as três jogadas vencedoras para X
            int[][] positions = winPositions[i];
            for (int j = 0; j < 3; j++) {
                game.makeMove(positions[j][0], positions[j][1]); // X
                if (j < 2) {
                    // O joga em uma posição qualquer que não interfira
                    boolean moved = false;
                    for (int row = 0; row < 3 && !moved; row++) {
                        for (int col = 0; col < 3 && !moved; col++) {
                            if (game.makeMove(row, col)) {
                                moved = true;
                            }
                        }
                    }
                }
            }
            
            System.out.println(game.displayBoard());
            System.out.println("Vencedor: " + game.getWinner());
            System.out.println();
        }
    }
    
    /**
     * Demonstra tratamento de erros
     */
    private static void demonstrateErrorHandling() {
        System.out.println("⚠️  TRATAMENTO DE ERROS:\n");
        
        TicTacToe game = new TicTacToe();
        
        System.out.println("1. Tentativas de jogadas inválidas:");
        
        // Jogada válida primeiro
        boolean result = game.makeMove(1, 1);
        System.out.println("Jogada (1,1): " + (result ? "✓ Válida" : "✗ Inválida"));
        
        // Tentativa de jogar na mesma posição
        result = game.makeMove(1, 1);
        System.out.println("Jogada (1,1) novamente: " + (result ? "✓ Válida" : "✗ Inválida - posição ocupada"));
        
        // Tentativa de posição fora dos limites
        result = game.makeMove(3, 3);
        System.out.println("Jogada (3,3): " + (result ? "✓ Válida" : "✗ Inválida - fora dos limites"));
        
        result = game.makeMove(-1, 0);
        System.out.println("Jogada (-1,0): " + (result ? "✓ Válida" : "✗ Inválida - coordenada negativa"));
        
        System.out.println("\nEstado atual do tabuleiro:");
        System.out.println(game.displayBoard());
        
        // Completa o jogo para testar jogadas após o fim
        game.makeMove(0, 0); // O
        game.makeMove(0, 1); // X
        game.makeMove(0, 2); // O
        game.makeMove(2, 0); // X
        game.makeMove(2, 1); // O
        game.makeMove(2, 2); // X - não ganha ainda
        game.makeMove(1, 0); // O ganha na coluna 0
        
        System.out.println("\n2. Tentativa de jogar após o fim do jogo:");
        result = game.makeMove(1, 2);
        System.out.println("Jogada após vitória: " + (result ? "✓ Válida" : "✗ Inválida - jogo já terminou"));
        
        System.out.println("\nTabuleiro final:");
        System.out.println(game.displayBoard());
        System.out.println("Status: " + (game.isGameWon() ? "Jogo ganho por " + game.getWinner() : 
                                       game.isGameTied() ? "Empate" : "Jogo em andamento"));
        System.out.println();
    }
}