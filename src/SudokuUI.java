import acm.program.GraphicsProgram;

/**
 * Created by Ye on 10/21/2015.
 */
public class SudokuUI extends GraphicsProgram {
    private SudokuGrid grid;
//    private String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
//    JComboBox petList;
    // Combo box for drop-down. Easy parameter changing.

    /**
     * Main run method to initiate Sudoku AI program
     */
    public void run() {
        grid = new SudokuGrid(9, "e");
        add(grid, 0, 0);
        grid.addNumToUI(grid.getBackendGrid());

//        petList = new JComboBox(petStrings);
//        add(petList);
//        petList.setSelectedIndex(4);
//        petList.addActionListener(this);

    }
}
