import java.util.Scanner;

/**
 * @filename - Game.java
 * @description - This is the entry point of program
 * @author - Arman Agrawal
 */


public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MainGame board = new MainGame();
        board.initBoard();
        boolean gameOver = false;
         int totalMoves = 0;
        int leftMoves = 0, rightMoves = 0, upMoves = 0, downMoves = 0;
        while (!gameOver) {

            board.printBoard();
            System.out.println("Score: " + board.getScore());
            gameOver = board.isGameOver();
            if (gameOver) {
                System.out.println("Game Over!");
                System.out.println("Total Moves: " + totalMoves + " | L: " + leftMoves + " R: " + rightMoves + " U: " + upMoves + " D: " + downMoves);
                break;
            }
            if (board.getMaxTile() == 2048) {
                board.printBoard();
                System.out.println("🎉 You Win! 🎉");
                System.out.println("Total Moves: " + totalMoves + " | L: " + leftMoves + " R: " + rightMoves + " U: " + upMoves + " D: " + downMoves);
                break;
            }
            char move = ' ';
            boolean valid = false;

            while (!valid) {
                System.out.print("Move (W/A/S/D): ");
                String input = sc.nextLine();

                if (input.length() == 1) {
                    move = input.charAt(0);
                    if (move == 'W' || move == 'A' || move == 'S' || move == 'D' ||
                            move == 'w' || move == 'a' || move == 's' || move == 'd') {
                        valid = true;
                    } else {
                        System.out.println("Invalid Choice. Please enter W, A, S, or D.");
                    }
                } else {
                    System.out.println("Invalid Choice. Enter only a single character.");
                }
            }
            boolean moved = false;
            move = Character.toUpperCase(move);

            switch (move) {

                case 'A':
                    moved = board.shiftBoard(0);
                    if(moved)leftMoves++;
                    break;
                case 'D':
                    moved = board.shiftBoard(1);
                    if(moved)rightMoves++;

                    break;
                case 'W':
                    moved = board.shiftBoard(2);
                    if(moved)upMoves++;

                    break;
                case 'S':
                    moved = board.shiftBoard(3);
                    if(moved)downMoves++;

                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
            if (moved) {
                totalMoves++;
                board.addRandomTiles();
            }
        }
        sc.close();
    }
}
