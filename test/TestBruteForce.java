import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Ye on 11/20/2015.
 */
public class TestBruteForce {
//    SudokuGrid grid;
    @Test
    public void testBruteForce() {
        int numRuns = 0;
        double before = System.currentTimeMillis();
//        while (true) {
//            grid = new SudokuGrid(4, "easy");
//            BruteForce bruteForce = new BruteForce();
//            bruteForce.implement(grid.getBackendGrid());
////            grid.setBackendSubgrid(grid.generateSubgrids());
////            System.out.println(Arrays.deepToString(grid.getBackendSubgrid()));
//            System.out.println(Arrays.deepToString(grid.getBackendGrid()));
//            SudokuEvaluator gridCheck = new SudokuEvaluator(grid.getBackendGrid());
//            numRuns++;
//            if (gridCheck.checker()) {
//                break;
//            }
//        }

        //TODO: why does SudokuGrid also get implemented by BruteForce object?
        // Begin trial code
        SudokuGrid grid = new SudokuGrid(4, "e");
        int[][] tempGrid = new int[4][4];
        System.arraycopy(grid.getBackendGrid(), 0, tempGrid, 0, tempGrid.length);
        System.out.println(Arrays.deepToString(tempGrid));
        BruteForce bruteForce = new BruteForce();
        bruteForce.implement(tempGrid);  // Implement only on temporary grid
        System.out.println(Arrays.deepToString(grid.getBackendGrid()));  // Implements on the SudokuGrid object as well
        System.out.println(Arrays.deepToString(tempGrid));  // Implements on the SudokuGrid object as well
        // End trial code

        double after = System.currentTimeMillis();
        System.out.println("Number of total runs for Brute Force: " + numRuns +"\n"+
        "Total time taken: " + (int)((after-before)/60000) + " minutes.");
    }
}
