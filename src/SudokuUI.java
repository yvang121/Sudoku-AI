import acm.graphics.GLabel;
import acm.program.ConsoleProgram;
import acm.program.GraphicsProgram;

/**
 * Created by Ye on 10/21/2015.
 */
public class SudokuUI extends GraphicsProgram {
    SudokuGrid grid;

    public void run() {
        grid = new SudokuGrid(9, "easy");
        add(grid);
    }
}
