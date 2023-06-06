import java.awt.*;

class Water extends Sprite {
    public Water(int x, int y) {
        super();
        image = null;
        setLocation(x, y);
    }
    @Override
    public void draw(Graphics g) {
        if (absolutePosition != null) {
            g.setColor(new Color(102, 217, 255));
            g.fillRect(absolutePosition.x, absolutePosition.y, 50, 50);
        }
    }
}