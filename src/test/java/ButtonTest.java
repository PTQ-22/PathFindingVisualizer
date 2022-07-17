import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ButtonTest {
    private final Color buttonColor = Color.blue;
    private final Color buttonColorHover = Color.darkGray;
    private final Button button = new Button(new Rectangle(0, 0, 100, 100), buttonColor, buttonColorHover, "");
    private final Grid grid = new Grid(0, 150, 41, 25);

    @Test
    void buttonReactsIfMouse() {
        Point mousePos = new Point(20, 50);
        assertTrue(button.isMouse(mousePos));
    }

    @Test
    void buttonNotReactsIfNotMouse() {
        Point mousePos = new Point(120, 200);
        assertFalse(button.isMouse(mousePos));
    }

    @Test
    void buttonChangesColorIfMouse() {
        Point mousePos = new Point(20, 50);
        button.isMouse(mousePos);
        assertEquals(buttonColorHover, button.getColor());
    }

    @Test
    void buttonChangesColorToNormalIfNotMouse() {
        Point mousePos = new Point(20, 50);
        button.isMouse(mousePos);
        mousePos.setLocation(300, 300);
        button.isMouse(mousePos);
        assertEquals(buttonColor, button.getColor());
    }

    @BeforeEach
    void initGridWithFewBordersDraw() {
        MouseHandler fakeHandler = new MouseHandler();
        fakeHandler.mouseButton = 1;
        fakeHandler.mousePos = new Point(400, 400);
        grid.checkPressed(fakeHandler);
    }

    @Test
    void drawingBordersWorks() {
        assertTrue(grid.containNode(Node.BORDER));
    }

    @Test
    void clearBordersButtonWorks() {
        grid.clearType(Node.BORDER);
        assertFalse(grid.containNode(Node.BORDER));
    }

}
