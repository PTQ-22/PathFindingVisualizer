import java.awt.*;

public abstract class Rect {
    protected Rectangle rectangle;
    protected Color color;

    protected Rect(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw(Graphics2D g2);

    public abstract boolean isMouse(Point mousePos);
}
