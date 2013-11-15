package is.ru.TicTacToe;
//import java.util.Arrays;
import java.lang.Math;

public class Game {

	public Board gameBoard;
	private int round = 0;
	private int player = 1;
	private int dim;
	private int dimsq;

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
			nextRound();
		}
		
	}

	public int isWinner(int n) {
		int result = 0;
		int count = 0;
		// Test for horizontal row
		int start = n - n%dim;
		int end = start + dim;
		for (; start < end; start++) {
			if (gameBoard.get(start) == getPlayer()) {
				count++;
			}
		}
		if (count == dim) {
			return player;
		}
		count = 0;

		// Test for vertical row
		start = (int)Math.floor(n/dim);
		for (; start < dimsq; start += dim) {
			if (gameBoard.get(start) == getPlayer()) {
				count++;
			}
		}
		if (count == dim) {
			return player;
		}
		count = 0;

		// Since we're lazy programmers, we always try and find diagonal rows
		// From the left corner down
		for (int i = 0; i < dimsq; i += dim+1) {
			if (gameBoard.get(i) == getPlayer()) {
				count++;
			}
		}
		if (count == dim) {
			return player;
		}
		count = 0;
		// From the right corner down
		for (int i = dim-1; i < dimsq; i += dim-1) {
			if (gameBoard.get(i) == getPlayer()) {
				count++;
			}
		}
		if (count == dim) {
			return player;
		}
		if (round == dimsq) {
			// Tie!
			return 3;		// 3 means tie.
		}
		return 0;
	}

}