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
        // Add a black border around everything
//        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));
    }
}

