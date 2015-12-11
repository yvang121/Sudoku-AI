import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Ye on 12/8/2015.
 */
public class TestBackTrack {
    Backtrack backtracker;
    @Test
    public void testBacktrack() {
        int[][] grid = {{1, 0, 0,0},{0,0,0,0},{0,0,0,0},{1,0,0,0}};
        backtracker = new Backtrack(grid);
        int count = backtracker.initialFill();
        boolean implement = backtracker.implement();
        assertEquals(2, count);
        assertFalse(implement);
    }
}
