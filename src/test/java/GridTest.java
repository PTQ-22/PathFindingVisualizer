import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {
    Grid newGrid = new Grid(0, 150, 41, 25);

    @Test
    void gridStateChangesToMoveStartAfterClick() {
        assertEquals(Grid.MV_START, newGrid.movingStartNode());
    }

    @Test
    void gridStateChangesToMoveEndAfterClick() {
        assertEquals(Grid.MV_END, newGrid.movingEndNode());
    }

    @Test
    void gridStateChangesToNoMoveAfterSecondClickOnStartNode() {
        newGrid.setState(newGrid.movingStartNode());
        assertEquals(Grid.NO_MV, newGrid.movingStartNode());
    }

    @Test
    void gridStateChangesToNoMoveAfterSecondClickOnEndNode() {
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

    @Test
    void getAnimationDirectionRightWorks() {
        Point p1 = new Point(10, 7);
        Point p2 = new Point(10, 8);
        assertEquals("right", Grid.getAnimationDirection(p1, p2));
    }

    @Test
    void getAnimationDirectionLeftWorks() {
        Point p1 = new Point(10, 4);
        Point p2 = new Point(10, 3);
        assertEquals("left", Grid.getAnimationDirection(p1, p2));
    }

    @Test
    void getAnimationDirectionUpWorks() {
        Point p1 = new Point(10, 7);
        Point p2 = new Point(9, 7);
        assertEquals("up", Grid.getAnimationDirection(p1, p2));
    }

    @Test
    void getAnimationDirectionDownWorks() {
        Point p1 = new Point(4, 7);
        Point p2 = new Point(5, 7);
        assertEquals("down", Grid.getAnimationDirection(p1, p2));
    }

    @Test
    void getAnimationDirectionNoDirectionWorks() {
        Point p1 = new Point(5, 7);
        Point p2 = new Point(5, 7);
        assertEquals("", Grid.getAnimationDirection(p1, p2));
    }
}
