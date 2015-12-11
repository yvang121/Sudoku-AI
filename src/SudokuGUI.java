
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ye on 12/10/2015.
 */
public class SudokuGUI extends JFrame {
    private String[] diffs = {"easy", "medium", "hard"};
    private Integer[] sizes = {4, 9, 16, 25};
    private String difficulty;
    private int gridDimension;

    private JComboBox diffOptions = new JComboBox(diffs);
    private JLabel dimLabel = new JLabel("Enter a grid dimension size and press enter:");
    private JTextField dimensionField = new JTextField(10);
    private JButton run;

    public SudokuGUI() {
        super("JPanel Practice");
        JPanel panel = new JPanel(new GridBagLayout());

        diffOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                difficulty = (String)cb.getSelectedItem();
                System.out.println("Difficulty entered: " + difficulty);
            }
        });

        dimensionField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridDimension = Integer.valueOf(dimensionField.getText());
                System.out.println("Dimension entered: " + gridDimension);
            }
        });

        run = new JButton( new AbstractAction("run") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                SudokuGrid grid = new SudokuGrid(gridDimension, difficulty);
                Backtrack backtrack = new Backtrack(grid.getBackendGrid());
                backtrack.implement();
                SudokuEvaluator eval = new SudokuEvaluator(grid.getBackendGrid());
                eval.evaluate();
            }
        });

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Specifies the padding in-between components in pixels

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(diffOptions, constraints);

        constraints.gridy = 1;
        panel.add(dimLabel, constraints);

        constraints.gridy = 2;
        panel.add(dimensionField, constraints);

        constraints.gridy = 3;
        panel.add(run, constraints);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 5;
        SudokuGridPanel sudokuGridPanel = new SudokuGridPanel(9, 9);
        panel.add(sudokuGridPanel, constraints);

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Sudoku Solver"));

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuGUI().setVisible(true);
            }
        });
    }
}
