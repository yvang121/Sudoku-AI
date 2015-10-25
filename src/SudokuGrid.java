import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GRect;
import acm.util.RandomGenerator;

import java.util.Random;

/**
 * Created by Ye-Vang on 10/21/2015.
 */
public class SudokuGrid extends GCompound {
    private static final int squareDimension = 40;
    int dimension;
    String difficulty;
    Random random;

    /**
     * Constructor for SudokuGrid objects.
     * @param dimension product of n * n subgrid perfect square.
     * @param difficulty string to indicate how many values to randomly generate and place on grid
     */
    public SudokuGrid(int dimension, String difficulty) {
        this.difficulty = difficulty.toLowerCase(); // How difficult a puzzle is, will affect how many starting numbers there are
        this.dimension = dimension;
        this.random = new RandomGenerator(); // Randomly generate numbers to put onto GCompound

        if (Math.sqrt(dimension) - Math.floor(Math.sqrt(dimension)) > 0) {
            // If the difference between integer and double is greater than 0, it's not a perfect square.
            try { // Throw an error, prompting users to input a perfect square size dimension
                throw new Exception("Invalid dimension size. \n Please enter a dimension size of a perfect square number.");
            } catch (Exception e) {
                // Prints the stack trace error, then exits out of the GraphicsProgram window.
                e.printStackTrace();
                System.exit(-1);
            } // TODO: should we just tell users to enter dimension size of 2, 3 or 4? Instead of throwing errors for
            // TODO: wrong dimension sizes?
        } else {
            for (int row = 0; row < dimension; row++) { // Generate the grid
                for (int column = 0; column < dimension; column++) {
                    addEmptySquare(row, column);
                }
            }
            addDividers();
            addBorders();
        }
    }

    /**
     * Adds exterior borders around the Sudoku Grid object.
     */
    public void addBorders() {
        int lengthOfLine = this.dimension * squareDimension;
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
    public void addDividers() {
        int divisor = (int) Math.sqrt(dimension); // How many divisors we're going to have
        for (int i = 1; i < divisor; i++) { // From 1 til max divisors:
            int dividerLine = i * divisor * squareDimension; // Placement of divisor line
            GLine xBoldLine = new GLine(dividerLine + 1, 0, dividerLine + 1, dimension * squareDimension);
            add(xBoldLine); // Adding line to GCompound
            GLine yBoldLine = new GLine(0, dividerLine + 1, dimension * squareDimension, dividerLine + 1);
            add(yBoldLine);
        }
    }

    /**
     * Adds empty squares to a designated pixel location
     * @param x the horizontal location to place empty square
     * @param y the vertical location to place empty square
     */
    public void addEmptySquare(int x, int y) {
        GRect rect = new GRect(squareDimension, squareDimension); // Add empty square
        add(rect, x * squareDimension, y * squareDimension);
    }


    // Getters and setters associated with the SudokuGrid object
    public static int getSquareDimension() {
        return squareDimension;
    }

    public int getDimension() {
        return dimension;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
