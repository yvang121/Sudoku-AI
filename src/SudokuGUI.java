
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ye on 12/10/2015.
 */
public class SudokuGUI extends JFrame {
    private int[][] grid;
    private String[] diffStrings = {"Easy", "Medium", "Hard"};
    private String[] algStrings = {"BF Backtrack", "Random-Restart", "Most-Constrained"};
    private String difficulty = "Easy";
    private int gridDimension = 0;
    private String algorithm = "BF Backtrack";
    private SudokuGrid sudokuGrid;

    private final JPanel panel = new JPanel(new GridBagLayout());
    private JLabel comboLabel;
    private JComboBox diffBox;
    private JLabel dimLabel;
    private JTextField dimField;
    private SudokuGridPanel sudokuGridPanel;
    private JComboBox algBox;
    private JButton generate;
    private JButton run;

    public SudokuGUI() {
        super("Sudoku AI");

        comboLabel = new JLabel("Please choose a difficulty: ");

        diffBox = new JComboBox(diffStrings);
        diffBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                difficulty = (String) cb.getSelectedItem();
                System.out.println("Difficulty entered: " + difficulty);
            }
        });

        dimLabel = new JLabel("Type in a grid dimension size and press enter:");

        dimField = new JTextField(20);
        dimField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridDimension = Integer.valueOf(dimField.getText());
                GridBagConstraints cons = new GridBagConstraints();
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
                } else {
                    try {
                        sudokuGrid = new SudokuGrid(gridDimension, difficulty);
                        grid = sudokuGrid.getBackendGrid();
                        panel.remove(sudokuGridPanel);
                        sudokuGridPanel = new SudokuGridPanel(gridDimension);
                        fillJPanel(grid, sudokuGridPanel, cons);
                    } catch (NullPointerException ex) {
                        ex.getCause();
                        JOptionPane.showMessageDialog(null, "I need both a difficulty and a dimension!",
                                "Invalid Parameters", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                cons.anchor = GridBagConstraints.WEST;
                cons.gridx = 0;
                cons.gridy = 0;
                cons.gridheight = 11;
                panel.add(sudokuGridPanel, cons);
                pack();
                System.out.println("Dimension entered: " + gridDimension);
            }
        });

        algBox = new JComboBox(algStrings);
        algBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox jComboBox = (JComboBox) e.getSource();
                algorithm = (String) jComboBox.getSelectedItem();
                System.out.println("Algorithm chosen: " + algorithm);
            }
        });

        generate = new JButton(new AbstractAction("Generate") {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridBagConstraints c = new GridBagConstraints();
                panel.remove(sudokuGridPanel);
                sudokuGridPanel = new SudokuGridPanel(gridDimension);
                sudokuGrid = new SudokuGrid(gridDimension, difficulty);
                grid = sudokuGrid.getBackendGrid();
                fillJPanel(grid, sudokuGridPanel, c);
            }
        });

        run = new JButton( new AbstractAction("Run") {
            @Override
            public void actionPerformed( ActionEvent e ) {
                GridBagConstraints c = new GridBagConstraints();
                try {
                    System.out.println("Running...");
                    switch (algorithm) {
                        case ("Random-Restart"):
//                            grid = new SudokuGrid(gridDimension, difficulty).getBackendGrid();
                            int numRuns = 0;
                            double before = System.currentTimeMillis();
                            boolean solved;
                            panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            int threshold = (int) Math.pow(gridDimension, (gridDimension*gridDimension)-sudokuGrid.getInitNumbers());
                            while (numRuns <= threshold*0.75) {
                                BruteForce bruteForce = new BruteForce();
                                int[][] tempGrid = bruteForce.implement(grid);
                                SudokuEvaluator eval = new SudokuEvaluator(tempGrid);
                                solved = eval.evaluate();
                                if (solved) {
                                    panel.remove(sudokuGridPanel);
                                    sudokuGridPanel = new SudokuGridPanel(gridDimension);
                                    fillJPanel(tempGrid, sudokuGridPanel, c);
                                    break;
                                }
                                numRuns++;
                            }
                            double end = System.currentTimeMillis();
                            double time = (end - before)/1000;
                            panel.setCursor(Cursor.getDefaultCursor());
                            JOptionPane.showMessageDialog(null, "Time taken: " + time + " seconds; Runs taken: " + numRuns,
                                    "Notification", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case ("BF Backtrack"):
//                            grid = new SudokuGrid(gridDimension, difficulty).getBackendGrid();
                            Backtrack backtrack = new Backtrack(grid);
                            boolean solved0;
                            double before0 = System.currentTimeMillis();
                            panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            backtrack.implement();
                            SudokuEvaluator checker = new SudokuEvaluator(grid);
                            solved0 = checker.evaluate();
                            double end0 = System.currentTimeMillis();
                            double time0 = (end0 - before0);
                            panel.remove(sudokuGridPanel);
                            sudokuGridPanel = new SudokuGridPanel(gridDimension);
                            if (solved0) {
                                fillJPanel(grid, sudokuGridPanel, c);
                                JOptionPane.showMessageDialog(null, "Time taken to solve: " + time0 + " milliseconds",
                                        "Solution found", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                fillJPanel(grid, sudokuGridPanel, c);
                                JOptionPane.showMessageDialog(null, "Unable to find a solution.",
                                        "No Solution", JOptionPane.ERROR_MESSAGE);
                            }
                            panel.setCursor(Cursor.getDefaultCursor());
                            break;
                        case ("Most-Constrained"):
//                            grid = new SudokuGrid(gridDimension, difficulty).getBackendGrid();
                            Constraint constraint = new Constraint(grid);
                            boolean solved1;
                            double before1 = System.currentTimeMillis();
                            panel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            constraint.implement();
                            SudokuEvaluator ch = new SudokuEvaluator(grid);
                            solved1 = ch.evaluate();
                            double end1 = System.currentTimeMillis();
                            double time1 = (end1 - before1);
                            panel.remove(sudokuGridPanel);
                            sudokuGridPanel = new SudokuGridPanel(gridDimension);
                            if (solved1) {
                                fillJPanel(grid, sudokuGridPanel, c);
                                JOptionPane.showMessageDialog(null, "Time taken to solve: " + time1 + " milliseconds",
                                        "Solution found", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                fillJPanel(grid, sudokuGridPanel, c);
                                JOptionPane.showMessageDialog(null, "Unable to find a solution.",
                                        "No Solution", JOptionPane.ERROR_MESSAGE);
                            }
                            panel.setCursor(Cursor.getDefaultCursor());
                            break;
                    }
                } catch (NullPointerException | ArithmeticException | ArrayIndexOutOfBoundsException ex) {
                    ex.getCause();
                    JOptionPane.showMessageDialog(null, "Please be sure to specify difficulty, " +
                                    "grid dimension and algorithm to run.",
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
        panel.add(diffBox, constraints);

        constraints.gridy = 2;
        panel.add(dimLabel, constraints);

        constraints.gridy = 3;
        panel.add(dimField, constraints);

        constraints.gridy = 4;
        panel.add(algBox, constraints);

        constraints.gridy = 5;
        panel.add(generate, constraints);

        constraints.gridy = 6;
        panel.add(run, constraints);

        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 10;
        sudokuGridPanel = new SudokuGridPanel(gridDimension);
        panel.add(sudokuGridPanel, constraints);

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Sudoku Solver"));
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void fillJPanel(final int[][] grid, final SudokuGridPanel sudokuGridPanel, GridBagConstraints c) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] != 0) {
                    JLabel numLabel = new JLabel(Integer.toString(grid[i][j]));
                    c.gridx = i;
                    c.gridy = j;
                    sudokuGridPanel.add(numLabel, c);
                }
            }
        }
        int divisor = (int) Math.sqrt(gridDimension); // How many divisors we're going to have
        for (int i = 1; i < divisor; i++) { // From 1 til max divisors:
            final int dividerLine = i * divisor; // Placement of divisor line
            JLayeredPane jPanel = new JLayeredPane();
            Dimension d = new Dimension(0, (grid.length - 1) * sudokuGridPanel.dimension);
            jPanel.setSize(d);
            jPanel.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, Color.black));
            c.fill = GridBagConstraints.BOTH;
            c.gridx = dividerLine;
            c.gridy = 0;
            c.gridheight = gridDimension;
            c.anchor = GridBagConstraints.WEST;
            sudokuGridPanel.add(jPanel, c);
        }
        for (int i = 1; i < divisor; i++) { // From 1 til max divisors:
            int dividerLine = i * divisor ; // Placement of divisor line
            JLayeredPane jPanel2 = new JLayeredPane();
            Dimension d = new Dimension(grid.length*sudokuGridPanel.dimension, 0);
            jPanel2.setSize(d);
            jPanel2.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, Color.black));
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 0;
            c.gridy = dividerLine;
            c.gridheight = 0;
            c.gridwidth = gridDimension;
            c.anchor = GridBagConstraints.NORTH;
            sudokuGridPanel.add(jPanel2, c);
        }
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 10;
        c.anchor = GridBagConstraints.WEST;
        panel.add(sudokuGridPanel, c);
        pack();
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
