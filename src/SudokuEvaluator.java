import java.util.Arrays;

/**
 * Created by Ye on 11/10/2015.
 */
public class SudokuEvaluator {
    int[][] grid;
    public SudokuEvaluator(int[][] grid) {
        this.grid = grid;
        this.checker();
    }

    public boolean checker() {
        int dimension = grid.length;
        int[] col = new int[dimension];
        int[] row = new int[dimension];
        int[] sub = new int[dimension];
        int subDimension = (int) Math.sqrt(dimension);
        int[][] subgrid = new int[dimension][dimension];
        int[] subColArray = new int[subDimension];
        int[] subRowArray = new int[subDimension];

        // Add values from grid to subgrid
        for (int i = 0; i < dimension; i++) {
            int subCol = (int) Math.floor(i/subDimension);
            for (int j = 0; j < dimension; j++) {
                int value = grid[i][j];
                int subRow = (int) Math.floor(j/subDimension);
                System.out.println("Value: " + value +"\n"+ "Subgrid column: " + subCol +"\n"+ "Subgrid row: " +
                subRow);
                System.out.println(Arrays.deepToString(subgrid));
                subgrid[subCol][subRow] = value;
                System.out.println(Arrays.deepToString(subgrid));
            }
        }
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                row[grid[j][i]] += 1;
                col[grid[i][j]] += 1;
                sub[grid[i][j]] += 1;
                if (row[grid[j][i]] > i + 1 || col[grid[i][j]] > i + 1 || sub[grid[i][j]] > i+1) {
                    String incorrect = "|Solution is INCORRECT.|";
                    String dashes = "";
                    for (char letter : incorrect.toCharArray()) {
                        dashes += '-';
                    }
                    System.out.println(dashes + "\n" + incorrect + "\n" + dashes);
                    return false;
                }
            }
        }

        //place into subgrids
        int section = -subDimension;
        int sectionPos = 0;
        for (int i=0; i < dimension; i++) {
            if (i%subDimension == 0){
                section += subDimension;
                sectionPos = 0;
            }
            if (i%subDimension != 0){
                sectionPos += subDimension;
            }
            int currSection = section;
            for (int j = 0; j <dimension; j++){
                if (j%subDimension == 0 && j != 0){
                    currSection += 1;
                }
                subgrid[currSection][j%subDimension + sectionPos] = grid[i][j];
            }
        }
        String correct = "|Solution is CORRECT.|";
        String dashes = "";
        for (char letter : correct.toCharArray()) {
            dashes += '-';
        }
        System.out.println(dashes + "\n" + correct + "\n" + dashes);
        return true;
    }
}
