package icc.stud.kotov_av.tictac;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.*;

public class TestTicTacToeField {

    @Test
    public void write() {
        final TicTacToeField tObj = new TicTacToeField();

        assertEquals(0, tObj.write(0, 0, Cell.O));
        assertEquals(-1, tObj.write(0, 0, Cell.X));
        assertEquals(-1, tObj.write(-1, 0, Cell.O));
        assertEquals(-1, tObj.write(0, -1, Cell.O));
        assertEquals(-1, tObj.write(3, 0, Cell.X));
        assertEquals(-1, tObj.write(0, 3, Cell.O));
    }

    @Test
    public void findMaxLength() {
        TicTacToeField tObj = new TicTacToeField(5);
        int[] expected = new int[5];

        expected = new int[] { 0, 0, 0, 0, 0 };
        assertArrayEquals(expected, tObj.findMaxLength(Cell.O));
        assertArrayEquals(expected, tObj.findMaxLength(Cell.X));

        expected = new int[] { 5, 0, 0, 4, 0 };
        assertArrayEquals(expected, tObj.findMaxLength(Cell.EMPTY));

        tObj.write(0, 0, Cell.O);
        tObj.write(0, 1, Cell.O);
        tObj.write(1, 0, Cell.X);
        tObj.write(2, 0, Cell.X);
        expected = new int[] { 2, 0, 0, 0, 1 };
        assertArrayEquals(expected, tObj.findMaxLength(Cell.O));
        expected = new int[] { 2, 1, 0, 2, 0 };
        assertArrayEquals(expected, tObj.findMaxLength(Cell.X));
        tObj.clearAll();

        tObj.write(0, 0, Cell.O);
        tObj.write(1, 1, Cell.O);
        tObj.write(1, 0, Cell.X);
        tObj.write(2, 1, Cell.X);
        tObj.write(3, 2, Cell.X);
        expected = new int[] { 2, 0, 0, 1, 1 };
        assertArrayEquals(expected, tObj.findMaxLength(Cell.O));
        expected = new int[] { 3, 1, 0, 3, 2 };
        assertArrayEquals(expected, tObj.findMaxLength(Cell.X));
        tObj.clearAll();

        tObj.write(0, 0, Cell.O);
        tObj.write(0, 1, Cell.O);
        tObj.write(1, 1, Cell.O);
        tObj.write(1, 2, Cell.O);
        tObj.write(2, 3, Cell.O);
        tObj.write(1, 0, Cell.X);
        tObj.write(2, 0, Cell.X);
        tObj.write(3, 0, Cell.X);
        tObj.write(4, 0, Cell.X);
        tObj.write(3, 1, Cell.X);
        tObj.write(2, 2, Cell.X);
        tObj.write(1, 3, Cell.X);
        tObj.write(0, 4, Cell.X);
        expected = new int[] { 3, 0, 1, 2, 3 };
        assertArrayEquals(expected, tObj.findMaxLength(Cell.O));
        expected = new int[] { 5, 4, 0, 0, 4 };
        assertArrayEquals(expected, tObj.findMaxLength(Cell.X));
        tObj.clearAll();
    }

    @Test 
    public void randomFindWinner() {
        TicTacToeField tObj = new TicTacToeField(5);

        java.util.Random rand = new java.util.Random();
        int countCells = 0;
        Cell type = Cell.X;

        while (countCells != 25) {
            while (tObj.write(rand.nextInt(5), rand.nextInt(5), type) != 0) {}
            
            if (type == Cell.O) 
                type = Cell.X; 
            else 
                type = Cell.O;

            countCells++;
        }

        // test stability of algorithm
        assertArrayEquals(tObj.findMaxLength(Cell.O), tObj.findMaxLength(Cell.O));
        assertArrayEquals(tObj.findMaxLength(Cell.X), tObj.findMaxLength(Cell.X));
        assertArrayEquals(tObj.findMaxLength(Cell.O), tObj.findMaxLength(Cell.O));
        assertArrayEquals(tObj.findMaxLength(Cell.X), tObj.findMaxLength(Cell.X));
    }
}
