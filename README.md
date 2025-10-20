# Jogo da Velha em Java - Ambiente Grafico com Swing

Este é um projeto simples de Jogo da Velha (Tic-Tac-Toe) desenvolvido em Java com uma interface gráfica utilizando Swing.
Ele suporta dois modos de jogo: dois jogadores humanos ou um jogador contra o computador.

## Funcionalidades

- **Interface Gráfica:** Interface intuitiva e amigável construída com Java Swing.
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
├── TicTacToe.java        # Classe principal com a interface gráfica (GUI)
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

Para iniciar o jogo com a interface gráfica:
```bash
java TicTacToe
```

## Como Jogar

1. **Inicie o jogo:** Execute o programa para abrir a janela do jogo.
2. **Escolha o modo de jogo:**
   - Dois jogadores humanos.
   - Jogador contra computador.
3. **Faça suas jogadas:**
   - Clique com o mouse na célula do tabuleiro onde deseja jogar.
4. **Objetivo:**
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

- **Níveis de dificuldade:** Implementar estratégias mais avançadas para o computador.
- **Modo online:** Permitir que dois jogadores joguem remotamente.
- **Jogo tridimensional:** Expandir para um tabuleiro 4x4x4.

## Licença

Este projeto é de código aberto e está disponível sob a licença MIT. Sinta-se à vontade para usá-lo, modificá-lo e compartilhá-lo.

---
