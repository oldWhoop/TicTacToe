package is.ru.TicTacToe;
import static org.junit.Assert.assertEquals;


import org.junit.Test;


public class BoardTest {
	Board b = new Board();

	@Test
    public void testGetDim() {
    	assertEquals(3, b.getDim());
    }

    @Test
    public void testGetDSQ() {
    	assertEquals(9, b.getDSQ());
    }

    @Test
    public void testBoardArray() {
    	for (int i = 0; i < b.getDSQ(); i++) {
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