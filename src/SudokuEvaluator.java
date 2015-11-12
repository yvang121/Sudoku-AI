/**
 * Created by Ye on 11/10/2015.
 */
public class SudokuEvaluator {
    private int[] col;
    private int[] row;

    public SudokuEvaluator(int gridDimension) {
        col = new int[gridDimension + 1];
        row = new int[gridDimension + 1];
    }

    public boolean checker(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
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
