import org.junit.Test;

/**
 * Created by Ye on 12/8/2015.
 */
public class TestBackTrack {
    Backtrack backtracker;
    @Test
    public void testBacktrack() {
        SudokuGrid grid = new SudokuGrid(4, "e");
        backtracker = new Backtrack(grid.getBackendGrid());
        boolean implement = backtracker.implement();
        System.out.println(implement);
        //TODO: fix bug.
    }
}