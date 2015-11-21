import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Ye on 11/20/2015.
 */
public class TestBruteForce {
    SudokuGrid grid;
    @Test
    public void testBruteForce() {
        int numRuns = 0;
        double before = System.currentTimeMillis();
        while (true) {
            grid = new SudokuGrid(4, "easy");
            BruteForce bruteForce = new BruteForce();
            bruteForce.implement(grid.getBackendGrid());
//            grid.setBackendSubgrid(grid.generateSubgrids());
//            System.out.println(Arrays.deepToString(grid.getBackendSubgrid()));
            System.out.println(Arrays.deepToString(grid.getBackendGrid()));
            SudokuEvaluator gridCheck = new SudokuEvaluator(grid.getBackendGrid());
            numRuns++;
            if (gridCheck.checker()) {
                break;
            }
        }
        double after = System.currentTimeMillis();
        System.out.println("Number of total runs for Brute Force: " + numRuns +"\n"+
        "Total time taken: " + (int)((after-before)/60000) + " minutes.");
    }
}
