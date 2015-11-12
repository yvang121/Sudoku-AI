import acm.program.GraphicsProgram;

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
        grid = new SudokuGrid(9, "easy");
        add(grid, 0, 0);
        BruteForce bruteForce = new BruteForce();
        System.out.println(Arrays.deepToString(bruteForce.implement(grid.getBackendGrid())));
        grid.setBackendGrid(bruteForce.implement(grid.getBackendGrid()));
        SudokuEvaluator gridCheck = new SudokuEvaluator(grid.getDimension());
        System.out.println(gridCheck.checker(grid.getBackendGrid()));
        grid.addNumbers(grid.getBackendGrid());
    }
}
