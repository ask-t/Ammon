import javax.swing.*;
import java.awt.*;
public class Ammon extends Sprite {
    public Ammon(int x, int y) {
        super();
        image = new ImageIcon("images/Ammon2.png");
        // resize image
        Image image2 = image.getImage();
        int width = 35;
        int height = 35;
        Image resizedImage = image2.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        image = new ImageIcon(resizedImage);
        setLocation(x, y);
    }
}