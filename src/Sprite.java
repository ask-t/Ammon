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
    // return the relative Position.
    public Point getter(){
        return relativePosition;
    }

    public boolean isTouching(Sprite other){
        if(this.getter().equals(other.getter())){
            return true;
        }else{
            return false;
        }
    }
    public boolean isNear(Sprite other){
        var re = this.getter();
        var ot = other.getter();
        if(re.x == ot.x-1 && re.y == ot.y|| re.x == ot.x+1 && re.y == ot.y||re.x == ot.x &&re.y == ot.y-1||re.x == ot.x &&re.y == ot.y+1 ){
            return true;
        }else{
            return false;
        }
    }
}