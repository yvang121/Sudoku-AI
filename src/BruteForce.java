import acm.util.RandomGenerator;

/**
 * Created by Ye on 11/9/2015.
 */
public class BruteForce {
    /**
     * After each iteration, it should check what integer values are invalid to be placed in the grid.
     * Implement a constraint-propagation checker. Checks through column/row + subgrid to identify valid
     * integers.
     */
    private RandomGenerator randomGenerator;
    private int[][] grid;
    private SudokuEvaluator bruteEval;


    public BruteForce(int[][] grid) {
        this.grid = grid;
        this.randomGenerator = new RandomGenerator();
        this.bruteEval = new SudokuEvaluator(grid);
    }

    public RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setRandomGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
}
