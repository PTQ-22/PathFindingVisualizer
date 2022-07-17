import java.awt.*;

public class Visualizer {
    private final WindowPanel windowPanel;
    private final MouseHandler mouseHandler;
    private final Button startButton;
    private final Button clearBordersButton;
    private final Button clearWaveButton;
    private final Grid grid;

    public Visualizer(WindowPanel windowPanel, MouseHandler mouseHandler) {
        this.windowPanel = windowPanel;
        this.mouseHandler = mouseHandler;
        this.startButton = new Button(new Rectangle(65, 60, 300, 60), Color.WHITE, new Color(200, 200, 200), "START");
        this.clearBordersButton = new Button(new Rectangle(465, 60, 300, 60), Color.WHITE, new Color(200, 200, 200), "CLEAR BORDERS");
        this.clearWaveButton = new Button(new Rectangle(865, 60, 300, 60), Color.WHITE, new Color(200, 200, 200), "CLEAR SEARCH");
        this.grid = new Grid(0, 5 * Grid.TILE_SIZE,WindowPanel.WIDTH / Grid.TILE_SIZE, WindowPanel.HEIGHT / Grid.TILE_SIZE - 5);
    }

    public void update() {
        if (mouseHandler.isClicked()) {
            grid.checkMouseClick(mouseHandler.mousePos);
        }
        grid.update(mouseHandler.mousePos);
        checkButtonsClicks();
        if (mouseHandler.isPressed) {
            grid.checkPressed(mouseHandler);
//            System.out.println(mouseHandler.isPressed);
        }
    }

    private void checkButtonsClicks() {
        if (startButton.isMouse(mouseHandler.mousePos) && mouseHandler.clicked) {

        }
        if (clearBordersButton.isMouse(mouseHandler.mousePos) && mouseHandler.clicked) {

        }
        if (clearWaveButton.isMouse(mouseHandler.mousePos) && mouseHandler.clicked) {

        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, WindowPanel.WIDTH, WindowPanel.HEIGHT);
        Button.drawButtons(g2);
        grid.draw(g2);
    }
}