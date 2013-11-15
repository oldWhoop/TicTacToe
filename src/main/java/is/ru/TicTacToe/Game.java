package is.ru.TicTacToe;
//import java.util.Arrays;
import java.lang.Math;

public class Game {

	public Board gameBoard;
	private int round = 0;
	private int player = 1;
	private int dim;	// Dimension of gameboard
	private int dimsq;	// Dimension squared

	// Constructor
	public Game(int dimension) {
		gameBoard = new Board(dimension);
		dim = dimension;
		dimsq = dimension*dimension;
	}

	public int getRound() {
		return round;
	}

	// New round, clears the board
	public void newRound() {
		round = 0;
		player = 1;
		gameBoard.clearBoard();
	}

	// Advance the game
	public void nextRound() {
		round++;
		if (round == dimsq) {
			round = 0;
		}
		player = round%2 + 1;
	}

	// Get player number
	public int getPlayer() {
		return player;
	}

	// Mark square for player
	public void mark(int n) {
		if (gameBoard.get(n) == 0) {
			gameBoard.set(n, player);
		}
		
	}

	public int isWinner(int n) {
		int result = 0;
		if (horizontalWin(n) || verticalWin(n) || diagonalWin(n)) {
			return player;
		}
		if (round == dimsq - 1) {
			// Tie!
			return 3;		// 3 means tie.
		}
		return 0;
	}

	public boolean horizontalWin(int n) {
		// Test for horizontal win
		int count = 0;
		int i = n - n%dim;
		int end = i + dim;
		for (; i < end; i++) {
			if (gameBoard.get(i) == getPlayer()) {
				count++;
			}
		}
		if (count == dim) {
			return true;
		}
		return false;
	}

	public boolean verticalWin(int n) {
		// Test for vertical row
		int count = 0;
		int start = n;
		// We need to determine the starting point for the loop
		while (start >= dim) {
			start -= dim;
		}
		for (; start < dimsq; start += dim) {
			if (gameBoard.get(start) == getPlayer()) {
				count++;
			}
		}
		if (count == dim) {
			return true;
		}
		return false;
	}

	public boolean diagonalWin(int n) {
		// Since we're lazy programmers, we always try and find diagonal rows
		// From the left corner down
		int count = 0;
		for (int i = 0; i < dimsq; i += dim+1) {
			if (gameBoard.get(i) == getPlayer()) {
				count++;
			}
		}
		if (count == dim) {
			return true;
		}
		count = 0;
		// From the right corner down
		for (int i = dim-1; i < dimsq; i += dim-1) {
			if (gameBoard.get(i) == getPlayer()) {
				count++;
			}
		}
		if (count == dim) {
			return true;
		}
		return false;
	}

}