import acm.program.GraphicsProgram;

import javax.swing.*;
import java.util.Arrays;

/**
 * Created by Ye on 10/21/2015.
 */
public class SudokuUI extends GraphicsProgram {
    private SudokuGrid grid;
    private String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
    JComboBox petList;
    // Combo box for drop-down. Easy parameter changing.

    /**
     * Main run method to initiate Sudoku AI program
     */
    public void run() {
//        grid = new SudokuGrid(9, "M");
//        add(grid, 0, 0);
//        grid.addNumToUI(grid.getBackendGrid());
//        BruteForce bruteForce = new BruteForce();
//        System.out.println(Arrays.deepToString(grid.getBackendGrid()));
//        System.out.println(Arrays.deepToString(bruteForce.implement(grid.getBackendGrid())));
//        grid.setBackendGrid(bruteForce.implement(grid.getBackendGrid()));
//        SudokuEvaluator gridCheck = new SudokuEvaluator(grid.getDimension());

        while (true) {
            grid = new SudokuGrid(9, "easy");
            BruteForce bruteForce = new BruteForce();
            int[][] genBruteForce = bruteForce.implement(grid.getBackendGrid());
            System.out.println(Arrays.deepToString(genBruteForce));
            SudokuEvaluator gridCheck = new SudokuEvaluator(grid.getDimension());
            if (gridCheck.checker(genBruteForce)) {
                add(grid, 0, 0);
                grid.addNumToUI(genBruteForce);
                break;
            }
        }

//        gridCheck.checker(grid.getBackendGrid());
//        grid.addNumbers(grid.getBackendGrid());

//        petList = new JComboBox(petStrings);
//        add(petList);
//        petList.setSelectedIndex(4);
//        petList.addActionListener(this);
    }
}
