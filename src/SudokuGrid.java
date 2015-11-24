import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GRect;
import acm.util.RandomGenerator;

/**
 * Created by Ye-Vang on 10/21/2015.
 */
public class SudokuGrid extends GCompound {
    private static final int SQUARE_DIMENSION = 40;
    private static final int X_COORDINATE = 17;
    private static final int Y_COORDINATE = 25;
    private static final double EASY_MODIFIER = 0.20;
    private static final double MED_MODIFIER = 0.15;
    private static final double HARD_MODIFIER = 0.10;
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
        } else {
            for (int row = 0; row < dimension; row++) { // Generate the grid
                for (int column = 0; column < dimension; column++) {
                    addEmptySquare(row, column);
                }
            }
            addDividers();
            addBorders();
            initNumbers();
            addNumToUI(backendGrid);
        }
    }

    /**
     * Initialize this grid with random location, random values. Has to make sure there are no duplicates!
     * @return backendGrid that values were inserted into
     */
    private int[][] initNumbers() {
        int totalNumValues = dimension * dimension;
        int xRandCoord;
        int yRandCoord;
        boolean noDuplicates;
        int genRandValue;
        if (difficulty.equals("easy") | difficulty.equals("e")) {
            // How many numbers to generate based on difficulty
            int numbers = (int) Math.ceil(totalNumValues*EASY_MODIFIER);
            for (int i = 0; i <= numbers; i++) {
                initGrid();
            }
        } else if (difficulty.equals("med") | (difficulty.equals("medium")) | (difficulty.equals("m"))) {
            int numbers = (int) Math.ceil(totalNumValues*MED_MODIFIER);
            for (int i = 0; i <= numbers; i++) {
                initGrid();
            }
        } else if (difficulty.equals("hard") | difficulty.equals("h")) {
            int numbers = (int) Math.ceil(totalNumValues*HARD_MODIFIER);
            for (int i = 0; i <= numbers; i++) {
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
        boolean collision;
        RandomGenerator random = new RandomGenerator();
        int xRandCoord = random.nextInt(dimension);  // generate a random integer for x position
        int yRandCoord = random.nextInt(dimension);  // generate random integer for y position
        int genRandValue = random.nextInt(1, dimension);  // generate random value for (y, x) grid pos
//        System.out.println("Initial y and x: " + "(" + yRandCoord + ", " + xRandCoord + ")");
        noDuplicates = checkInit(yRandCoord, xRandCoord, genRandValue);
        while (!noDuplicates || backendGrid[yRandCoord][xRandCoord] != 0) {
            // Check to see if placing randomly gen'd value conflicts with same value in another row/col/subgrid
            // Also take into account that we could be placing a value that's already generated into the same spot
            RandomGenerator newRandGen = new RandomGenerator();
            xRandCoord = newRandGen.nextInt(dimension);
            yRandCoord = newRandGen.nextInt(dimension);
            genRandValue = newRandGen.nextInt(1, dimension);
            noDuplicates = checkInit(yRandCoord, xRandCoord, genRandValue);
//            collision = backendGrid[yRandCoord][xRandCoord] != 0;
//            System.out.println("Collision: " + collision);
        }
//        System.out.println("Result y and x: " + "(" + yRandCoord + ", " + xRandCoord + ")" +
//                " Value inserted: " + genRandValue + "\n");
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
//        String dashes = "-----------------------------------------------------------------";
//        System.out.println(dashes);
//        System.out.println("Grid: " + Arrays.deepToString(backendGrid));
//        System.out.println("Subgrid: " + Arrays.deepToString(eval.getSubgrid()));
//        System.out.println("Transferred row " + row +
//                ", value to insert: " + value + " " + Arrays.toString(rowArray));
//        System.out.println("Transferred column " + column +
//                ", value to insert: " + value + " " + Arrays.toString(colArray));
//        System.out.println("Transferred subgrid: " + Arrays.toString(subArray));
//        System.out.println("Initialize status: " + (checkCol & checkRow & checkSubs) + "\n" +
//                dashes);
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

    /**
     * Adds exterior borders around the Sudoku Grid object.
     */
    private void addBorders() {
        int lengthOfLine = dimension * SQUARE_DIMENSION;
        GLine topXLine = new GLine(0, 1, lengthOfLine, 1); // Offset the line to make it look 'bolder'
        add(topXLine);
        GLine leftYLine = new GLine(1, 0, 1, lengthOfLine);
        add(leftYLine);
        GLine rightYLine = new GLine(lengthOfLine - 1, 0, lengthOfLine - 1, lengthOfLine);
        add(rightYLine);
        GLine bottomXLine = new GLine(0, lengthOfLine - 1, lengthOfLine, lengthOfLine - 1);
        add(bottomXLine);
    }

    /**
     * Adds dividers to clearly indicate subgrids in the Sudoku Grid
     */
    private void addDividers() {
        int divisor = (int) Math.sqrt(dimension); // How many divisors we're going to have
        for (int i = 1; i < divisor; i++) { // From 1 til max divisors:
            int dividerLine = i * divisor * SQUARE_DIMENSION; // Placement of divisor line
            GLine xBoldLine = new GLine(dividerLine + 1, 0, dividerLine + 1, dimension * SQUARE_DIMENSION);
            add(xBoldLine); // Adding line to GCompound
            GLine yBoldLine = new GLine(0, dividerLine + 1, dimension * SQUARE_DIMENSION, dividerLine + 1);
            add(yBoldLine);
        }
    }

    /**
     * Adds empty squares to a designated pixel location
     * @param x the horizontal location to place empty square
     * @param y the vertical location to place empty square
     */
    private void addEmptySquare(int x, int y) {
        GRect rect = new GRect(SQUARE_DIMENSION, SQUARE_DIMENSION); // Add empty square
        add(rect, x * SQUARE_DIMENSION, y * SQUARE_DIMENSION);
    }

    /**
     * Adds number values to the grid interface.
     * @param backendGrid 2d array to read from and put onto graphical user interface.
     */
    public void addNumToUI(int[][] backendGrid) {
        for (int i = 0; i < backendGrid.length; i++) {
            for (int j = 0; j < backendGrid.length; j++) {
                if (backendGrid[i][j] != 0) {
                    GLabel numberLabel = new GLabel(Integer.toString(backendGrid[i][j]));
                    add(numberLabel, X_COORDINATE + i * SQUARE_DIMENSION, Y_COORDINATE + j * SQUARE_DIMENSION);
                }
            }
        }
    }


    // Getters and setters associated with the SudokuGrid object
    public static int getSQUARE_DIMENSION() {
        return SQUARE_DIMENSION;
    }

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
