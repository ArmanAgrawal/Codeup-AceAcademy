
import java.util.Random;

public class MainGame {
    private static final int size = 4;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    private int[][] board;
    private Random random;
    private int score = 0;
    private int maxTile = 0;


    public MainGame() {
        board = new int[size][size];    
        random = new Random();
    }

    public void initBoard() {
        addRandomTiles();
        addRandomTiles();
    }

    public int getScore() {
        return score;
    }

    public int getMaxTile() {
        return maxTile;
    }

    public void addRandomTiles() {
        int empty = 0;
        int[][] emptyArray = new int[size * size][2];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    emptyArray[empty][0] = i;
                    emptyArray[empty][1] = j;
                    empty++;
                }
            }
        }
        if (empty == 0)
            return;

        int r = random.nextInt(empty);
        int row = emptyArray[r][0];
        int col = emptyArray[r][1];

        board[row][col] = random.nextInt(10) < 9 ? 2 : 4;
        if (board[row][col] > maxTile)
            maxTile = board[row][col];
    }

    public void printBoard() {
        System.out.println("-----------------------------");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%5d", board[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }

    public boolean shiftBoard(int dir) {
        boolean moved = false;

        if (dir == LEFT || dir == RIGHT) {
            for (int i = 0; i < size; i++) {
                if (shiftLine(board[i], dir))
                    moved = true;
            }
        } else {
            for (int j = 0; j < size; j++) {
                int[] col = new int[size];
                for (int i = 0; i < size; i++)
                    col[i] = board[i][j];

                if (shiftLine(col, dir))
                    moved = true;

                for (int i = 0; i < size; i++)
                    board[i][j] = col[i];
            }
        }

        return moved;
    }

    private boolean shiftLine(int[] line, int dir) {
        boolean moved = false;
        int[] newLine = new int[size];
        int pos, lastIndex = -1;

        int j, step;
        if (dir == LEFT || dir == UP) {
            pos = 0;
            j = 0;
            step = 1;
        } else {
            pos = size - 1;
            j = size - 1;
            step = -1;
        }

        while ((step == 1 && j < size) || (step == -1 && j >= 0)) {
            if (line[j] != 0) {
                if (canMerge(line, newLine, step, pos, j, lastIndex)) {

                    if (step == 1) {
                        newLine[pos - 1] *= 2;
                        score += newLine[pos - 1];
                        if (newLine[pos - 1] > maxTile)
                            maxTile = newLine[pos - 1];
                        lastIndex = pos - 1;

                    } else {
                        newLine[pos + 1] *= 2;
                        score += newLine[pos + 1];
                        if (newLine[pos + 1] > maxTile)
                            maxTile = newLine[pos + 1];
                        lastIndex = pos + 1;
                    }
                    moved = true;
                } else {
                    newLine[pos] = line[j];
                    if (j != pos)
                        moved = true;
                    pos += step;
                }
            }
            j += step;
        }

        for (int k = 0; k < size; k++)
            line[k] = newLine[k];

        return moved;
    }

    private boolean canMerge(int[] line,int[] newLine,int step,int pos,int j,int lastIndex){
        if(step==1){
             return pos > 0 && newLine[pos - 1] == line[j] && lastIndex != pos - 1;
        }else{
            return pos < size - 1 && newLine[pos + 1] == line[j] && lastIndex != pos + 1;
        }
    }

    public boolean isGameOver() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0)
                    return false;

            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (board[i][j] == board[i][j + 1])
                    return false;

            }
        }
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == board[i + 1][j])
                    return false;

            }
        }
        return true;
    }

}
