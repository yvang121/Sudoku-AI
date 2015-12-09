import acm.util.RandomGenerator;

/**
 * Created by Ye on 11/9/2015.
 */
public class BruteForce {
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
        int[][] tempGrid = new int[grid.length][grid.length];
        for(int i = 0; i < tempGrid.length; i++){
            for(int j = 0; j < tempGrid.length; j++){
                if(grid[i][j] == 0){
                    tempGrid[i][j] += randomGenerator.nextInt(1, tempGrid.length);
                } else {
                    int clone = grid[i][j];
                    tempGrid[i][j] = clone;
                }
            }
        }
        return tempGrid;
    }

    public RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }

    public void setRandomGenerator(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }
}


