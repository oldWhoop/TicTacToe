package is.ru.TicTacToe;
import java.util.Arrays;

public class Board {

	private int[] gameBoard;
	private int dim;
	private int dimsq;

	public Board() {
		gameBoard = new int[9];
		dim = 3;
		dimsq = 9;
		Arrays.fill(gameBoard, 0);
	}
	
	public Board(int dimension) {
		gameBoard = new int[dimension*dimension];
		dim = dimension;
		dimsq = dim*dim;
		Arrays.fill(gameBoard, 0);
	}

	public void clearBoard() {
		for (int i = 0; i < gameBoard.length; i++) {
			this.set(i, 0);
		}
	}

	public int get(int arrI) {
		return gameBoard[arrI];
	}

	public void set(int arrI, int val) {
		gameBoard[arrI] = val;
	}

	public int getDim() {
		return dim;
	}

	public int getDSQ() {
		return dimsq;
	}
}