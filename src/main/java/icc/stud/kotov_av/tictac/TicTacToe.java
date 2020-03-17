package icc.stud.kotov_av.tictac;

import java.util.Arrays;

public class TicTacToe {
    
    private Cell[][] field;
    private final int size;

    /**
     * @param size The size of the field
     */
    public TicTacToe(int size) {
        this.size = size;
        field = new Cell[size][size];
        Arrays.fill(field, Cell.EMPTY);
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
        field = new Cell[size][size];
        Arrays.fill(field, Cell.EMPTY);
    }

    /**
     * Finds the maximum length of a sequence of values of the specified type
     * 
     * @param type Type of cells to search for
     * @return Array: maximum length of the sequence, coordinates of the beginning and end of the sequence
     */
    public int[] findMaxLength(Cell type) {
        int maxLength = 0;
        int lastMaxLength = 0;

        // search by lines
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == type) lastMaxLength++;
                else {
                    if (maxLength < lastMaxLength) maxLength = lastMaxLength;
                    lastMaxLength = 0;
                }
            }
        }
        
        // search by columns
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[j][i] == type) lastMaxLength++;
                else {
                    if (maxLength < lastMaxLength) maxLength = lastMaxLength;
                    lastMaxLength = 0;
                }
            }
        }

        // search by diagonals, parallel to the main diagonal
        for (int x = 0; x < size; x++) {
            
        }

        // search by diagonals, parallel to the side diagonal
        for (int x = (size - 1); x >= 0; x--) {
            
        }

        int[] result = new int[5];
        result[0] = maxLength; // the maximum length of the sequence
        result[1] = 1; // X coordinate of the beginning of the sequence
        result[2] = 1; // Y coordinate of the beginning of the sequence
        result[3] = 1; // X coordinate of the end of the sequence
        result[4] = 1; // Y coordinate of the end of the sequence
        
        return result;
    }
}