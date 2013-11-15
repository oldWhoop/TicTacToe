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
		int val = 2;
		g.mark(square, val);
		assertEquals(val, g.gameBoard.get(square));
		assertEquals(1, g.getRound());
		assertEquals(2, g.getPlayer());
	}

	@Test
	public void testNewRound() {
		for (int i = 0; i < 9; i++) {
			g.mark(i, (i%2)+1);
		}
		g.newRound();
		testGameBoard();
	}

	@Test
	public void testMaxRound() {
		for(int i = 0; i < 9; i++) {
			g.mark((i), (i%2)+1);
		}
		assertEquals(0, g.getRound());
		assertEquals(1, g.getPlayer());
	}


}