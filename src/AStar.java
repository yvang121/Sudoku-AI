import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Ye on 10/29/2015.
 * A* heuristic search
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
        SudokuEvaluator evaluator;
        boolean solution = false;
        HashSet<GridNode> visited = new HashSet<GridNode>(); // coords that have been visited already + expanded
        PriorityQueue<GridNode> fringe = new PriorityQueue<GridNode>(); // frontier coords that have yet to be visited
        GridNode startNode = new GridNode(0, 0, calcHeuristic(0, 0), null);  // create start node object
        fringe.add(startNode);  // add start node to priority queue
        while (!solution | !fringe.isEmpty()) {  // while the solution is false or fringe set is not empty:
            GridNode visitNode = fringe.poll();  // poll from priority queue
            ArrayList<GridNode> neighbors = generateNeighbors(grid, visitNode);  // generate the neighbors above, left, right and below
            // TODO: how to prevent duplicate neighbors being added into fringe set?
            // place value into visitNode's location on backend grid based on constraints
            // add this node into the visited hash set. should only visit every cell in grid once, hence the set
            evaluator = new SudokuEvaluator(grid);
            solution = evaluator.evaluate();
        }
//        if ()
        return false;
    }

    //TODO: Specify a total goal cost(to decrease as we go along). Heuristic can be based on which gives highest jump
    /**
     * Calculates the heuristic cost of a single grid square using the row, column and subgrid
     * @return heuristic value for given row and column.
     */
    public int calcHeuristic(int row, int col) {
        //TODO: implement the heuristic calculator
        // For the current cell, identify its row, col and subgrid. Pick list that has MRV. If all =, pick randomly
        // MRV(Minimum remaining value). Of the MRVs, if m>1, pick randomly, else pick that value.
        return 0;
    }

    /**
     * Generate neighbors for a given node object.
     * @param grid backend grid to refer to for values and coordinates
     * @param currNode the current node
     */
    public ArrayList<GridNode> generateNeighbors(int[][] grid, GridNode currNode) {
        int[] neighborCells = {-1, 1};
        // TODO: maybe we should just fill in the priority queue manually. This finds a literal path.
        // TODO: the path could get stuck if it's surrounded by numbers != 0.
        ArrayList<GridNode> nodes = new ArrayList<GridNode>();
        for (int neighboringCell : neighborCells) {
            int neighborRow = currNode.getRow() + neighboringCell;
            int neighborCol = currNode.getColumn() + neighboringCell;
            if (neighborRow >= 0 & neighborRow < grid.length) {
                int neighborVal = grid[neighborRow][currNode.getColumn()];
                if (neighborVal == 0) {
                    GridNode newNode = new GridNode(neighborRow, currNode.getColumn(),
                            calcHeuristic(neighborRow, currNode.getColumn()), currNode);
                    nodes.add(newNode);
                }
            }
            if (neighborCol >= 0 & neighborCol < grid.length) {
                int neighborVal = grid[currNode.getRow()][neighborCol];
                if (neighborVal == 0) {
                    GridNode newNode = new GridNode(currNode.getRow(), neighborCol,
                            calcHeuristic(currNode.getRow(), neighborCol), currNode);
                    nodes.add(newNode);
                }
            }
        } return nodes;
    }
}
