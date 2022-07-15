import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ButtonTest {
    private final Color buttonColor = Color.blue;
    private final Color buttonColorHover = Color.darkGray;
    private final Button button = new Button(new Rectangle(0, 0, 100, 100), buttonColor, buttonColorHover, "");

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
}
