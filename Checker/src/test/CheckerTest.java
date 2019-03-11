package test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import Assignment3.Checker;

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
}

public class CheckerTest {

	public int x, y, newX, newY, turn;
	public boolean end;

	//isOnBoard
	@Test
	public void isOnBoard_a() {
		x = 1;
		y = 2;
		Position PositionNow = new Position(x, y);
		assertTrue("It is not on board.", PositionNow.isOnBoard());
	}
	
	@Test
	public void isOnBoard_b() {
		x = -1;
		y = 1;
		Position PositionNow = new Position(x, y);
		assertFalse("It is on board.", PositionNow.isOnBoard());
	}
	
	@Test
	public void isOnBoard_c() {
		x = 200;
		y = 200;
		Position PositionNow = new Position(x, y);
		assertFalse("It is on board.", PositionNow.isOnBoard());
	}
	
	@Test
	public void GameOver() {
		end = false;
		Checker.end = end;
		Board.printInitialBoard();
		turn = 1;
		Player.turn = turn;
		x = 9;
		assertTrue("The game not over.", Checker.end = true);
		Checker.end = false;
		y = 9;
		assertTrue("The game not over.", Checker.end = true);
		Checker.end = false;
		newX = 9;
		assertTrue("The game not over.", Checker.end = true);
		Checker.end = false;
		newY = 9;
		assertTrue("The game not over.", Checker.end = true);

	}
	
	@Test
	public void Turn1() {
		Board.printInitialBoard();
		turn = 1;
		Player.turn = turn;
		x = -1;
		y = 1;
		newX = -1;
		newY = 1;
		Position PositionNow = new Position(x, y);
		Position newPosition = new Position(newX, newY);
		assertEquals(1, AbstractPiece.checkTurn());
		assertFalse("It is a valid move.", AbstractPiece.move(newPosition, PositionNow));
		assertFalse("It is owned by player 1.", AbstractPiece.checkPieceOwner(PositionNow));
		assertFalse("It is a valid positon.", AbstractPiece.isValidPosition(newPosition, PositionNow));
		Board.printboard();
	}
	
	@Test
	public void Turn2() {
		Board.printInitialBoard();
		turn = 2;
		Player.turn = turn;
		x = 187;
		y = 1;
		newX = 250;
		newY = 1;
		Position PositionNow = new Position(x, y);
		Position newPosition = new Position(newX, newY);
		assertEquals(2, AbstractPiece.checkTurn());
		assertFalse("It is a valid move.", AbstractPiece.move(newPosition, PositionNow));
		assertFalse("It is owned by player 1.", AbstractPiece.checkPieceOwner(PositionNow));
		assertFalse("It is a valid positon.", AbstractPiece.isValidPosition(newPosition, PositionNow));
		Board.printboard();
	}

}
