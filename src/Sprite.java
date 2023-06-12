import javax.swing.*;
import java.awt.*;

abstract class Sprite {
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
        /*
        Draw background of the map. Each square size is 50x50px (default) check Style.java
        The Number of Squares is 10x10
        The size of Rectangle is 50px x 10 squares = 500px (default)
        */
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

    public Point getter(){
        return relativePosition;
    }
}