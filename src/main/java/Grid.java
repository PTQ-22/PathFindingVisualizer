import java.awt.*;

public class Grid {
    public static final int TILE_SIZE = 30;
    public static final int RECT_SIZE = 28;
    private final Node[][] nodes;
    private final int startX;
    private final int startY;
    private final int width;
    private final int height;
    private Point startNodePos = new Point(11, 10);
    private Point endNodePos = new Point(11, 30);

    public Grid(int x, int y, int width, int height) {
        nodes = new Node[height][width];
        this.startX = x;
        this.startY = y;
        this.width = width;
        this.height = height;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                nodes[i][j] = new Node(
                        new Rectangle(startX + j * TILE_SIZE, startY + i * TILE_SIZE, RECT_SIZE, RECT_SIZE),
                        Color.WHITE, Node.EMPTY, new Point(i, j));
            }
        }
        nodes[startNodePos.x][startNodePos.y].setType(Node.START);
        nodes[startNodePos.x][startNodePos.y].setColor(new Color(0xDD28FB));
        nodes[endNodePos.x][endNodePos.y].setType(Node.END);
        nodes[endNodePos.x][endNodePos.y].setColor(new Color(0, 150, 0));
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                nodes[i][j].draw(g2);
            }
        }
    }
}
