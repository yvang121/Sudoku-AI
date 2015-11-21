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
        grid = new SudokuGrid(4, "e");
        int[][] grid1 = {{1,2,3,4},{3,4,1,2},{2,3,4,1},{4,1,2,3}};
        grid.setBackendGrids(grid1);
        add(grid, 0, 0);
        grid.addNumToUI(grid.getBackendGrid());
//        BruteForce bruteForce = new BruteForce();
//        grid.setBackendGrids(bruteForce.implement(grid.getBackendGrid()));
//        SudokuEvaluator gridCheck = new SudokuEvaluator(grid.getBackendGrid());
//        grid.addNumToUI(grid.getBackendGrid());
//        System.out.println(Arrays.deepToString(grid.getBackendGrid()));
//        System.out.println(Arrays.deepToString(grid.getBackendSubgrid()));

//        petList = new JComboBox(petStrings);
//        add(petList);
//        petList.setSelectedIndex(4);
//        petList.addActionListener(this);

    }
}
