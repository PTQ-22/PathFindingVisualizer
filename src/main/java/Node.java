import java.awt.*;

public class Node extends Rect implements Runnable {
    public static final char EMPTY = '.';
    public static final char BORDER = '#';
    public static final char START = 's';
    public static final char END = 'e';
    public static final char WAVE = 'w';
    public static final char PATH = 'p';
    public static final char NO_PATH = 't';
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
            case WAVE -> {return Color.YELLOW;}
            case PATH -> {return Color.blue;}
            case NO_PATH -> {return Color.red;}
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

    public void animate(char type) {
        if (this.type == type)
            new Thread(this).start();
    }

    @Override
    public void run() {
        int numOfOperations = 5;
        int val = 10;
        for (int i = 0; i < numOfOperations; ++i) {
            setColor(changeRGB(color, -val));
        }
        for (int i = 0; i < numOfOperations; ++i) {
            setColor(changeRGB(color, val));
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Color changeRGB(Color currentColor, int val) {
        int r = verifyColorVal(currentColor.getRed() + val);
        int g = verifyColorVal(currentColor.getGreen() + val);
        int b = verifyColorVal(currentColor.getBlue() + val);
        return new Color(r, g, b);
    }

    public static int verifyColorVal(int val) {
        if (val > 255) return 255;
        else if (val < 0) return 0;
        return val;
    }
}
