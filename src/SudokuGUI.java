
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ye on 12/10/2015.
 */
public class SudokuGUI extends JFrame {
    private String[] diffs = {"Easy", "Medium", "Hard"};
    private Integer[] sizes = {4, 9, 16, 25};
    private String difficulty;
    private int gridDimension = 0;

    private final JPanel panel = new JPanel(new GridBagLayout());
    private JLabel comboLabel = new JLabel("Please choose a difficulty: ");
    private JComboBox diffOptions = new JComboBox(diffs);
    private JLabel dimLabel = new JLabel("Type in a grid dimension size and press enter:");
    private JTextField dimensionField;
    private SudokuGridPanel sudokuGridPanel;
    private JButton generate;
    private JButton run;

    public SudokuGUI() {
        super("JPanel Practice");

        diffOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                difficulty = (String)cb.getSelectedItem();
                System.out.println("Difficulty entered: " + difficulty);
            }
        });

        dimensionField = new JTextField(20);
        dimensionField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridDimension = Integer.valueOf(dimensionField.getText());
                if (Math.sqrt(gridDimension) - Math.floor(Math.sqrt(gridDimension)) > 0) {
                    // If the difference between integer and double is greater than 0, it's not a perfect square.
                    try { // Throw an error, prompting users to input a perfect square size dimension
                        throw new Exception("Invalid dimension size.\n");
                    } catch (Exception ex) {
                        // Prints the stack trace error, then exits out of the GraphicsProgram window.
                        ex.getCause();
                        JOptionPane.showMessageDialog(null, "Please enter a a valid dimension size " +
                                "of a perfect square number. \n (i.e. 1, 4, 9, 25...)",
                                "Invalid Dimension", JOptionPane.ERROR_MESSAGE);
                    }
                }
                GridBagConstraints cons = new GridBagConstraints();
                cons.anchor = GridBagConstraints.WEST;
                cons.gridx = 0;
                cons.gridy = 0;
                cons.gridheight = 6;
                panel.remove(sudokuGridPanel);
                sudokuGridPanel = new SudokuGridPanel(gridDimension);
                panel.add(sudokuGridPanel, cons);
                panel.revalidate();
                pack();
                System.out.println("Dimension entered: " + gridDimension);
            }
        });

        run = new JButton( new AbstractAction("run") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                try {
                    SudokuGrid grid = new SudokuGrid(gridDimension, difficulty);
                    Backtrack backtrack = new Backtrack(grid.getBackendGrid());
                    backtrack.implement();
                    SudokuEvaluator eval = new SudokuEvaluator(grid.getBackendGrid());
                    eval.evaluate();
                } catch (NullPointerException | ArithmeticException | ArrayIndexOutOfBoundsException ex) {
                    ex.getCause();
                    JOptionPane.showMessageDialog(null, "Please specify valid parameters.",
                            "Invalid Parameters", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Specifies the padding in-between components in pixels

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(comboLabel, constraints);

        constraints.gridy = 1;
        panel.add(diffOptions, constraints);

        constraints.gridy = 2;
        panel.add(dimLabel, constraints);

        constraints.gridy = 3;
        panel.add(dimensionField, constraints);

        constraints.gridy = 4;
        panel.add(run, constraints);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 6;
        sudokuGridPanel = new SudokuGridPanel(gridDimension);
        panel.add(sudokuGridPanel, constraints);

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Sudoku Solver"));
//        Dimension dimension = new Dimension(500, 500);
//        panel.setPreferredSize(dimension);
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
