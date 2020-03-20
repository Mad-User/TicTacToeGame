package icc.stud.kotov_av.tictac;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.*;

public class TestTicTacToe {
    
    @Test
    public void write() {
        final TicTacToe ttt = new TicTacToe(3);

        assertEquals(0, ttt.write(0, 0, Cell.O));
        assertEquals(-1, ttt.write(0, 0, Cell.X));
        assertEquals(-1, ttt.write(-1, 0, Cell.O));
        assertEquals(-1, ttt.write(0, -1, Cell.O));
        assertEquals(-1, ttt.write(3, 0, Cell.X));
        assertEquals(-1, ttt.write(0, 3, Cell.O));
    }

    @Test 
    public void findMaxLength() {
        TicTacToe tObj = new TicTacToe(5);
        int[] expected = new int[5];
        
        expected = new int[] {0, 0, 0, 0, 0};
        assertArrayEquals(expected, tObj.findMaxLength(Cell.O));
        assertArrayEquals(expected, tObj.findMaxLength(Cell.X));

        expected = new int[] {5, 0, 0, 4, 0};
        assertArrayEquals(expected, tObj.findMaxLength(Cell.EMPTY));

        tObj.write(0, 0, Cell.O);
        tObj.write(0, 1, Cell.O);
        tObj.write(1, 0, Cell.X);
        tObj.write(2, 0, Cell.X);
        expected = new int[] {2, 0, 0, 0, 1};
        assertArrayEquals(expected, tObj.findMaxLength(Cell.O));
        expected = new int[] {2, 1, 0, 2, 0};
        assertArrayEquals(expected, tObj.findMaxLength(Cell.X));
        tObj.clearAll();

    }
}