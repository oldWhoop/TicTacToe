package is.ru.TicTacToe;
import java.util.Arrays;

public class Game {

	public Board gameBoard;
	private int round = 0;
	private int player = 1;
	private int MAXROUND = 9;

	// Constructor
	public Game() {
		gameBoard = new Board();
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
		if (round == MAXROUND) {
			round = 0;
		}
		player = round%2 + 1;
	}

	// Get player number
	public int getPlayer() {
		return player;
	}

	// Mark square for player
	public void mark(int n, int val) {
		gameBoard.set(n, val);
		nextRound();
	}
}