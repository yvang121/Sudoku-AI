import java.util.*;

/**
 * Created by Ye on 11/10/2015.
 */
public class SudokuEvaluator {
    int[][] grid;
    int[][] subgrid;

    public SudokuEvaluator(int[][] grid) {
        this.grid = grid;
        this.subgrid = generate2dSubgrid();
    }

    /**
     * Checks the entire grid to see if there are any duplicates in it.
     * @return true if the checker didn't find any duplicates within the grid.
     */
    public boolean checker() {
        int dimension = grid.length;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                boolean columns = checkCol(j);
                boolean rows = checkRow(i);
                boolean subgrids = checkSubgrid(i, j);
                if (!(columns & rows & subgrids)) {
                    printIncorrect();
                    return false;
                }
            }
        }
        printCorrect();
        return true;
    }

    public boolean checkSubgrid(int row, int column) {
        int subDimension = (int) Math.sqrt(grid.length);
        int[][][] sub3dgrid = generateSubgrids();
        int[] subArray = sub3dgrid[(int)Math.floor(row/subDimension)][(int)Math.floor(column/subDimension)];
        return checkDuplicates(subArray);
    }

    public boolean checkRow(int row) {
        int[] rowArray = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            if (grid[row][i] != 0) {
                rowArray[i] = grid[row][i];
            }
        }
        return checkDuplicates(rowArray);
    }

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

    public int[][] generate2dSubgrid() {
        int dimension = grid.length;
        int subDimension = (int)Math.sqrt(dimension);
        int section = -subDimension;
        int sectionPos = 0;
        int[][] subgrid = new int[dimension][dimension];

        for (int i = 0; i < dimension; i++) {
            if (i%subDimension == 0){  // if i is divisible by subgrid dimension
                section += subDimension;  // add subgrid dimension to section (initialized to 0) 2x for 4x4
                sectionPos = 0;  // reset the section position
            }
            if (i%subDimension != 0){  // else if it's not divisible by subgrid dimension
                sectionPos += subDimension; // section position increments by subgrid dimension
            }
            int currentSection = section;
            for (int j = 0; j < dimension; j++){
                if (j%subDimension == 0 && j != 0){  // if j divisible by subgrid dimension
                    // section isn't 0
                    currentSection += 1; // increment
                }
                subgrid[currentSection][j % subDimension + sectionPos] = grid[i][j];
            }
        }
        return subgrid;
    }

    public void printIncorrect() {
        String incorrect = "|Solution is INCORRECT.|";
        String dashes = "";
        for (char letter : incorrect.toCharArray()) {
            dashes += '-';
        }
        System.out.println(dashes + "\n" + incorrect + " => " + Arrays.deepToString(grid) + "\n" + dashes);
    }

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
