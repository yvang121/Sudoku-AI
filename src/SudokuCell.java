import javax.swing.*;
import java.awt.*;

/**
 * Created by Ye on 12/10/2015.
 */
public class SudokuCell extends JLabel {
    private int digit; //the number it would display
    private int x;
    private int y; //the x,y position on the grid
    private static final int SQUARE_DIMENSION = 40;

    public SudokuCell(int x, int y) {
        super();
        this.x = x;
        this.y = y;

        /** create a black border */
        setBorder(BorderFactory.createLineBorder(Color.black));

        /** set size to 50x50 pixel for one square */
        Dimension d = new Dimension(SQUARE_DIMENSION,SQUARE_DIMENSION);
        setPreferredSize(d);
    }

    public static int getSquareDimension() {
        return SQUARE_DIMENSION;
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

