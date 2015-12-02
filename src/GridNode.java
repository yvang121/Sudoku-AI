import com.sun.istack.internal.NotNull;

/**
 * Created by Ye on 11/28/2015.
 * Grid node for the AStar class to utilize.
 */
public class GridNode implements Comparable<GridNode> {
    int heuristic;
    int column;
    int row;
    GridNode parent;

    public GridNode(int row, int col, int heuristic) {
        this.column = col;
        this.row = row;
        this.heuristic = heuristic;
    }

    @Override
    public int compareTo(@NotNull GridNode node) {
        return Integer.compare(this.getHeuristic(), node.getHeuristic());
    }

    public int getHeuristic() {
        return heuristic;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public GridNode getParent() {
        return parent;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setParent(GridNode parent) {
        this.parent = parent;
    }
}
