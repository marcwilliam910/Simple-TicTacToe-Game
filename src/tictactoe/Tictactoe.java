package tictactoe;

import java.util.Scanner;

public class Tictactoe {

    static String[] board = new String[9];
    static String turn = "X";
    static int draw = 0;
    static int[] combi = new int[3];
    static int[][] winningCombinations = {
        {0, 1, 2}, // Top row
        {3, 4, 5}, // Middle row
        {6, 7, 8}, // Bottom row
        {0, 3, 6}, // Left column
        {1, 4, 7}, // Middle column
        {2, 5, 8}, // Right column
        {0, 4, 8}, // Diagonal from top left to bottom right
        {2, 4, 6} // Diagonal from top right to bottom left
    };

    static void start() {
        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }
        showBoard();

        while (draw != 9) {
            if (isXDone() || isODone()) {
                if (isXDone()) {
                    System.out.printf("X wins! Winning Combination is [%s, %s, %s]\n", combi[0], combi[1], combi[2]);
                } else {
                    System.out.printf("O wins! Winning Combination is [%s, %s, %s]\n", combi[0], combi[1], combi[2]);
                }

                for (int i = 0; i < 9; i++) {
                    board[i] = String.valueOf(i + 1);
                }
                showBoard();
                draw = 0;
            }
            int slot = showInputToBoard();
            if (slot >= 10 || slot <= 0) {
                clearScreen();
                System.out.println("Please Enter Valid Slot");
                showBoard();
            } else {
                if (draw == 8) {
                    clearScreen();
                    System.out.println("DRAW!\n");
                    for (int i = 0; i < 9; i++) {
                        board[i] = String.valueOf(i + 1);
                    }
                    showBoard();
                    draw = 0;
                } else if (board[slot - 1].equals("X") || board[slot - 1].equals("O")) {
                    clearScreen();
                    System.out.println("Please Select Other Slot");
                    showBoard();
                } else {
                    board[slot - 1] = turn;
                    turn = changePlayer(turn);
                    draw++;
                    clearScreen();
                    showBoard();
                }
            }
        }
    }

    static boolean isXDone() {
        boolean done = false;
        for (int i = 0; i < winningCombinations.length; i++) {
            for (int j = 0; j < 1; j++) {
                if (board[winningCombinations[i][j]].equals("X") && board[winningCombinations[i][j + 1]].equals("X") && board[winningCombinations[i][j + 2]].equals("X")) {
                    done = true;
                    combi[j] = winningCombinations[i][j] + 1;
                    combi[j + 1] = winningCombinations[i][j + 1] + 1;
                    combi[j + 2] = winningCombinations[i][j + 2] + 1;
                }
            }
        }
        return done;
    }

    static boolean isODone() {
        boolean done = false;
        for (int i = 0; i < winningCombinations.length; i++) {
            for (int j = 0; j < 1; j++) {
                if (board[winningCombinations[i][j]].equals("O") && board[winningCombinations[i][j + 1]].equals("O") && board[winningCombinations[i][j + 2]].equals("O")) {
                    done = true;
                    combi[j] = winningCombinations[i][j] + 1;
                    combi[j + 1] = winningCombinations[i][j + 1] + 1;
                    combi[j + 2] = winningCombinations[i][j + 2] + 1;
                }
            }
        }
        return done;
    }

    static int showInputToBoard() {
        Scanner s = new Scanner(System.in);
        System.out.printf("%s's TURN - Enter Slot Number: ", turn);
        int slot = s.nextInt();

        return slot;
    }

    private static void showBoard() {
        String line = "-------------";
        System.out.println(line);
        for (int i = 0; i < 9; i += 3) {
            System.out.printf("| %s | %s | %s |\n", board[i], board[i + 1], board[i + 2]);
            System.out.println(line);
        }

    }

    public static void main(String[] args) {
        start();
    }

    //not working on some environment
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static String changePlayer(String turn) {
        return (turn.equals("X")) ? "O" : "X";
    }

}
