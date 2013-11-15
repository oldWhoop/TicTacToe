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
	}

	@Test
	public void testNextRound() {
		g.nextRound();
		assertEquals(1, g.getRound());
	}


}