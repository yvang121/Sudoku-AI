import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GRect;
import acm.util.RandomGenerator;

import java.util.Random;

/**
 * Created by Ye-Vang on 10/21/2015.
 */
public class SudokuGrid extends GCompound {
    private static final int squareDimension = 50;
    int dimension;
    String difficulty;
    Random random;

    public SudokuGrid(int dimension, String difficulty) {
        this.difficulty = difficulty;
        this.dimension = dimension;
        this.random = new RandomGenerator();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                GRect rect = new GRect(squareDimension, squareDimension);
                add(rect, i*squareDimension, j*squareDimension);
            }
        }
        int divisor = (int) Math.sqrt(dimension);
        for (int i = 1; i < divisor; i++) {
            int dividerLine = i*divisor*squareDimension;
            GLine xBoldLine = new GLine(dividerLine+1, 0, dividerLine+1, dimension*squareDimension);
            add(xBoldLine);
            GLine yBoldLine = new GLine(0, dividerLine+1, dimension*squareDimension, dividerLine+1);
            add(yBoldLine);
        }
        GLine topLine = new GLine(0, 1, dimension*squareDimension, 1);
        add(topLine);
        GLine leftLine = new GLine(1, 0, 1, dimension*squareDimension);
        add(leftLine);
        GLine rightLine = new GLine(dimension*squareDimension-1, 0, dimension*squareDimension-1, dimension*squareDimension);
        add(rightLine);
        GLine bottomLine = new GLine(0, dimension*squareDimension-1, dimension*squareDimension, dimension*squareDimension-1);
        add(bottomLine);
        System.out.println((int) Math.sqrt(dimension));
    }
}
