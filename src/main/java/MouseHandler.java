import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
    public boolean clicked = false;
    public Point mousePos = new Point(0, 0);
    public boolean isPressed = false;
    public int mouseButton = 0;

    public boolean isClicked() {
        if (clicked) {
            clicked = false;
            return true;
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        clicked = true;
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mousePos.x = mouseEvent.getX();
        mousePos.y = mouseEvent.getY();
    }

    // these methods must be implemented even if they are empty
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        isPressed = true;
        mouseButton = mouseEvent.getButton();
    }
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        isPressed = false;
    }
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}
    @Override
    public void mouseExited(MouseEvent mouseEvent) {}
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        mousePos.x = mouseEvent.getX();
        mousePos.y = mouseEvent.getY();
    }
}
