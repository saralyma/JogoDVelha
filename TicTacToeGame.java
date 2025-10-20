
import java.util.Scanner;

/**
 * Classe principal para executar o jogo da velha Fornece interface de linha de
 * comando para interação com o usuário
 *
 * @author Copilot
 * @version 1.0
 */
public class TicTacToeGame {

    private Scanner scanner;
    private TicTacToe game;

    public TicTacToeGame() {
        scanner = new Scanner(System.in);
    }

    /**
     * Método principal para executar o jogo
     */
    public static void main(String[] args) {
        TicTacToeGame gameRunner = new TicTacToeGame();
        gameRunner.playGame();
    }

    /**
     * Loop principal do jogo
     */
    public void playGame() {
        System.out.println("=== JOGO DA VELHA ===");
        System.out.println("Bem-vindo ao Tic-Tac-Toe!");

        boolean playAgain = true;

        while (playAgain) {
            setupGame();
            runGameLoop();
            playAgain = askPlayAgain();
        }

        System.out.println("Obrigado por jogar!");
        scanner.close();
    }

    /**
     * Configura o tipo de jogo (2 jogadores ou contra computador)
     */
    private void setupGame() {
        System.out.println("\nEscolha o modo de jogo:");
        System.out.println("1. Dois jogadores humanos");
        System.out.println("2. Jogador vs Computador");

        int choice = getValidChoice(1, 2);

        if (choice == 1) {
            // Modo dois jogadores
            game = new TicTacToe();
            System.out.println("\nModo: Dois jogadores");
            System.out.println("Jogador 1: X");
            System.out.println("Jogador 2: O");
        } else {
            // Modo contra computador
            System.out.println("\nVocê quer ser o primeiro jogador? (s/n)");
            String response = scanner.nextLine().toLowerCase();
            boolean humanFirst = response.startsWith("s");

            game = new TicTacToe(humanFirst);

            if (humanFirst) {
                System.out.println("\nModo: Você vs Computador");
                System.out.println("Você: X (primeiro)");
                System.out.println("Computador: O");
            } else {
                System.out.println("\nModo: Você vs Computador");
                System.out.println("Computador: X (primeiro)");
                System.out.println("Você: O");
            }
        }
    }

    /**
     * Loop principal de uma partida
     */
    private void runGameLoop() {
        System.out.println("\nInstruções:");
        System.out.println("- Digite a linha (0-2) e coluna (0-2) para fazer sua jogada");
        System.out.println("- Exemplo: '1 1' para jogar no centro");

        while (!game.isGameOver()) {
            System.out.println(game.displayBoard());

            if (game.isComputerMode() && game.getCurrentPlayer() != game.getHumanPlayer()) {
                System.out.println("Computador está pensando...");
                try {
                    Thread.sleep(1000); // Simula tempo de "pensamento"
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                // A jogada do computador é feita automaticamente na classe TicTacToe
            } else {
                makeHumanMove();
            }
        }

        // Mostra resultado final
        System.out.println(game.displayBoard());
        displayGameResult();
    }

    /**
     * Processa a jogada de um jogador humano
     */
    private void makeHumanMove() {
        boolean validMove = false;

        while (!validMove) {
            if (game.isComputerMode()) {
                System.out.printf("Sua vez (%s): ", game.getCurrentPlayer());
            } else {
                System.out.printf("Jogador %s, digite sua jogada: ", game.getCurrentPlayer());
            }

            String input = scanner.nextLine().trim();

            // Verifica se o input tem o formato correto
            String[] parts = input.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Formato inválido! Digite linha e coluna separadas por espaço (ex: 1 1)");
                continue;
            }

            try {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                validMove = game.makeMove(row, col);

                if (!validMove) {
                    System.out.println("Jogada inválida! Tente novamente.");
                    System.out.println("Certifique-se de que:");
                    System.out.println("- Os números estão entre 0 e 2");
                    System.out.println("- A posição está vazia");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números!");
            }
        }
    }

    /**
     * Mostra o resultado final do jogo
     */
    private void displayGameResult() {
        if (game.isGameWon()) {
            TicTacToe.Cell winner = game.getWinner();

            if (game.isComputerMode()) {
                if (winner == game.getHumanPlayer()) {
                    System.out.println("🎉 Parabéns! Você ganhou!");
                } else {
                    System.out.println("😅 O computador ganhou! Tente novamente.");
                }
            } else {
                System.out.println("🎉 Jogador " + winner + " ganhou!");
            }
        } else if (game.isGameTied()) {
            System.out.println("🤝 Empate! Bom jogo para ambos!");
        }
    }

    /**
     * Pergunta se o usuário quer jogar novamente
     *
     * @return true se quer jogar novamente, false caso contrário
     */
    private boolean askPlayAgain() {
        System.out.println("\nDeseja jogar novamente? (s/n)");
        String response = scanner.nextLine().toLowerCase();
        return response.startsWith("s");
    }

    /**
     * Obtém uma escolha válida do usuário dentro de um intervalo
     *
     * @param min valor mínimo
     * @param max valor máximo
     * @return escolha válida do usuário
     */
    private int getValidChoice(int min, int max) {
        int choice = -1;
        boolean valid = false;

        while (!valid) {
            System.out.print("Digite sua escolha (" + min + "-" + max + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    valid = true;
                } else {
                    System.out.println("Escolha inválida! Digite um número entre " + min + " e " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido!");
            }
        }

        return choice;
    }
}
