import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Ye on 11/20/2015.
 */
public class TestSudokuGrid {
    SudokuGrid grid;
    @Test
    public void testCheckInit() {
        int dimension = 4;
        grid = new SudokuGrid(dimension, "easy");
        int[][] tempGrid = {{1,4,0,0},{2,0,0,0},{0,0,0,0},{0,0,0,0}};
        grid.setBackendGrids(tempGrid);

        //Testing equals and booleans
        String dashes = "-----------------------------------------------------------------";
        assertTrue("Grid check 1", grid.checkInit(1, 1, 3));
        System.out.println(dashes);
        tempGrid[1][1] = 3;
        assertFalse(grid.checkInit(3, 1, 3));
    }
}
