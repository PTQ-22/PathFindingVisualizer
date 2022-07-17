
import java.awt.*;
import java.util.LinkedList;

public class Grid {
    public static final int TILE_SIZE = 30;
    public static final int RECT_SIZE = 28;
    public static final String NO_MV = "no-mv";
    public static final String MV_START = "mv-st";
    public static final String MV_END = "mv-en";
//    public static final String MV
    private final Node[][] nodes;
    private final int width;
    private final int height;
    private Point startNodePos = new Point(11, 10);
    private Point endNodePos = new Point(11, 30);
    private String currentState = "";

    public Grid(int x, int y, int width, int height) {
        nodes = new Node[height][width];
        this.width = width;
        this.height = height;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                nodes[i][j] = new Node(
                        new Rectangle(x + j * TILE_SIZE, y + i * TILE_SIZE, RECT_SIZE, RECT_SIZE),
                       Node.EMPTY, new Point(i, j));
            }
        }
        nodes[startNodePos.x][startNodePos.y].setType(Node.START);
        nodes[endNodePos.x][endNodePos.y].setType(Node.END);
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                nodes[i][j].draw(g2);
            }
        }
    }

    public void update(Point mousePos) {
        switch (currentState) {
            case Grid.MV_START -> {
                Point newPos = findPositionInGrid(nodes, mousePos);
                if (newPos != null) {
                    if (newPos.equals(endNodePos)) {
                        endNodePos = startNodePos;
                    }
                    swapNodesType(startNodePos, newPos);
                    animateNodesAround(startNodePos, newPos);
                    startNodePos = newPos;
                }
            }
            case Grid.MV_END -> {
                Point newPos = findPositionInGrid(nodes, mousePos);
                if (newPos != null) {
                    if (newPos.equals(startNodePos)) {
                        startNodePos = endNodePos;
                    }
                    swapNodesType(endNodePos, newPos);
                    animateNodesAround(endNodePos, newPos);
                    endNodePos = newPos;
                }
            }
            default -> {}
        }
    }

    public void swapNodesType(Point pos1, Point pos2) {
        char tmp = nodes[pos1.x][pos1.y].getType();
        nodes[pos1.x][pos1.y].setType(nodes[pos2.x][pos2.y].getType());
        nodes[pos2.x][pos2.y].setType(tmp);
    }

    public void animateNodesAround(Point oldPos, Point newPos) {
        if (!oldPos.equals(newPos)) {
            String direction = getAnimationDirection(oldPos, newPos);
            nodes[newPos.x][Math.min(newPos.y + 1, nodes[0].length - 1)].animate(Node.EMPTY);
            nodes[Math.min(newPos.x + 1, nodes.length - 1)][newPos.y].animate(Node.EMPTY);
            nodes[Math.max(newPos.x - 1, 0)][newPos.y].animate(Node.EMPTY);
            nodes[newPos.x][Math.max(newPos.y - 1, 0)].animate(Node.EMPTY);
            switch (direction) {
                case "right" -> {
                    nodes[newPos.x][Math.min(newPos.y + 1, nodes[0].length - 1)].animate(Node.EMPTY);
                    nodes[Math.min(newPos.x + 1, nodes.length - 1)][Math.min(newPos.y + 1, nodes[0].length - 1)].animate(Node.EMPTY);
                    nodes[Math.max(newPos.x - 1, 0)][Math.min(newPos.y + 1, nodes[0].length - 1)].animate(Node.EMPTY);
                }
                case "left" -> {
                    nodes[newPos.x][Math.max(newPos.y - 1, 0)].animate(Node.EMPTY);
                    nodes[Math.min(newPos.x + 1, nodes.length - 1)][Math.max(newPos.y - 1, 0)].animate(Node.EMPTY);
                    nodes[Math.max(newPos.x - 1, 0)][Math.max(newPos.y - 1, 0)].animate(Node.EMPTY);

                }
                case "down" -> {
                    nodes[Math.min(newPos.x + 1, nodes.length - 1)][newPos.y].animate(Node.EMPTY);
                    nodes[Math.min(newPos.x + 1, nodes.length - 1)][Math.min(newPos.y + 1, nodes[0].length - 1)].animate(Node.EMPTY);
                    nodes[Math.min(newPos.x + 1, nodes.length - 1)][Math.max(newPos.y - 1, 0)].animate(Node.EMPTY);

                }
                case "up" -> {
                    nodes[Math.max(newPos.x - 1, 0)][newPos.y].animate(Node.EMPTY);
                    nodes[Math.max(newPos.x - 1, 0)][Math.min(newPos.y + 1, nodes[0].length - 1)].animate(Node.EMPTY);
                    nodes[Math.max(newPos.x - 1, 0)][Math.max(newPos.y - 1, 0)].animate(Node.EMPTY);
                }
                default -> {}
        }
        }
    }

    public static String getAnimationDirection(Point oldPos, Point newPos) {
        if (oldPos.equals(newPos)) return "";
        if (oldPos.x == newPos.x) {
            if (oldPos.y < newPos.y) return "right";
            else return "left";
        }
        else {
            if (oldPos.x < newPos.x) return "down";
            else return "up";
        }
    }

    public void checkMouseClick(Point mousePos) {
        Point pos = findPositionInGrid(nodes, mousePos);
        if (pos != null) {
            if (nodes[pos.x][pos.y].getType() == Node.START) {
                setState(movingStartNode());
                return;
            }
            else if (nodes[pos.x][pos.y].getType() == Node.END) {
                setState(movingEndNode());
                return;
            }
        }
        setState(Grid.NO_MV);
    }

    public void checkPressed(MouseHandler mouseHandler) {
        Point pos = findPositionInGrid(nodes, mouseHandler.mousePos);
        if (pos != null) {
            if (nodes[pos.x][pos.y].getType() == Node.EMPTY && mouseHandler.mouseButton == 1) {
                nodes[pos.x][pos.y].setType(Node.BORDER);
//                nodes[pos.x][pos.y].animate();
            }
            else if (nodes[pos.x][pos.y].getType() == Node.BORDER && mouseHandler.mouseButton == 3) {
                nodes[pos.x][pos.y].setType(Node.EMPTY);
            }
        }
    }

    public void setState(String newState) {
        currentState = newState;
    }

    public String movingStartNode() {
        if (currentState.equals(Grid.MV_END)) return Grid.MV_END;
        else if (currentState.equals(Grid.MV_START)) return Grid.NO_MV;
        return Grid.MV_START;
    }

    public String movingEndNode() {
        if (currentState.equals(Grid.MV_START)) return Grid.MV_START;
        else if (currentState.equals(Grid.MV_END)) return Grid.NO_MV;
        return Grid.MV_END;
    }

    public static Point findPositionInGrid(Node[][] grid, Point mousePos) {
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                if (grid[i][j].rectangle.contains(mousePos)) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public boolean containNode(char type) {
        for (Node[] row : nodes) {
            for (Node node : row) {
                if (node.getType() == type) {
                    return true;
                }
            }
        }
        return false;
    }

    public void clearType(char type) {
        for (Node[] row : nodes) {
            for (Node node : row) {
                if (node.getType() == type) {
                    node.setType(Node.EMPTY);
                }
            }
        }
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public Node getStartNode() {
        return nodes[startNodePos.x][startNodePos.y];
    }

    public Node getEndNode() {
        return nodes[endNodePos.x][endNodePos.y];
    }
}
