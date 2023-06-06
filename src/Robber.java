import javax.swing.*;
import java.awt.*;
class Robber extends Sprite {
    public Robber(int x, int y) {
        super();
        image = new ImageIcon("images/robber2.png");
        Image image2 = image.getImage();
        int width = 35;
        int height = 35;
        Image resizedImage = image2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        image = new ImageIcon(resizedImage);
        setLocation(x, y);
    }
}