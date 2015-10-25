import acm.program.GraphicsProgram;

/**
 * Created by Ye on 10/21/2015.
 */
public class SudokuUI extends GraphicsProgram {
    SudokuGrid grid;

    /**
     * Adding mouse listeners to sense clicks, and be able to handle that action accordingly
     */
    public void init() {
        addMouseListeners();
    }

    /**
     * Main run method to initiate Sudoku AI program
     */
    public void run() {
        grid = new SudokuGrid(9, "easy");
        add(grid, 0, 0);
    }
}
