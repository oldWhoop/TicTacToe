package is.ru.TicTacToe;
import static org.junit.Assert.assertEquals;


import org.junit.Test;


public class GameTest {
	int dim = 5;
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
		for (int i = 0; i < dsq; i++) {
			g.nextRound();
		}
		assertEquals(0, g.getRound());
		assertEquals(1, g.getPlayer());
	}

	@Test
	public void testMark() {
		boolean markd = g.mark(4);
		assertEquals(1, g.gameBoard.get(4));
		g.nextRound();
		assertEquals(1, g.getRound());
		assertEquals(2, g.getPlayer());
		// Now test if we can mark the same square again
		markd = g.mark(4);
		// Nothing should change
		assertEquals(1, g.gameBoard.get(4));
		// Then if we move forward if we finally mark an empty square
		markd = g.mark(5);
		// The game should advance
		assertEquals(2, g.gameBoard.get(5));
	}

	@Test
	public void testNewRound() {
		boolean markd = false;
		for (int i = 0; i < dsq; i++) {
			markd = g.mark(i);
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
		// Test for horizontal win
		boolean markd = false;
		for (int i = 0; i < dim; i++) {
			markd = g.mark(i);
		}
		for (int i = 0; i < dim; i++) {
			assertEquals(true, g.horizontalWin(i));
		}
	}

	@Test
	public void testVerticalWin() {
		// Test for vertical win
		boolean markd = false;
		for (int i = 0; i < dsq; i += dim) {
			markd = g.mark(i);
		}
		for (int i = 0; i < dsq; i += dim) {
			assertEquals(true, g.verticalWin(i));
		}
	}

	@Test
	public void testDiagonalWin() {
		boolean markd = false;
		// Test for diagonal win from left
		for (int i = 0; i < dsq; i += dim+1) {
			markd = g.mark(i);
		}
		for (int i = 0; i < dsq; i += dim+1) {
			assertEquals(true, g.diagonalWin(i));
		}
		g.newRound();
		
		// And from right
		for (int i = dim-1; i < dsq; i += dim-1) {
			markd = g.mark(i);
		}
		for (int i = dim-1; i < dsq; i += dim-1) {
			assertEquals(1, g.gameBoard.get(i));
			//assertEquals(true, g.diagonalWin(i));
		}
	}

	@Test
	public void testIsWinner() {
		boolean markd = false;
		for (int i = 0; i < dim; i++) {
			markd = g.mark(i);
		}
		for (int i = 0; i < dim; i++) {
			assertEquals(1, g.isWinner(i));
		}
		g.newRound();
		// Now for player 2
		markd = g.mark(dim);
		g.nextRound();
		for (int i = 0; i < dim; i++) {
			markd = g.mark(i);
		}
		for (int i = 0; i < dim; i++) {
			assertEquals(2, g.isWinner(i));
		}
	}

	@Test
	public void testIsTie() {
		boolean markd = g.mark(1);
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

	@Test
	public void testNrWins() {
		//TODO
		assertEquals(1,1);
	}


}