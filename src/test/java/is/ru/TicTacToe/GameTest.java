package is.ru.TicTacToe;
import static org.junit.Assert.assertEquals;


import org.junit.Test;


public class GameTest {
	Game g = new Game();

	@Test
	public void testGameBoard() {
    	for (int i = 0; i < 9; i++) {
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
	}

	@Test
	public void testMark() {
		int square = 4;
		g.mark(square);
		assertEquals(1, g.gameBoard.get(square));
		assertEquals(1, g.getRound());
		assertEquals(2, g.getPlayer());
		// Now test if we can mark the same square again
		g.mark(square);
		// Nothing should change
		assertEquals(1, g.gameBoard.get(square));
		assertEquals(1, g.getRound());
		assertEquals(2, g.getPlayer());
		// Then if we move forward if we finally mark an empty square
		g.mark(5);
		// The game should advance
		assertEquals(2, g.gameBoard.get(5));
		assertEquals(2, g.getRound());
		assertEquals(1, g.getPlayer());
	}

	@Test
	public void testNewRound() {
		for (int i = 0; i < 9; i++) {
			g.mark(i);
		}
		g.newRound();
		testGameBoard();
	}

	@Test
	public void testMaxRound() {
		for(int i = 0; i < 9; i++) {
			g.mark(i);
		}
		assertEquals(0, g.getRound());
		assertEquals(1, g.getPlayer());
	}


}