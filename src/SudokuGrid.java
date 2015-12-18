import acm.util.RandomGenerator;

/**
 * Created by Ye-Vang on 10/21/2015.
 */
public class SudokuGrid {
    private static final double EASY_MODIFIER = 0.20;
    private static final double MED_MODIFIER = 0.15;
    private static final double HARD_MODIFIER = 0.10;
    private int initNumbers;
    private int dimension;
    private int subDimension;
    private String difficulty;
    private int[][] backendGrid;
    private int[][][] backendSubgrid;

    /**
     * Constructor for SudokuGrid objects.
     * @param dimension the length of entire grid.
     * @param difficulty string to indicate how many values to randomly generate and place on grid
     */
    public SudokuGrid(int dimension, String difficulty) {
        this.difficulty = difficulty.toLowerCase(); // How difficult a puzzle is, will affect how many starting numbers there are
        this.dimension = dimension;
        this.subDimension = (int) Math.sqrt(dimension);
        this.backendGrid = new int[dimension][dimension]; // Grid to store integers at grid locations
        this.backendSubgrid = generateSubgrid();

        if (Math.sqrt(dimension) - Math.floor(Math.sqrt(dimension)) > 0) {
            // If the difference between integer and double is greater than 0, it's not a perfect square.
            try { // Throw an error, prompting users to input a perfect square size dimension
                throw new Exception("Invalid dimension size. \n Please enter a dimension size of a perfect square number.");
            } catch (Exception e) {
                // Prints the stack trace error, then exits out of the GraphicsProgram window.
                e.printStackTrace();
                System.exit(-1);
            }
        }
        initNumbers();
    }

    /**
     * Initialize this grid with random location, random values. Has to make sure there are no duplicates!
     * @return backendGrid that values were inserted into
     */
    private int[][] initNumbers() {
        int totalNumValues = dimension * dimension;
        if (difficulty.equals("easy") | difficulty.equals("e")) {
            // How many numbers to generate based on difficulty
            initNumbers = (int) Math.floor(totalNumValues*EASY_MODIFIER);
            for (int i = 0; i <= initNumbers; i++) {
                initGrid();
            }
        } else if (difficulty.equals("med") | (difficulty.equals("medium")) | (difficulty.equals("m"))) {
            initNumbers = (int) Math.ceil(totalNumValues*MED_MODIFIER);
            for (int i = 0; i <= initNumbers; i++) {
                initGrid();
            }
        } else if (difficulty.equals("hard") | difficulty.equals("h")) {
            initNumbers = (int) Math.ceil(totalNumValues*HARD_MODIFIER);
            for (int i = 0; i <= initNumbers; i++) {
                initGrid();
            }
        }
        return backendGrid;
    }

    /**
     * Initialize the grid with random values (1 to length) at random locations
     */
    public void initGrid() {
        boolean noDuplicates;
        RandomGenerator random = new RandomGenerator();
        int xRandCoord = random.nextInt(dimension);  // generate a random integer for x position
        int yRandCoord = random.nextInt(dimension);  // generate random integer for y position
        int genRandValue = random.nextInt(1, dimension);  // generate random value for (y, x) grid pos
        noDuplicates = checkInit(yRandCoord, xRandCoord, genRandValue);
        while (!noDuplicates || backendGrid[yRandCoord][xRandCoord] != 0) {
            // Check to see if placing randomly gen'd value conflicts with same value in another row/col/subgrid
            // Also take into account that we could be placing a value that's already generated into the same spot
            RandomGenerator newRandGen = new RandomGenerator();
            xRandCoord = newRandGen.nextInt(dimension);
            yRandCoord = newRandGen.nextInt(dimension);
            genRandValue = newRandGen.nextInt(1, dimension);
            noDuplicates = checkInit(yRandCoord, xRandCoord, genRandValue);
        }
        backendGrid[yRandCoord][xRandCoord] = genRandValue;
        backendSubgrid = generateSubgrid();
    }

    /**
     * Checks if a value can be placed in a certain row/column/subgrid by checking for duplicates.
     * @param row it will be placed in.
     * @param column it will be placed in.
     * @param value that will be inserted into backend grid.
     * @return true if no duplicates, false if duplicates were found.
     */
    public boolean checkInit(int row, int column, int value) {
        int[] colArray = new int[dimension+1];
        int[] rowArray = new int[dimension+1];
        int[] subArray = new int[dimension+1];
        colArray[colArray.length-1] = value;
        rowArray[rowArray.length-1] = value;
        subArray[subArray.length-1] = value;
        int subgridX = (int) Math.floor(column/subDimension);
        int subgridY = (int) Math.floor(row/subDimension);
        SudokuEvaluator eval = new SudokuEvaluator(backendGrid);

        // for each index in rowArray, add from corresponding
        System.arraycopy(backendGrid[row], 0, rowArray, 0, dimension);
        for (int r = 0; r < dimension; r++) {
            colArray[r] = backendGrid[r][column];
        }
        System.arraycopy(backendSubgrid[subgridY][subgridX], 0, subArray, 0, dimension);
        boolean checkCol = eval.checkDuplicates(colArray);
        boolean checkRow = eval.checkDuplicates(rowArray);
        boolean checkSubs = eval.checkDuplicates(subArray);
        return checkCol & checkRow & checkSubs;
    }

    /**
     * Generate the 3d subgrid of this Sudoku grid.
     * @return 3d Subgrids
     */
    public int[][][] generateSubgrid() {
        int dimension = backendGrid.length;
        int subDimension = (int) Math.sqrt(dimension);
        int[][][] finalSubgrid = new int[subDimension][subDimension][dimension];
        // Add values from grid to subgrid
        for (int i = 0; i < dimension; i++) {  // Loop through entire grid of n x n
            for (int j = 0; j < dimension; j++) {
                int value = backendGrid[i][j];  // get the value
                int subRow = (int) Math.floor(i/subDimension); // Calculate correspondence to subgrid location x
                int subCol = (int) Math.floor(j/subDimension); // Calculate correspondence to subgrid location y
                for (int k = 0; k < dimension; k++) {  // 3d array access
                    if (finalSubgrid[subRow][subCol][k] == 0) {
                        finalSubgrid[subRow][subCol][k] += value;
                        break;
                    }
                }
            }
        }
        return finalSubgrid;
    }

    // Getters and setters associated with the SudokuGrid object
    public int getDimension() {
        return dimension;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int[][] getBackendGrid() {
        return backendGrid;
    }

    public static double getEasyModifier() {
        return EASY_MODIFIER;
    }

    public static double getMedModifier() {
        return MED_MODIFIER;
    }

    public static double getHardModifier() {
        return HARD_MODIFIER;
    }

    public int getInitNumbers() {
        return initNumbers;
    }

    public int[][][] getBackendSubgrid() {
        return this.backendSubgrid;
    }

    public void setBackendSubgrid(int[][][] backendSubgrid) {
        this.backendSubgrid = backendSubgrid;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setBackendGrids(int[][] backendGrid) {
        this.backendGrid = backendGrid;
        this.backendSubgrid = generateSubgrid();
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
