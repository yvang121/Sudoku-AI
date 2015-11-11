import acm.program.GraphicsProgram;

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
        BruteForce bruteForce = new BruteForce(grid.getBackendGrid());
    }
}
