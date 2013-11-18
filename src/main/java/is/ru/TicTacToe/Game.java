package is.ru.TicTacToe;
//import java.util.Arrays;
import java.lang.Math;

public class Game {

	public Board gameBoard;
	private int round = 0;
	private int player = 1;
	private int[] playerWins;
	private int dim;	// Dimension of gameboard
	private int dimsq;	// Dimension squared

	// Constructor
	public Game(int dimension) {
		gameBoard = new Board(dimension);
		dim = dimension;
		dimsq = dimension*dimension;
		playerWins = new int[] {0, 0};
	}

	public int oneTurn(int n) {
		int winner = 0;
		if (mark(n)) {
			// No need to check for winners unless there have been enough rounds
			if(getRound() > (2*dim - 2)) {
			winner = isWinner(n);
			}
			nextRound();
		}		
		return winner;
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
			newRound();
		}
		else {
			player = round%2 + 1;
		}	
	}

	// Get player number
	public int getPlayer() {
		return player;
	}

	// Mark square for player
	public boolean mark(int n) {
		if (gameBoard.get(n) == 0) {
			gameBoard.set(n, player);
			return true;
		}
		return false;
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
		return 0;	// No winner
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
			addWin();
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
			addWin();
			return true;
		}
		return false;
	}

	public boolean diagonalWin(int n) {
		// Find diagonal wins
		// From the left corner down
		int count = 0;
		for (int i = 0; i < dimsq; i += dim+1) {
			if (gameBoard.get(i) == getPlayer()) {
				count++;
			}
		}
		if (count == dim) {
			addWin();
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
			addWin();
			return true;
		}
		return false;
	}

	public void addWin() {
		playerWins[player-1]++;
	}

}