import java.util.*;

/**
 * Created by Ye on 11/10/2015.
 * Evaluator class to check 2d arrays for correctness.
 */
public class SudokuEvaluator {
    private int[][] grid;
    private int[][] subgrid;

    public SudokuEvaluator(int[][] grid) {
        this.grid = grid;
        this.subgrid = generate2dSubgrid();
    }

    /**
     * Checks the entire grid to see if there are any duplicates in it.
     * @return true if no duplicates found within the grid.
     */
    public boolean checker() {
        int dimension = grid.length;
        boolean checkSubgrids = checkAllSubgrids();
        for (int i = 0; i < dimension; i++) {
            boolean rows = checkRow(i);
            for (int j = 0; j < dimension; j++) {
                boolean columns = checkCol(j);
                if (!(columns & rows & checkSubgrids)) {
                    printIncorrect();
                    return false;
                }
            }
        }
        printCorrect();
        return true;
    }

    public boolean checkAllSubgrids() {
        for (int i = 0; i < grid.length; i++) {
            HashSet<Integer> subValues = new HashSet<Integer>();
            for (int j = 0; j < grid.length; j++) {
                if (subgrid[i][j] != 0) {
                    if (subValues.contains(subgrid[i][j])) {
                        return false;
                    } else {
                        subValues.add(subgrid[i][j]);
                    }
                }
            }
            if (subValues.size() != grid.length) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks a subgrid for duplicates
     * @param row of the grid
     * @param column of the grid
     * @return true if there are no duplicates in input row and column
     */
    public boolean checkSubgrid(int row, int column) {
        int subDimension = (int) Math.sqrt(grid.length);
        int[][][] sub3dgrid = generateSubgrids();
        int[] subArray = sub3dgrid[(int)Math.floor(row/subDimension)][(int)Math.floor(column/subDimension)];
        return checkDuplicates(subArray);
    }

    /**
     * Checks a row for duplicates
     * @param row to be checked
     * @return true if there are no duplicates in input row
     */
    public boolean checkRow(int row) {
        int[] rowArray = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            if (grid[row][i] != 0) {
                rowArray[i] = grid[row][i];
            }
        }
        return checkDuplicates(rowArray);
    }

    /**
     * Checks a column for duplicates
     * @param col to be checked
     * @return true if there are no duplicates in input column
     */
    public boolean checkCol(int col) {
        int[] colArray = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][col] != 0) {
                colArray[i] = grid[i][col];
            }
        }
        return checkDuplicates(colArray);
    }

    /**
     * Check for duplicates in an array
     * @param array
     * @return true if no duplicates are in the array
     */
    public boolean checkDuplicates(int[] array) {
        Set<Integer> numbers = new HashSet<Integer>();
        for (int val : array) {
            if (val != 0 & numbers.contains(val)) {
                return false;
            } else if (val != 0 & !numbers.contains(val)) {
                numbers.add(val);
            }
        }
        return true;
    }

    /**
     * Checks the location for duplicates.
     * @param row of the value
     * @param column of the value
     * @return true if there's no other duplicate
     */
    public boolean checkLocation(int row, int column) {
        boolean columns = checkCol(column);
        boolean rows = checkRow(row);
        boolean subgrids = checkSubgrid(row, column);
        if (!(columns & rows & subgrids)) {
            return false;
        }
        return true;
    }

    /**
     * Helper function to generate a 3d subgrid for directly translating from grid to subgrid
     * @return 3d array designating subgrids
     */
    public int[][][] generateSubgrids() {
        int dimension = grid.length;
        int subDimension = (int) Math.sqrt(dimension);
        int[][][] finalSubgrid = new int[subDimension][subDimension][dimension];
        // Add values from grid to subgrid
        for (int i = 0; i < dimension; i++) {  // Loop through entire grid of n x n
            for (int j = 0; j < dimension; j++) {
                int value = grid[i][j];  // get the value
                int subCol = (int) Math.floor(i/subDimension); // Calculate correspondence to subgrid location x
                int subRow = (int) Math.floor(j/subDimension); // Calculate correspondence to subgrid location y
                for (int k = 0; k < dimension; k++) {  // 3d array access
                    if (finalSubgrid[subRow][subCol][k] == 0) {
                        finalSubgrid[subRow][subCol][k] += value;
                        break;
                    }
                }
            }
        }
        return finalSubgrid;
    }

    /**
     * Generate the 2d array subgrid associated with grid
     * @return 2d array
     */
    public int[][] generate2dSubgrid() {
        int dimension = grid.length;
        int subDimension = (int)Math.sqrt(dimension);
        int subgridSect = -subDimension;
        int subArrayLoc = 0;
        int[][] subgrid = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            if (i%subDimension == 0){  // if i is divisible by subgrid dimension
                subgridSect += subDimension;  // add subgrid dimension to section (initialized to 0) 2x for 4x4
                subArrayLoc = 0;  // reset the section position
            }
            if (i%subDimension != 0){  // else if it's not divisible by subgrid dimension
                subArrayLoc += subDimension; // section position increments by subgrid dimension
            }
            int currentSection = subgridSect;
            for (int j = 0; j < dimension; j++){
                if (j%subDimension == 0 && j != 0){  // if j divisible by subgrid dimension
                    // section isn't 0
                    currentSection += 1; // increment
                }
                subgrid[currentSection][j % subDimension + subArrayLoc] = grid[i][j];
            }
        }
        return subgrid;
    }

    /**
     * Prints a box stating a solution is incorrect.
     */
    public void printIncorrect() {
        String incorrect = "|Solution is INCORRECT.|";
        String dashes = "";
        for (char letter : incorrect.toCharArray()) {
            dashes += '-';
        }
        System.out.println(dashes + "\n" + incorrect + " => " + Arrays.deepToString(grid) + "\n" + dashes);
    }

    /**
     * Prints a box stating a solution is correct.
     */
    public void printCorrect() {
        String correct = "|Solution is CORRECT.|";
        String dashes = "";
        for (char letter : correct.toCharArray()) {
            dashes += '-';
        }
        System.out.println(dashes + "\n" + correct + " => " + Arrays.deepToString(grid) + "\n" + dashes);
    }

    public int[][] getGrid() {
        return grid;
    }

    public int[][] getSubgrid() {
        return subgrid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void setSubgrid(int[][] subgrid) {
        this.subgrid = subgrid;
    }
}
