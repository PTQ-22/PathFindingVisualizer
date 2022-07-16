import java.awt.*;
import java.util.LinkedList;

public class Button extends Rect {
    private static final LinkedList<Button> buttons = new LinkedList<>();
    private final Color hoverColor;
    private final Color mainColor;
    private final Color borderColor = new Color(0x208B70);
    private final String text;
    private final Font font = new Font("FreeSans", Font.BOLD, 30);

    protected Button(Rectangle rectangle, Color color, Color hoverColor, String text) {
        super(rectangle, color);
        Button.buttons.add(this);
        this.hoverColor = hoverColor;
        this.mainColor = color;
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    @Override
    protected void draw(Graphics2D g2) {
        g2.setColor(borderColor);
        int borderSize = 5;
        g2.fillRect(rectangle.x - borderSize, rectangle.y - borderSize,
                rectangle.width + 2 * borderSize, rectangle.height + 2 * borderSize);

        g2.setColor(color);
        g2.fill(rectangle);

        g2.setColor(borderColor);
        g2.setFont(font);
        // draw centered text
        FontMetrics fontMetrics = g2.getFontMetrics();
        g2.drawString(text,
                rectangle.x + ((rectangle.width - fontMetrics.stringWidth(text)) / 2)
                , rectangle.y + (((rectangle.height - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent()));
    }

    @Override
    public boolean isMouse(Point mousePos) {
        if (rectangle.contains(mousePos)) {
            setColor(hoverColor);
            return true;
        }
        setColor(mainColor);
        return false;
    }

    public static void drawButtons(Graphics2D g2) {
        for (Button b : buttons) {
            b.draw(g2);
        }
    }
}
