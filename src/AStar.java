import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Ye on 10/29/2015.
 */
public class AStar {
    /**
     * Heuristic calculator: count the number of values divided by the dimension (so for a 4x4 if there are
     * 3 out of 4 values in a subgrid, 3/4 = 0.75, let's multiply that by 10 and set that a heuristic), so a high
     * heuristic score is good. AStar would traverse in reverse. Identify all the coordinates that have the most
     * obvious and correct answers first. The ones that are less obvious usually have multiply possible values.
     */

    //TODO: specify start/goal? iterate through and find the min possible value => the obvious answers.
    public boolean AStarSearch(int[][] grid) {
        HashSet<GridNode> visited = new HashSet<GridNode>(); // coords that have been visited already + expanded
        PriorityQueue<GridNode> fringe = new PriorityQueue<GridNode>(); // fringe coords that have yet to be visited
        return false;
    }

    /**
     * Calculates the heuristic cost of a single grid square using the row, column and subgrid
     * @return heuristic value for given row and column.
     */
    public int calcHeuristic() {
        //TODO: implement the heuristic calculator
        return 0;
    }
}
