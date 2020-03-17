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
        assertEquals(-1, ttt.write(3, 0, Cell.O));
        assertEquals(-1, ttt.write(0, 3, Cell.O));
    }

    @Test
    public void findMaxLength() {
        TicTacToe ttt = new TicTacToe(3);
        int[] expected = new int[5];
        
        expected = new int[] {0, 1, 1, 1, 1};
        assertArrayEquals(expected, ttt.findMaxLength(Cell.O));
    

    }
}
