import javax.swing.*;
import java.awt.*;

/**
 * Created by Ye on 12/10/2015.
 */
public class SudokuCell extends JLabel {
    int digit; //the number it would display
    int x, y; //the x,y position on the grid

    public SudokuCell(int x, int y) {
        super();
        this.x = x;
        this.y = y;

        /** create a black border */
        setBorder(BorderFactory.createLineBorder(Color.black));

        /** set size to 50x50 pixel for one square */
        Dimension d = new Dimension(40,40);
        setPreferredSize(d);
    }

    public int getDigit() {
        return digit;
    }

    public void setDigit(int digit) {
        this.digit = digit;
    }
}

