package is.ru.TicTacToe;
import static org.junit.Assert.assertEquals;


import org.junit.Test;


public class GameTest {
	int dim = 3;
	int dsq = dim*dim;
	Game g = new Game(dim);

	@Test
	public void testGameBoard() {
    	for (int i = 0; i < dsq; i++) {
    		assertEquals(0, g.gameBoard.get(i));
    	}
    	assertEquals(0, g.getRound());
    	assertEquals(1, g.getPlayer());
	}

	@Test
	public void testNextRound() {
		g.nextRound();
		assertEquals(1, g.getRound());
		assertEquals(2, g.getPlayer());
		g.nextRound();
		assertEquals(2, g.getRound());
		assertEquals(1, g.getPlayer());
		g.nextRound();
		assertEquals(3, g.getRound());
		assertEquals(2, g.getPlayer());
		
	}

	@Test
	public void testLastRound() {
		for (int i = 0; i < 9; i++) {
			g.nextRound();
		}
		assertEquals(0, g.getRound());
		assertEquals(1, g.getPlayer());
	}

	@Test
	public void testMark() {
		g.mark(4);
		assertEquals(1, g.gameBoard.get(4));
		g.nextRound();
		assertEquals(1, g.getRound());
		assertEquals(2, g.getPlayer());
		// Now test if we can mark the same square again
		g.mark(4);
		// Nothing should change
		assertEquals(1, g.gameBoard.get(4));
		// Then if we move forward if we finally mark an empty square
		g.mark(5);
		// The game should advance
		assertEquals(2, g.gameBoard.get(5));
	}

	@Test
	public void testNewRound() {
		for (int i = 0; i < dsq; i++) {
			g.mark(i);
			g.nextRound();
		}
		g.newRound();
		testGameBoard();
	}

	@Test
	public void testMaxRound() {

		for(int i = 0; i < dsq; i++) {
			g.nextRound();
		}
		assertEquals(0, g.getRound());
		assertEquals(1, g.getPlayer());
	}

	@Test
	public void testHorizontalWin() {
		// Test for diagonal win from left
		g.mark(6);
		g.mark(8);
		g.mark(7);
		assertEquals(true, g.horizontalWin(8));
		assertEquals(true, g.horizontalWin(7));
		assertEquals(true, g.horizontalWin(6));
	}

	@Test
	public void testVerticalWin() {
		// Test for diagonal win from left
		g.mark(0);
		g.mark(3);
		g.mark(6);
		assertEquals(true, g.verticalWin(6));
		assertEquals(true, g.verticalWin(0));
		assertEquals(true, g.verticalWin(3));
	}

	@Test
	public void testDiagonalWin() {
		// Test for diagonal win from left
		g.mark(0);
		g.mark(4);
		g.mark(8);
		assertEquals(true, g.diagonalWin(8));
		assertEquals(true, g.diagonalWin(0));
		assertEquals(true, g.diagonalWin(4));
		g.newRound();
		// And from right
		g.mark(2);
		g.mark(4);
		g.mark(6);
		assertEquals(true, g.diagonalWin(2));
		assertEquals(true, g.diagonalWin(6));
		assertEquals(true, g.diagonalWin(4));
		g.newRound();
	}

	@Test
	public void testIsWinner() {
		g.mark(2);
		g.mark(4);
		g.mark(6);
		assertEquals(1, g.isWinner(6));
		g.newRound();
		g.mark(4);
		g.nextRound();
		g.mark(1);
		g.mark(2);
		g.mark(0);
		assertEquals(2, g.isWinner(0));
	}

	@Test
	public void testIsTie() {
		g.mark(1);
		for (int i = 0; i < dsq - 1; i++) {
			g.nextRound();
		}
		assertEquals(3, g.isWinner(8));
	}

	@Test
	public void testOneTurn() {
		int r = 0;
		for (int i = 0; i < dsq; i++) {
			r = g.oneTurn(i);
		}
		assertEquals(1, r);
		assertEquals(0, g.getRound());
		assertEquals(0, g.gameBoard.get(4));
	}



}