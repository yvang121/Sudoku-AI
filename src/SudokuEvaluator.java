/**
 * Created by Ye on 11/10/2015.
 */
public class SudokuEvaluator {
    private int[] col;
    private int[] row;

    public SudokuEvaluator() {
        col = new int[9];
        row = new int[9];
    }

    private boolean checker (int[][] grid) {
        for (int i = 0; i <= grid.length - 1; i++) {
            for (int j = 0; j <= grid.length - 1; j++) {
                row[grid[j][i]] += 1;
                col[grid[i][j]] += 1;
                if (row[grid[j][i]] > i + 1 || col[grid[i][j]] > i + 1) {
                    return false;
                }
            }
        }
        return true;
    }


}
