import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.util.Arrays;

/**
 * Created by Ye on 10/21/2015.
 */
public class SudokuUI extends GraphicsProgram {
    private SudokuGrid grid;


    /**
     * Main run method to initiate Sudoku AI program
     */
    public void run() {
        grid = new SudokuGrid(4, "easy");
        add(grid, 0, 0);
//        System.out.println(Arrays.deepToString(bruteForce.implement(grid.getBackendGrid())));
//        grid.setBackendGrid(bruteForce.implement(grid.getBackendGrid()));
        SudokuEvaluator gridCheck = new SudokuEvaluator(grid.getDimension());
        int[][] initGrid = grid.getBackendGrid();
        BruteForce bruteForce = new BruteForce();
        while (true) {
            int[][] genBruteForce = bruteForce.implement(initGrid);
            System.out.println(Arrays.deepToString(genBruteForce));
            grid.addNumbers(genBruteForce);
            if (gridCheck.checker(genBruteForce)) {
                break;
            } else {
                RandomGenerator gen = new RandomGenerator();
                bruteForce.setRandomGenerator(gen);
            }
        }
//        gridCheck.checker(grid.getBackendGrid());
//        grid.addNumbers(grid.getBackendGrid());
    }
}
