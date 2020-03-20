package icc.stud.kotov_av.tictac;

import java.util.ArrayList;

public class TicTacToe {
    
    private Cell[][] field;
    private final int size;

    /**
     * @param size The size of the field
     */
    public TicTacToe(int size) {
        this.size = size;
        field = new Cell[size][size];
        
        clearAll();
    }

    /**
     * Writes X or Y to the specified cell
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param type Recordable condition
     * @return Notifies of successful (0) or unsuccessful (-1) completion of the record
     */ 
    public int write(int x, int y, Cell type) {
        if ((x < 0 || x >= size) || (y < 0 || y >= size)) return -1;

        if (field[x][y] == Cell.EMPTY && (type != Cell.O || type != Cell.X)) {
            field[x][y] = type;
            return 0;
        } else return -1;
    }

    /**
     * Clearing the specified cell
     * 
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void clear(int x, int y) {
	    field[x][y] = Cell.EMPTY;
    }

    /**
     * Clearing all cells
     */
    public void clearAll() {
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                field[i][j] = Cell.EMPTY;
    }

    /**
     * Finds the maximum length of a sequence of values of the specified type
     * 
     * @param type Type of cells to search for
     * @return Array: maximum length of the sequence, coordinates of the beginning and end of the sequence
     */
    public int[] findMaxLength(Cell type) {
        int[] result = new int[5];
        int[] buffer = new int[5];
        result[0] = 0; // the maximum length of the sequence
        result[1] = 0; // X coordinate of the beginning of the sequence
        result[2] = 0; // Y coordinate of the beginning of the sequence
        result[3] = 0; // X coordinate of the end of the sequence
        result[4] = 0; // Y coordinate of the end of the sequence

        // строки
        for (int y = 0; y < size; y++) {
            buffer = lineCheck(type, 0, y, 1, 0);
            if (buffer[0] > result[0]) {
                result[0] = buffer[0];
                result[1] = buffer[1];
                result[2] = buffer[2];
                result[3] = buffer[3];
                result[4] = buffer[4];
            }
        }

        // столбцы
        for (int x = 0; x < size; x++) {
            buffer = lineCheck(type, x, 0, 0, 1);
            if (buffer[0] > result[0]) {
                result[0] = buffer[0];
                result[1] = buffer[1];
                result[2] = buffer[2];
                result[3] = buffer[3];
                result[4] = buffer[4];
            }
        }

        // основная диагональ
        for (int y = 0; y < size; y++) {
            buffer = lineCheck(type, 0, y, 1, 1);
            if (buffer[0] > result[0]) {
                result[0] = buffer[0];
                result[1] = buffer[1];
                result[2] = buffer[2];
                result[3] = buffer[3];
                result[4] = buffer[4];
            }
        }

        for (int x = 0; x < size; x++) {
            buffer = lineCheck(type, x, 0, 1, 1);
            if (buffer[0] > result[0]) {
                result[0] = buffer[0];
                result[1] = buffer[1];
                result[2] = buffer[2];
                result[3] = buffer[3];
                result[4] = buffer[4];
            }
        }

        // побочная диагональ
        for (int y = 0; y < size; y++) {
            buffer = lineCheck(type, (size - 1), y, -1, 1);
            if (buffer[0] > result[0]) {
                result[0] = buffer[0];
                result[1] = buffer[1];
                result[2] = buffer[2];
                result[3] = buffer[3];
                result[4] = buffer[4];
            }
        }

        for (int x = 0; x < size; x++) {
            buffer = lineCheck(type, x, 0, -1, 1);
            if (buffer[0] > result[0]) {
                result[0] = buffer[0];
                result[1] = buffer[1];
                result[2] = buffer[2];
                result[3] = buffer[3];
                result[4] = buffer[4];
            }
        }
        
        return result;
    }

    private int[] lineCheck(Cell type, int x0, int y0, int dx, int dy) {
        int[] result = new int[5];

        ArrayList<Sequence> sequences = new ArrayList<Sequence>();

        result[0] = 0;
        
        result[1] = x0;
        result[2] = y0;

        result[3] = y0;
        result[4] = y0;

        boolean isStart = true;

        int x = x0;
        int y = y0;

        while ((x >= 0 && x < size) && (y >= 0 && y < size)) {
            if (field[x][y].equals(type)) {
                result[0] += 1;
                if (isStart) {
                    result[1] = x;
                    result[2] = y;

                    isStart = false;
                } else {
                    result[3] = x;
                    result[4] = y;
                }
            } else {
                Sequence sequence = new Sequence(result[0], result[1], result[2], result[3], result[4]);
                sequences.add(sequence);

                result[0] = 0;
                result[1] = 0;
                result[2] = 0;
                result[3] = 0;
                result[4] = 0;

                isStart = true;
            }

            x += dx;
            y += dy;
        }

        for (int i = 0; i < sequences.size(); i++) {
            if (sequences.get(i).getMaxLength() > result[0]) {
                result = sequences.get(i).getData();
            }
        }

        return result;
    }

    private class Sequence {
        private int maxLength;
        
        private int xStart;
        private int yStart;

        private int xEnd;
        private int yEnd;

        public Sequence( int maxLength, int xStart, int yStart, int xEnd, int yEnd) {
            this.maxLength = maxLength;
        
            this.xStart = xStart;
            this.yStart = yStart;
    
            this.xEnd = xEnd;
            this.yEnd = yEnd;
        }

        public int[] getData() {
            int[] data = new int[5];

            data[0] = this.maxLength;
            data[1] = this.xStart;
            data[2] = this.yStart;
            data[3] = this.xEnd;
            data[4] = this.yEnd;

            return data;
        }

        public int getMaxLength() {
            return this.maxLength;
        }
    }
}