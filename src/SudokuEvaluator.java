import java.util.*;

/**
 * Created by Ye on 11/10/2015.
 */
public class SudokuEvaluator {
    int[][] grid;
    int[][] subgrid;

    public SudokuEvaluator(int[][] grid) {
        this.grid = grid;
//        this.subgrid = generate2dSubgrid();
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

    public boolean checkDuplicates(int[] array) {
        Set<Integer> numbers = new HashSet<Integer>();
        for (int val : array) {
            if (val != 0 & numbers.contains(val)) {
                return false;
            } else {
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
            if (i%subDimension == 0){
                section += subDimension;
                sectionPos = 0;
            }
            if (i%subDimension != 0){
                sectionPos += subDimension;
            }
            for (int j = 0; j <dimension; j++){
                if (j%subDimension == 0 && section != 0){
                    section += 1;
                }
                subgrid[section][j % subDimension + sectionPos] = grid[i][j];
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
}
