import java.awt.*;

public class Visualizer {
    private final WindowPanel windowPanel;
    private final MouseHandler mouseHandler;
    private final Button startButton;
    private final Button clearBordersButton;
    private final Button clearWaveButton;

    public Visualizer(WindowPanel windowPanel, MouseHandler mouseHandler) {
        this.windowPanel = windowPanel;
        this.mouseHandler = mouseHandler;
        this.startButton = new Button(new Rectangle(50, 110, 300, 70), Color.RED, new Color(200, 0, 0), "START");
        this.clearBordersButton = new Button(new Rectangle(450, 110, 300, 70), Color.BLUE, new Color(0, 0, 200), "CLEAR BORDERS");
        this.clearWaveButton = new Button(new Rectangle(850, 110, 300, 70), Color.WHITE, new Color(200, 200, 200), "CLEAR SEARCH");
    }

    public void update() {
        if (startButton.isMouse(mouseHandler.mousePos)) {

        }
        if (clearBordersButton.isMouse(mouseHandler.mousePos)) {

        }
        if (clearWaveButton.isMouse(mouseHandler.mousePos)) {

        }
    }

    public void draw(Graphics2D g2) {
        Button.drawButtons(g2);
    }
}