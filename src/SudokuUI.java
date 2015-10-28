import acm.program.GraphicsProgram;

/**
 * Created by Ye on 10/21/2015.
 */
public class SudokuUI extends GraphicsProgram {
    SudokuGrid grid;

    /**
     * Main run method to initiate Sudoku AI program
     */
    public void run() {
        grid = new SudokuGrid(16, "easy");
        add(grid, 0, 0);
    }
}
