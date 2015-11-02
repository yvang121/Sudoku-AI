/**
 * Created by Ye on 10/29/2015.
 */
public class AStar {
    /**
     * Heuristic calculator. Good heuristic, when there are no duplicates in a row, column
     * or subgrid. Bad heuristic, when there are duplicates in a row/column/subgrid, we want to avoid
     * that path. How to calculate heuristic: for each conflict in a row, column or subgrid, increment up so that
     * we can avoid the higher values of heuristic sums and pick low heuristic values.
     */

    /**
     * Calculates the heuristic cost of a single grid square using the row, column and subgrid
     * @param column
     * @param row
     * @return
     */
    public int calculateHeuristic(int column, int row) {
        return 0;
    }
}
