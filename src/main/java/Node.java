import java.awt.*;

public class Node extends Rect {
    public static final char EMPTY = '.';
    public static final char BORDER = '#';
    public static final char START = 's';
    public static final char END = 'e';
    public static final char WAVE = 'w';
    public static final char PATH = 'p';
    private char type;
    private Point gridPosition;

    public Node(Rectangle rectangle, char type, Point gridPosition) {
        super(rectangle, chooseColor(type));
        this.type = type;
        this.gridPosition = gridPosition;
    }

    public static Color chooseColor(char type) {
        switch (type) {
            case BORDER -> {return Color.BLACK;}
            case START -> {return new Color(0xDD28FB);}
            case END -> {return new Color(0, 150, 0);}
            default -> {return Color.WHITE;}
        }
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
        setColor(chooseColor(type));
        this.type = type;
    }

    public Point getGridPosition() {
        return gridPosition;
    }

    public void setGridPosition(Point gridPosition) {
        this.gridPosition = gridPosition;
    }
}
