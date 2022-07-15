import java.awt.*;

public class Node extends Rect {
    public static char EMPTY = '.';
    public static char BORDER = '#';
    public static char START = 's';
    public static char END = 'e';
    public static char WAVE = 'w';
    public static char PATH = 'p';

    private char type;
    private Point gridPosition;

    public Node(Rectangle rectangle, Color color, char type, Point gridPosition) {
        super(rectangle, color);
        this.type = type;
        this.gridPosition = gridPosition;
    }

    @Override
    protected void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fill(rectangle);
    }

    @Override
    public boolean isMouse(Point mousePos) {
        return rectangle.contains(mousePos);
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public Point getGridPosition() {
        return gridPosition;
    }

    public void setGridPosition(Point gridPosition) {
        this.gridPosition = gridPosition;
    }
}
