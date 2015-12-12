import javax.swing.*;
import java.awt.*;

/**
 * Created by Ye on 12/10/2015.
 */
public class SudokuGridPanel extends JPanel {
    int dimension;
    public SudokuGridPanel(int dimension) {
        super(new GridBagLayout());
        this.dimension = dimension;
        GridBagConstraints constraints = new GridBagConstraints();
        // Construct the grid.
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
                constraints.fill = GridBagConstraints.BOTH;
                constraints.gridx = i;
                constraints.gridy = j;
                add(new SudokuCell(i, j), constraints);
            }
        }
        int divisor = (int) Math.sqrt(dimension); // How many divisors we're going to have
        for (int i = 1; i < divisor; i++) { // From 1 til max divisors:
            int dividerLine = i * divisor * SudokuCell.getSquareDimension(); // Placement of divisor line

        }
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridheight = 10;
        // Add a black border around everything
        setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
    }
}

