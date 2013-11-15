package is.ru.TicTacToe;
import java.util.Arrays;

public class Game {

	public Board gameBoard;
	private int round = 0;

	public Game() {
		gameBoard = new Board();
	}

	public void mark(int n, int val) {
		gameBoard.set(n, val);
		nextRound();
	}

	public int getRound() {
		return round;
	}

	public void nextRound() {
		round++;
	}

	public void newRound() {
		round = 0;
		gameBoard.clearBoard();
	}


}