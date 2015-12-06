import org.junit.Test;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Ye-Vang on 12/6/2015.
 */
public class TestAStar {
    AStar aStar;
    @Test
    public void testAStar() {
        aStar = new AStar();
        PriorityQueue<GridNode> fringe = new PriorityQueue<GridNode>();
        GridNode node = new GridNode(1, 1, aStar.calcHeuristic(), null);
        int[][] grid = {{0,4,0,0}, {2,1,3,0},{0,0,0,0},{0,0,0,0}};
        ArrayList<GridNode> neighbors = aStar.generateNeighbors(grid, node);
        System.out.println(fringe.size());
    }
}
