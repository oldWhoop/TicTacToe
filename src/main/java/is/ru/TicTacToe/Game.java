package is.ru.TicTacToe;
//import java.util.Arrays;
import java.lang.Math;

public class Game {

	public Board gameBoard;
	private int round = 0;
	private int player = 1;
	private int[] playerWins;

	// Default constructor
	public Game() {
		gameBoard = new Board(); // Calls default Board constructor
		playerWins = new int[] {0, 0, 0};
	}

	// Constructor, if we want to go crazy
	/* // We don't use it now, only if we want to expand the game
	public Game(int dimension) {
		gameBoard = new Board(dimension);
		playerWins = new int[] {0, 0, 0};
	}*/

	// Function that handles one turn
	public int oneTurn(int n) {
		int winner = 0;
		if (mark(n)) {
			// No need to check for winners unless there have been enough rounds
			if(getRound() >= (2*gameBoard.getDim()- 2)) {
				winner = isWinner(n);
			}
			if (winner == 0) nextRound();
			else newRound();
		}		
		return winner;
	}

	// Gets current round - SUCCESSFULLY TESTED!
	public int getRound() {
		return round;
	}

	// New round, clears the board - SUCCESSFULLY TESTED!
	public void newRound() {
		// TODO: implement winner of last round starts next
		// then make a test for it.
		round = 0;
		player = 1;
		gameBoard.clearBoard();
	}

	// Advance the game - SUCCESSFULLY TESTED!
	public void nextRound() {
		round++;
		if (round == gameBoard.getDSQ()) {
			newRound();
		}
		else {
			player = round%2 + 1;
		}	
	}

	// Get current player number - SUCCESSFULLY TESTED!
	public int getPlayer() {
		return player;
	}

	// Mark square for player, returns true if success - SUCCESSFULLY TESTED!
	public boolean mark(int n) {
		if (gameBoard.get(n) == 0) {
			gameBoard.set(n, player);
			return true;
		}
		return false;
	}

	// Finds any winning situation - SUCCESSFULLY TESTED!
	public int isWinner(int n) {
		int result = 0;
		if (horizontalWin(n) || verticalWin(n) || diagonalWin(n)) {
			return player;
		}
		if (round == gameBoard.getDSQ() - 1) {
			// Tie!
			return 3;		// 3 means tie.
		}
		return 0;	// No winner
	}

	// Finds horizontal wins - SUCCESSFULLY TESTED!
	public boolean horizontalWin(int n) {
		// Test for horizontal win
		int count = 0;
		int i = n - n%gameBoard.getDim();
		int end = i + gameBoard.getDim();
		for (; i < end; i++) {
			if (gameBoard.get(i) == getPlayer()) {
				count++;
			}
		}
		if (count == gameBoard.getDim()) {
			addWin();
			return true;
		}
		return false;
	}

	// Finds vertical wins - SUCCESSFULLY TESTED!
	public boolean verticalWin(int n) {
		// Test for vertical row
		int count = 0;
		int start = n;
		// We need to determine the starting point for the loop
		while (start >= gameBoard.getDim()) {
			start -= gameBoard.getDim();
		}
		for (; start < gameBoard.getDSQ(); start += gameBoard.getDim()) {
			if (gameBoard.get(start) == getPlayer()) {
				count++;
			}
		}
		if (count == gameBoard.getDim()) {
			addWin();
			return true;
		}
		return false;
	}

	// Findds diagonal wins - SUCCESSFULLY TESTED!
	public boolean diagonalWin(int n) {
		// From the left corner down
		int count = 0;
		for (int i = 0; i < gameBoard.getDSQ(); i += gameBoard.getDim()+1) {
			if (gameBoard.get(i) == getPlayer()) {
				count++;
			}
		}
		if (count == gameBoard.getDim()) {
			addWin();
			return true;
		}
		count = 0;
		// From the right corner down
		for (int i = gameBoard.getDim()-1; i < gameBoard.getDSQ() - 1; i += gameBoard.getDim()-1) {
			if (gameBoard.get(i) == getPlayer()) {
				count++;
			}
		}
		if (count == gameBoard.getDim()) {
			addWin();
			return true;
		}
		return false;
	}

	// Adds win to current player - SUCCESSFULLY TESTED!
	public void addWin() {
		playerWins[player]++;
	}

	// Gets the nr of wins of players - SUCCESSFULLY TESTED!
	public int getWins(int playa) {
		return playerWins[playa];
	}

	public void resetWins() {
		playerWins[1] = 0;
		playerWins[2] = 0;
	}
}