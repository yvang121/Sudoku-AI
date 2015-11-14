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
    private String difficulty;
    private RandomGenerator random;
    private int[][] backendGrid;

    /**
     * Constructor for SudokuGrid objects.
     * @param dimension product of n * n subgrid perfect square.
     * @param difficulty string to indicate how many values to randomly generate and place on grid
     */
    public SudokuGrid(int dimension, String difficulty) {
        this.difficulty = difficulty.toLowerCase(); // How difficult a puzzle is, will affect how many starting numbers there are
        this.dimension = dimension;
        this.random = new RandomGenerator(); // Randomly generate numbers to put onto GCompound
        this.backendGrid = new int[dimension][dimension]; // Grid to store integers at grid locations

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

    private int[][] initNumbers() {
        int numberOfValues = dimension * dimension;
        if (difficulty.equals("easy") | difficulty.equals("e")) {
            // How many numbers to generate based on difficulty
            int numbers = (int) Math.ceil(numberOfValues*EASY_MODIFIER);
            //TODO: has to check to see if the randomly generated value isn't already in the same row/col/subgrid.
            for (int i = 0; i <= numbers; i++) {
                int xCoord = random.nextInt(dimension);
                int yCoord = random.nextInt(dimension);
                int genRandValue = random.nextInt(1, dimension);
                backendGrid[xCoord][yCoord] = genRandValue;
            }
        } else if (difficulty.equals("med") | (difficulty.equals("medium")) | (difficulty.equals("m"))) {
            int numbers = (int) Math.ceil(numberOfValues*MED_MODIFIER);
            for (int i = 0; i <= numbers; i++) {
                int xRandCoord = random.nextInt(dimension);
                int yRandCoord = random.nextInt(dimension);
                int genRandValue = random.nextInt(1, dimension);
                backendGrid[xRandCoord][yRandCoord] = genRandValue;
            }
        } else if (difficulty.equals("hard") | difficulty.equals("h")) {
            int numbers = (int) Math.ceil(numberOfValues*HARD_MODIFIER);
            for (int i = 0; i <= numbers; i++) {
                int xCoord = random.nextInt(dimension);
                int yCoord = random.nextInt(dimension);
                int genRandValue = random.nextInt(1, dimension);
                backendGrid[xCoord][yCoord] = genRandValue;
            }
        }
        return backendGrid;
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

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setBackendGrid(int[][] backendGrid) {
        this.backendGrid = backendGrid;
    }
}
