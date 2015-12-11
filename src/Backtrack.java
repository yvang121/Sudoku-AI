/**
 * Created by Kenny Bello on 12/7/2015.
 */
public class Backtrack {
    private int[][] grid;
    private int sqRoot;
    private int count;

    public Backtrack(int[][] grid){
        this.grid = grid;
        this.sqRoot = (int) Math.sqrt(grid.length);
        this.count = initialFill();
    }

    /**
     * Populates a 2d array with valid values and backtracks recursively if the taken path fails.
     * @return boolean
     */
    public boolean implement(){
        int[] coords = findEmpty();
//        if (count == grid.length*grid.length) {
//            return true; // Base case: if everything in the grid is filled, break.
//        }
        if (coords != null) {
            int row = coords[0];
            int col = coords[1];

            for (int i = 1; i <= grid.length; i++) {
                if (isOk(row, col, i)) {
                    grid[row][col] = i;
                    System.out.println(row+" "+col);
                    if (implement()) {
                        count++; // Used for base case. Counts number of filled init values + # of values implemented
                        System.out.println("Added " + i + " to cell: (" + row + ", " + col + ")");
                        return true;
                    }
                    grid[row][col] = 0;
                }
            }
        } else {
            return true;
        }
        System.out.println("Failed!");
        return false;
    }

    /**
     * Counts the initial values initialized in the grid.
     * @return a count of the number of initial values.
     */
    public int initialFill() {
        int count = 0;
        for (int[] values : grid) {
            for (int value : values) {
                if (value != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Checks all columns, rows and subgrids for the input num
     * @param row to ceck in for num
     * @param col to check in for num
     * @param num to check for conflicts
     * @return true if there are no conflicts
     */
    public boolean isOk(int row, int col, int num){
        return checkCol(col, num)&& checkRow(row, num) && checkSect(row, col, num);
    }

    /**
     * Checks a particular column for num input
     * @param col to check in
     * @param num integer value to check for
     * @return true if there isn't an occurrence of num in column
     */
    public boolean checkCol(int col, int num){
        for (int i = 0; i < grid.length; i++){
            if (grid[i][col] == num){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks a particular row for num input
     * @param row row to check in
     * @param num integer value to check for
     * @return true if there isn't an occurrence of num in row
     */
    public boolean checkRow(int row, int num){
        for(int j = 0; j < grid.length; j++){
            if (grid[row][j] == num){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks a particular section(subgrid) for num input
     * @param row of num
     * @param col of num
     * @param num integer value to check for
     * @return true if num does not occur in section
     */
    public boolean checkSect(int row, int col, int num){
        row = row - (row%sqRoot);
        col = col - (col%sqRoot);

        for(int i = 0; i < sqRoot; i++){
            for(int j = 0; j < sqRoot; j++){
                if(grid[row + i][col + j] == num){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Finds an empty cell to place a value in.
     * @return the coordinates of the empty cell or null if there are no empty cells.
     */
    public int[] findEmpty(){
        int[] empty = new int[2];
        for (int i = grid.length-1; i >= 0; i--){
            for (int j = grid.length-1; j >= 0; j--){
                if(grid[i][j] == 0){
                    empty[0] = i;
                    empty[1] = j;
                    return empty;
                }
            }
        }
        return null;
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getSqRoot() {
        return sqRoot;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void setSqRoot(int sqRoot) {
        this.sqRoot = sqRoot;
    }
}
