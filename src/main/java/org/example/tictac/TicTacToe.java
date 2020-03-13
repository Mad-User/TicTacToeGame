public class Tic_Tac_toe {
    // types: 0 - zero cell, 1 - X in cell, 2 - O in cell
    private int[][] field;
    private final int size;

    public Tic_Tac_toe(int size) {
        this.size = size;
        field = new int[size][size];
    }

    /*
     * Will write X or O ( by type: 1-X, 2-O ) to cell( x, y ) if cell is zero else will return error ( -1 )
     */
    public int write(int x, int y, int type) {
        if (field[x][y] == 0 && (type == 1 || type == 2)) {
            field[x][y] = type;
            return 0;
        } else return -1;
    }

    public int get(int x, int y){
        return field[x][y];
    }

    public int find_max_length(int type) {
        int max_length = 0;
        int last_length = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j] == type) last_length++;
                else {
                    if (max_length < last_length) max_length = last_length;
                    last_length = 0;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[j][i] == type) last_length++;
                else {
                    if (max_length < last_length) max_length = last_length;
                    last_length = 0;
                }
            }
        }

        for (int x = 0; x < size; x++) {
            max_length = check_line(x, type,false);
        }

        for (int x = (size - 1); x >= 0; x--) {
            max_length = check_line(x, type,true);
        }

        return max_length;
    }

    private int check_line(int x, int type, boolean invert) {
        int max_length = 0;
        int last_length = 0;

        int y = 0;
        if (!invert) {
            while (x < size && y < size) {
                if (field[x][y] == type) last_length++;
                else {
                    if (max_length < last_length) max_length = last_length;
                    last_length = 0;
                }

                x++;
                y++;
            }
        } else {
            while (x > 0 && y < size) {
                if (field[x][y] == type) last_length++;
                else {
                    if (max_length < last_length) max_length = last_length;
                    last_length = 0;
                }

                x--;
                y++;
            }
        }

        return max_length;
    }
}