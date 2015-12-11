import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by Ye on 10/21/2015.
 */
public class SudokuUI extends GraphicsProgram {
    private SudokuGrid grid;
//    private String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
//    JComboBox petList;
    // Combo box for drop-down. Easy parameter changing.

    public void init() {
        addKeyListeners();
    }
    /**
     * Main run method to initiate Sudoku AI program
     */
    public void run() {
        grid = new SudokuGrid(9, "e");
        add(grid, 0, 0);
        Backtrack backtracker = new Backtrack(grid.getBackendGrid());
        boolean implemented = backtracker.implement();
        SudokuEvaluator se = new SudokuEvaluator(backtracker.getGrid());
        se.checker();
        if (!implemented) {
            JOptionPane.showMessageDialog(null, "Unable to find a solution.",
                    "No Solution", JOptionPane.ERROR_MESSAGE);
            pause(500);
            exit();
        }
        grid.addNumToUI(grid.getBackendGrid());
//        petList = new JComboBox(petStrings);
//        add(petList);
//        petList.setSelectedIndex(4);
//        petList.addActionListener(this);

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            pause(100);
            System.exit(0);
        }
    }
}
