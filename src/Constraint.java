/**
 * Created by Kenny Bello on 12/12/2015.
 */
public class Constraint {
    private int[][] grid;
    private int sqRoot;

    public Constraint(int[][] grid){
        this.grid = grid;
        this.sqRoot = (int) Math.sqrt(grid.length);
    }

    public boolean implement(){
        int row = -1;
        int col = -1;
        int[] best = new int[grid.length+1];
        int prevChoices = grid.length+1;

        for (int i=0; i<grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 0) {
                    int[] newChoices = getChoices(i, j);
                    if (newChoices == null){
                        continue;
                    }
                    else if (row < 0 || newChoices[grid.length] < prevChoices) {
                        row = i;
                        col = j;
                        prevChoices = newChoices[grid.length];
                        best = newChoices;
                    }
                }
            }
        }
        if (row < 0){
            return true;
        }
        else {
            for (int x = 0; x<best.length-1; x++){
                if (best[x] > 0){
                    System.out.println(best[x]);
                    grid[row][col] = best[x];
                    if (implement()) {
                        return true;
                    }
                    grid[row][col] = 0;
                }
            }
        }

        return false;
    }

    private int[] getChoices(int row, int col){
        int max = 0;
        int[] vals = new int[grid.length+1];
        for (int num = 1; num <= grid.length; num++){
            if (checkRow(row, num)&&checkCol(col, num)&&checkSect(row, col, num)){
                vals[num-1] = num;
                max++;
            }
        }
        if (max > 0){
            vals[grid.length] = max;
            return vals;
        }
        else {
            return null;
        }
    }

    public boolean checkRow(int row, int num){
        for(int j = 0; j < grid.length; j++){
            if (grid[row][j] == num){
                return false;
            }
        }
        return true;
    }

    public boolean checkCol(int col, int num){
        for (int i = 0; i < grid.length; i++){
            if (grid[i][col] == num){
                return false;
            }
        }
        return true;
    }

    public boolean checkSect(int row, int col, int num){
        row = row - (row%sqRoot);
        col = col - (col%sqRoot);

        for(int i = 0; i < grid.length; i++){
            if(grid[row + i/sqRoot][col + i%sqRoot] == num){
                return false;
            }
        }
        return true;
    }


}
