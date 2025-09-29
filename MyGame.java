import java.util.*;

public class MyGame {
    public static void createBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = '.';
            }
        }
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean checkWinner(char[][] board, char player) {
        for (int i = 0; i < board.length; i++) {
            // rows:
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            // Columns:
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
            if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
                return true;
            }
            if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
                return true;
            }
        }
        return false;
    }

    public static boolean drawGame(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        final int BOARD_WIDTH = 3;
        final int BOARD_HEIGHT = 3;
        char[][] gameBoard = new char[BOARD_HEIGHT][BOARD_WIDTH];

        char playerX = 'X';
        char playerO = 'O';

        int playerXWinCount = 0, playerOWinCount = 0;

        while (true) {
            createBoard(gameBoard);
            System.out.print("Enter player (X/O) : ");
            String playerChoice = sc.next();
            char currentPlayer = (playerChoice.toUpperCase().charAt(0) == 'X') ? playerX : playerO;

            boolean gameEnded = false;

            while (!gameEnded) {
                printBoard(gameBoard);

                System.out.println("currentPlayer : " + currentPlayer);

                System.out.print("Enter row (0-2) : ");
                int row = sc.nextInt();
                System.out.print("Enter column (0-2) : ");
                int col = sc.nextInt();
                System.out.println();

                if (row < 0 || row > gameBoard.length || col < 0 || col > gameBoard[0].length) {
                    System.out.println("Invalid coordinates!");
                    continue;
                }

                // Block player from overriding the playerPos:
                boolean currPos = true;
                if (gameBoard[row][col] == '.' && currPos) {
                    gameBoard[row][col] = currentPlayer;
                } else {
                    currPos = false;
                }

                if (checkWinner(gameBoard, currentPlayer)) {
                    printBoard(gameBoard);
                    System.out.println("Winner is " + currentPlayer);
                    if (currentPlayer == playerX) {
                        playerXWinCount++;
                    } else {
                        playerOWinCount++;
                    }
                    gameEnded = true;
                } else if (drawGame(gameBoard)) {
                    printBoard(gameBoard);
                    System.out.println("It's a Draw!");
                    gameEnded = true;
                }
                // player Switch:
                else {
                    currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
                }

            }

            System.out.println("\n---SCORES---");
            System.out.println("Player O : " + playerOWinCount);
            System.out.println("Player X : " + playerXWinCount);
            System.out.println("--------------");

            System.out.print("\nPlay again? (yes/no) : ");
            String playAgain = sc.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }
        System.out.println("Game Over. Thanks for playing!");
        sc.close();
    }
}
