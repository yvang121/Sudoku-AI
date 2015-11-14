import java.util.Arrays;

/**
 * Created by Ye on 11/10/2015.
 */
public class SudokuEvaluator {
    private int[] col;
    private int[] row;
    private int[][] subgrid;
    private int subgridSize;

    public SudokuEvaluator(int gridDimension) {
        this.col = new int[gridDimension + 1];
        this.row = new int[gridDimension + 1];
        this.subgridSize = (int) Math.sqrt(gridDimension);
        this.subgrid = new int[gridDimension][gridDimension];
    }

    public boolean checker(int[][] grid) {
        int[] subColArray = new int[subgridSize];
        int[] subRowArray = new int[subgridSize];

        // Add values from grid to subgrid
        for (int col = 0; col < grid.length; col++) {
            for (int row = 0; row < grid.length; row++) {
                int value = grid[col][row];
                int subCol = (int) Math.floor(col/grid.length);
                int subRow = (int) Math.floor(row/grid.length);
                subgrid[subCol][subRow] = value;
            }
        }
        System.out.println(Arrays.deepToString(subgrid));

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                row[grid[j][i]] += 1;
                col[grid[i][j]] += 1;
                if (row[grid[j][i]] > i + 1 || col[grid[i][j]] > i + 1) {
                    String incorrect = "|Solution is INCORRECT.|";
                    String dashes = "";
                    for (char letter : incorrect.toCharArray()) {
                        dashes += '-';
                    }
                    System.out.println(dashes + "\n" + incorrect + "\n" + dashes);
                    return false;
                }
            }
        }
        String correct = "|Solution is CORRECT.|";
        String dashes = "";
        for (char letter : correct.toCharArray()) {
            dashes += '-';
        }
        System.out.println(dashes + "\n" + correct + "\n" + dashes);
        return true;
    }

    /**
     * Generating subgrid => column / Math.sqrt(dimensions) + row - row % Math.sqrt(dimensions)
     */
    public boolean checkSubgrid(int[][] grid) {
        int[] subColArray = new int[subgridSize];
        int[] subRowArray = new int[subgridSize];

        for (int col = 0; col < subgridSize; col++) {
            for (int row = 0; row < subgridSize; row++) {
                int value = grid[col][row];
                int subCol = (int) Math.floor(col/grid.length);
                int subRow = (int) Math.floor(row/grid.length);
                subgrid[subCol][subRow] = value;
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                row[grid[j][i]] += 1;
                col[grid[i][j]] += 1;
                if (row[grid[j][i]] > i + 1 || col[grid[i][j]] > i + 1) {
                    String incorrect = "|Solution is INCORRECT.|";
                    String dashes = "";
                    for (char letter : incorrect.toCharArray()) {
                        dashes += '-';
                    }
                    System.out.println(dashes + "\n" + incorrect + "\n" + dashes);
                    return false;
                }
            }
        }
        System.out.println(Arrays.deepToString(subgrid));
        return true;
    }
}
