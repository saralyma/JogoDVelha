# Jogo da Velha em Java

Este é um projeto simples de Jogo da Velha (Tic-Tac-Toe) desenvolvido em Java.
Ele suporta dois modos de jogo: dois jogadores humanos ou um jogador contra o computador.

## Funcionalidades

- **Dois modos de jogo:**
  - Dois jogadores humanos alternando entre X e O.
  - Um jogador humano contra o computador, com o jogador podendo escolher quem começa.
- **Validação de jogadas:** Apenas jogadas em células vazias são permitidas.
- **Detecção de vitória e empate:** O jogo verifica automaticamente se há um vencedor ou se ocorreu um empate após cada jogada.
- **Estratégia do computador:**
  - Tenta ganhar se possível.
  - Bloqueia o oponente se ele estiver prestes a ganhar.
  - Prioriza o centro, depois os cantos e, por último, as bordas.

## Estrutura do Projeto

```
JogoDVelha/
├── TicTacToe.java        # Classe principal do jogo
├── TicTacToeGame.java    # Interface de linha de comando para jogar
├── TicTacToeTest.java    # Testes automatizados
├── TicTacToeDemo.java    # Demonstração avançada
└── README.md             # Este arquivo
```

## Como Compilar e Executar

### Pré-requisitos
- Java JDK 8 ou superior instalado.

### Compilação
Abra o terminal na pasta do projeto e execute:
```bash
javac *.java
```

### Execução

#### Para jogar:
```bash
java TicTacToeGame
```

#### Para executar os testes:
```bash
java TicTacToeTest
```

#### Para ver a demonstração avançada:
```bash
java TicTacToeDemo
```

## Como Jogar

1. **Escolha o modo de jogo:**
   - Dois jogadores humanos.
   - Jogador contra computador.
2. **Faça suas jogadas:**
   - Digite as coordenadas no formato "linha coluna" (exemplo: `1 1` para o centro).
   - As coordenadas válidas são 0, 1 e 2.
3. **Objetivo:**
   - Alinhe três símbolos iguais (horizontal, vertical ou diagonal).
   - Evite que o oponente faça três em linha.

## Exemplo de Tabuleiro

```
  0   1   2
0 X | O | X
  ---------
1 O | X | O
  ---------
2 X |   | O
```

## Melhorias Futuras

- **Interface Gráfica (GUI):** Usar JavaFX ou Swing para criar uma interface visual.
- **Níveis de dificuldade:** Implementar estratégias mais avançadas para o computador.
- **Modo online:** Permitir que dois jogadores joguem remotamente.
- **Jogo tridimensional:** Expandir para um tabuleiro 4x4x4.

## Licença

Este projeto é de código aberto e está disponível sob a licença MIT. Sinta-se à vontade para usá-lo, modificá-lo e compartilhá-lo.

---
