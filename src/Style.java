import java.awt.*;

public class Style {
    // set up how many squares you want
    protected int numRows = 10;
    protected int numColumns = 10;


    protected int squareSize = 50; // px

    // for JFrame
    protected Point margin = new Point(15,15);
    protected Point windowSize = new Point(numRows*squareSize+margin.x*4 ,numColumns*squareSize+margin.y*5);

}
