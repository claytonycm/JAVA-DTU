package Assignment3;
import java.util.InputMismatchException;
import java.util.Scanner;

class Position {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isOnBoard() {
        return (x >= 0 && x <= 7 && y >= 0 && y <= 7);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}

class Player {
    public static int turn = 1;

    public static int turnOfPlayer() {
        return turn;
    }
}

class Board {
    public static int length = 8;// vertical
    public static int width = 20;// horizontal
    public static char[][] board = new char[length][width]; // 12x20, 2D-array

    public static void printInitialBoard() {

        System.out.println("Enter 9 to end the game");
        System.out.println("   0 1 2 3 4 5 6 7   <-X axis");
        System.out.println("  +----------------+");

        // Loop 2D- array
        for (int row = 0; row < length; row++) {
            System.out.print(row + " |");
            for (int col = 3; col < width - 1; col++) {
                board[row][col] = 0;
                // 1
                if (row == 0 && col % 4 == 1) {
                    board[row][col] = 1;
                }
                if (row == 1 && (2 + col) % 4 == 1) {
                    board[row][col] = 1;
                }
                if (row == 2 && col % 4 == 1) {
                    board[row][col] = 1;
                }
                // 2
                if (row == 5 && (2 + col) % 4 == 1) {
                    board[row][col] = 2;
                }
                if (row == 6 && col % 4 == 1) {
                    board[row][col] = 2;
                }
                if (row == 7 && (2 + col) % 4 == 1) {
                    board[row][col] = 2;
                }
                // Printing 1 & 2
                if (board[row][col] == 0) {
                    System.out.print(" ");
                }
                if (board[row][col] == 1) {
                    System.out.print("1");
                }
                if (board[row][col] == 2) {
                    System.out.print("2");
                }
            }
            System.out.println("|  ");
        }
        // Tail
        System.out.println("  +----------------+");
        System.out.println("   0 1 2 3 4 5 6 7  ");
    }

    public static void printboard() {

        System.out.println("Enter 9 to end the game");
        System.out.println("   0 1 2 3 4 5 6 7   <-X axis");
        System.out.println("  +----------------+");

        // Loop 2D- array
        for (int row = 0; row < length; row++) {
            System.out.print(row + " |");
            for (int col = 3; col < width - 1; col++) {
                // Printing
                if (board[row][col] == 0) {
                    System.out.print(" ");
                }
                if (board[row][col] == 1) {
                    System.out.print("1");
                }
                if (board[row][col] == 2) {
                    System.out.print("2");
                }
            }
            System.out.println("|  ");
        }
        // Tail
        System.out.println("  +----------------+");
        System.out.println("   0 1 2 3 4 5 6 7  ");
    }
}

abstract class AbstractPiece extends Board {

    public static int checkTurn() {

        int turn = 0;

        if (Player.turn % 2 == 1) {
            turn = 1;
            System.out.println("Turn of player no. " + turn);
        }

        else {
            turn = 2;
            System.out.println("Turn of player no. " + turn);
        }

        System.out.println("Coordinate of piece to move");
        return turn;
    }
    
    public static boolean move(Position newPosition, Position PositionNow) {
        int x = PositionNow.x();
        int y = PositionNow.y();
        int newX = newPosition.x();
        int newY = newPosition.y();

        if (isValidPosition(newPosition, PositionNow)) {
            if (Player.turn % 2 == 1) {
                board[y][2 * x + 3] = 0;
                board[newY][2 * newX + 3] = 1;
            } else {
                board[y][2 * x + 3] = 0;
                board[newY][2 * newX + 3] = 2;
            }
            System.out.println("Piece moved!");
            return true;

        } else {
            return false;
        }
    }

    public static boolean checkPieceOwner(Position PositionNow) {

        int x = PositionNow.x();
        int y = PositionNow.y();

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Input out of range! Please enter 0-7.");
            return false;

        }
        if (Player.turn % 2 == 1) {
            if (board[y][2 * x + 3] == 1) {
                return true;
            } else {
                System.out.println("It is not yours");
            }

        }

        else {
            if (board[y][2 * x + 3] == 2) {
                return true;
            } else {
                System.out.println("It is not yours");
            }

        }
        return false;
    }

    public static boolean isValidPosition(Position newPosition, Position PositionNow) {
        int x = PositionNow.x();
        int y = PositionNow.y();
        int newX = newPosition.x();
        int newY = newPosition.y();

        if (newX < 0 || newY > 7 || newY < 0 || newY > 7) {
            System.out.println("Input out of range! Please enter 0-7.");
            return false;
        }
        if (Player.turn % 2 == 1) {
            if (newPosition.isOnBoard()) {

                if (Board.board[newY][2 * newX + 3] == 0 && (newY == y + 1 && (newX == x + 1 || newX == x - 1))) {
                    return true;
                } else { // wrong position
                    System.out.println("Something goes wrong!! Please try again.");
                    return false;
                }
            }

        }

        else {
            if (newPosition.isOnBoard()) {

                if (Board.board[newY][2 * newX + 3] == 0 && (newY == y - 1 && (newX == x + 1 || newX == x - 1))) {
                    return true;
                } else { // wrong position
                    System.out.println("Something goes wrong!! Please try again.");
                    return false;
                }
            }
        }
        return false;

    }

    
}

public class Checker {

    public static boolean end;

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        Scanner reader = new Scanner(System.in);
        Board.printInitialBoard();

        Loop: while (end == false) {

            AbstractPiece.checkTurn();

            int x = 0;
            int y = 0;
            int newX = 0;
            int newY = 0;

            boolean success1 = false;
            boolean success2 = false;

            while (!success1) {
                try {
                    System.out.print("Enter X: ");
                    x = reader.nextInt();
                    System.out.print("Enter Y: ");
                    y = reader.nextInt();
                    success1 = true;
                } catch (InputMismatchException e1) {
                    System.out.println("Wrong input type! Please enter 0-7.");
                    reader.next();
                    success1 = false;
                }

                // End game
                if ((x == 9) || (y == 9)) {
                    System.out.println("The game is ended. Thank you for playing!");
                    end = true;
                    System.exit(0);
                }

                Position PositionNow = new Position(x, y);

                if (AbstractPiece.checkPieceOwner(PositionNow) == false) {
                    continue Loop;
                }

                System.out.println("Coordinate of new position");

                while (!success2) {
                    try {
                        System.out.print("Enter X: ");
                        newX = reader.nextInt();
                        System.out.print("Enter Y: ");
                        newY = reader.nextInt();
                        success2 = true;
                    } catch (InputMismatchException e1) {
                        System.out.println("Wrong input type! Please enter 0-7.");
                        reader.next();
                        success2 = false;
                    }

                    Position newPosition = new Position(newX, newY);

                    if (AbstractPiece.move(newPosition, PositionNow) == false) {
                        continue Loop;
                    }
                    Board.printboard();
                    Player.turn = Player.turn + 1;

                }
            }

        }

    }
}
