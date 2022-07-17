import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    void gridStateChangesToMoveStartAfterClick() {
        Grid newGrid = new Grid(0, 150, 41, 25);
        assertEquals(Grid.MV_START, newGrid.movingStartNode());
    }

    @Test
    void gridStateChangesToMoveEndAfterClick() {
        Grid newGrid = new Grid(0, 150, 41, 25);
        assertEquals(Grid.MV_END, newGrid.movingEndNode());
    }

    @Test
    void gridStateChangesToNoMoveAfterSecondClickOnStartNode() {
        Grid newGrid = new Grid(0, 150, 41, 25);
        newGrid.setState(newGrid.movingStartNode());
        assertEquals(Grid.NO_MV, newGrid.movingStartNode());
    }

    @Test
    void gridStateChangesToNoMoveAfterSecondClickOnEndNode() {
        Grid newGrid = new Grid(0, 150, 41, 25);
        newGrid.setState(newGrid.movingEndNode());
        assertEquals(Grid.NO_MV, newGrid.movingEndNode());
    }

    @Test
    void findPositionInGridWorks() {
        int n = 5, m = 5;
        Node[][] grid = new Node[n][m];
        int size = 10;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                grid[i][j] = new Node(
                        new Rectangle(j*size,i*size, size, size),
                        Node.EMPTY, new Point(i, j));
            }
        }
        Point mousePos = new Point(25, 41);
        assertEquals(new Point(4, 2), Grid.findPositionInGrid(grid, mousePos));
    }
}
