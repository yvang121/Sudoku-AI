import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
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
        int[][] tempGrid = new int[dimension][dimension];
        tempGrid[0][0] = 1;
        grid.setBackendGrids(tempGrid);
        System.out.println("Backend grid: " + Arrays.deepToString(grid.getBackendGrid()));
        System.out.println("Backend subgrid: " + Arrays.deepToString(grid.getBackendSubgrid()));

        //Testing equals and booleans
        assertEquals(1, tempGrid[0][0]);
        assertTrue("There are no duplicates in row, column or subgrid.", grid.checkInit(0, 0, 2));
        assertFalse(grid.checkInit(1, 1, tempGrid[0][0]));
    }
}
