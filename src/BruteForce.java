import acm.util.RandomGenerator;

/**
 * Created by Ye on 11/9/2015.
 */
public class BruteForce {
    /**
     * After each iteration, it should check what integer values are invalid to be placed in the grid.
     * Implement a constraint-propagation checker. Checks through column/row + subgrid to identify valid
     * integers.
     *
     * Backtracking:
     *   Find row, col of an unassigned cell
     If there is none, return true
     For digits from 1 to 9
     a) If there is no conflict for digit at row,col
     assign digit to row,col and recursively try fill in rest of grid
     b) If recursion successful, return true
     c) Else, remove digit and try another
     If all digits have been tried and nothing worked, return false
     */
    private RandomGenerator randomGenerator;


    public BruteForce() {
        this.randomGenerator = new RandomGenerator();
    }

    /**
     * Populates a 2d array with random values
     * @param grid
     * @return 2d array
     */
    public int[][] implement(int[][] grid){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                if(grid[i][j] == 0){
                    grid[i][j] += randomGenerator.nextInt(1, grid.length);
                }
            }
        }
        return grid;
    }

    public RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }

    public void setRandomGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }
}


