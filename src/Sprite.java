import javax.swing.*;
import java.awt.*;

public class Sprite {
    protected Point absolutePosition;
    protected Point relativePosition;
    protected ImageIcon image;
    Style style = new Style();
    private int squareSize = style.squareSize;
    private Point margin = style.margin;

    public Sprite() {
        absolutePosition = new Point();
        relativePosition = new Point();
        image = null;
    }

    public void setLocation(int x, int y) {
        relativePosition.setLocation(x, y);
        absolutePosition.setLocation(relativePosition.x * squareSize+margin.x, relativePosition.y * squareSize+margin.y);
    }

    public void setLocation(Point p) {
        if (p == null) {
            absolutePosition = null;
            relativePosition = null;
        } else {
            setLocation(p.x, p.y);
        }
    }

    public void draw(Graphics g) {
        if (absolutePosition != null && image != null) {
            image.paintIcon(null, g, absolutePosition.x, absolutePosition.y);
        }
    }
}