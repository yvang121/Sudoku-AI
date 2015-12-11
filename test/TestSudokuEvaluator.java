import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ye on 11/20/2015.
 */
public class TestSudokuEvaluator {
    @Test
    public void testSudokuEvaluator() {
        int dimension = 4;
        int[][] grid = {{1,2,3,4},{3,4,1,2},{2,3,4,1},{4,1,2,3}};
        int[][] grid1 = {{2,1,4,3},{3,3,4,1},{4,3,2,1},{1,2,1,4}};
        SudokuEvaluator evaluator = new SudokuEvaluator(grid1);
        SudokuEvaluator evaluator1 = new SudokuEvaluator(grid);

        assertFalse("Column 1 = False", evaluator.checkCol(1));
        assertTrue("Column 0 = True", evaluator.checkCol(0));
        assertFalse("Row 1 = False", evaluator.checkRow(1));
        assertFalse("Subgrid 1 = False", evaluator.checkSubgrid(0, 1));
        assertTrue(evaluator1.evaluate());
        assertFalse(evaluator.evaluate());
        assertTrue(evaluator1.checkAllSubgrids());
    }
}
