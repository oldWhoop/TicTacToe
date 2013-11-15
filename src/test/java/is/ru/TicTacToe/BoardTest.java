package is.ru.TicTacToe;
import static org.junit.Assert.assertEquals;


import org.junit.Test;


public class BoardTest {
	Board b = new Board();

    @Test
    public void testBoardArray() {
    	int counter = 0;
    	for (int i = 0; i < 9; i++) {
    		assertEquals(0, b.get(i));
    	}
    }

    @Test
    public void testSetGet() {
    	int n = 4;
    	int val = 3;
    	b.set(n, val);
    	assertEquals(val, b.get(n));
    }

    @Test
    public void testClearBoard() {
    	b.set(3, 4);
    	b.clearBoard();
    	testBoardArray();
    }



}