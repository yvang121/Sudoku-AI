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


    public BruteForce() {
        this.randomGenerator = new RandomGenerator();
    }

    public int[][] implement(int[][] grid){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                if(grid[j][i] == 0){
                    grid[j][i] += randomGenerator.nextInt(1, grid.length);
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


