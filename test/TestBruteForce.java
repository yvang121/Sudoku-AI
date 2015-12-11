import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Ye on 11/20/2015.
 */
public class TestBruteForce {
    SudokuGrid grid = new SudokuGrid(4, "e");
    @Test
    public void testBruteForce() {
        int numRuns = 0;
        double before = System.currentTimeMillis();
        while (numRuns < 1500000) {
            BruteForce bruteForce = new BruteForce();
            int[][] tempGrid = bruteForce.implement(grid.getBackendGrid());
            System.out.println(Arrays.deepToString(grid.getBackendGrid()));
            SudokuEvaluator gridCheck = new SudokuEvaluator(tempGrid);
            numRuns++;
            if (gridCheck.evaluate()) {
                break;
            }
        }
        double after = System.currentTimeMillis();
        System.out.println("Number of total runs for Brute Force: " + numRuns +"\n"+
        "Total time taken: " + (int)((after-before)/60000) + " minutes.");
    }
}
