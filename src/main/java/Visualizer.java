import java.awt.*;

public class Visualizer {
    private final MouseHandler mouseHandler;
    private final Button startButton;
    private final Button clearBordersButton;
    private final Button clearWaveButton;
    private final Grid grid;

    public Visualizer(MouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
        this.startButton = new Button(new Rectangle(65, 60, 300, 60), Color.WHITE, new Color(200, 200, 200), "START");
        this.clearBordersButton = new Button(new Rectangle(465, 60, 300, 60), Color.WHITE, new Color(200, 200, 200), "CLEAR BORDERS");
        this.clearWaveButton = new Button(new Rectangle(865, 60, 300, 60), Color.WHITE, new Color(200, 200, 200), "CLEAR SEARCH");
        this.grid = new Grid(0, 5 * Grid.TILE_SIZE,WindowPanel.WIDTH / Grid.TILE_SIZE, WindowPanel.HEIGHT / Grid.TILE_SIZE - 5);
    }

    public void update() {
        checkButtonsClicks();
        if (mouseHandler.isClicked()) {
            grid.checkMouseClick(mouseHandler.mousePos);
        }
        grid.update(mouseHandler.mousePos);
        if (mouseHandler.isPressed) {
            grid.checkPressed(mouseHandler);
        }
    }

    private void checkButtonsClicks() {
        if (startButton.isMouse(mouseHandler.mousePos) && mouseHandler.clicked) {
            Algorithm algo = new Algorithm(grid, grid.getStartNode(), grid.getEndNode());
            new Thread(algo).start();
        }
        if (clearBordersButton.isMouse(mouseHandler.mousePos) && mouseHandler.clicked) {
            grid.clearType(Node.BORDER);
        }
        if (clearWaveButton.isMouse(mouseHandler.mousePos) && mouseHandler.clicked) {
            grid.clearType(Node.WAVE);
            grid.clearType(Node.PATH);
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, WindowPanel.WIDTH, WindowPanel.HEIGHT);
        Button.drawButtons(g2);
        grid.draw(g2);
    }
}