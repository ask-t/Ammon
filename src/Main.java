import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JPanel {
    // fields
    Style style = new Style();
    private int numRows = style.numRows;
    private int numColumns = style.numColumns;
    private int squareSize = style.squareSize;
    private Point margin = style.margin;
    private Ammon ammon;
    private ArrayList<Sheep> sheepList;
    private ArrayList<Robber> robberList;
    private ArrayList<Tree> treeList;
    private ArrayList<Water> waterList;

    // Constructor
    public Main() {

        // Array
        sheepList = new ArrayList<>();
        robberList = new ArrayList<>();
        treeList = new ArrayList<>();
        waterList = new ArrayList<>();

        //Objects
        ammon = new Ammon(5, 8);
        sheepList.add(new Sheep(4, 0));
        sheepList.add(new Sheep(9, 0));
        sheepList.add(new Sheep(0, 3));
        sheepList.add(new Sheep(5, 3));
        sheepList.add(new Sheep(9, 5));
        sheepList.add(new Sheep(2, 6));
        sheepList.add(new Sheep(0, 7));
        sheepList.add(new Sheep(7, 9));
        robberList.add(new Robber(2, 0));
        robberList.add(new Robber(7, 2));
        robberList.add(new Robber(4, 4));
        robberList.add(new Robber(9, 7));
        robberList.add(new Robber(2, 9));
        treeList.add(new Tree(0, 4));
        treeList.add(new Tree(0, 5));
        treeList.add(new Tree(1, 4));
        treeList.add(new Tree(1, 5));
        treeList.add(new Tree(4, 1));
        treeList.add(new Tree(5, 1));
        treeList.add(new Tree(4, 2));
        treeList.add(new Tree(5, 2));
        treeList.add(new Tree(4, 9));
        treeList.add(new Tree(5, 9));
        waterList.add(new Water(0, 0));
        waterList.add(new Water(0, 1));
        waterList.add(new Water(0, 8));
        waterList.add(new Water(0, 9));
        waterList.add(new Water(1, 0));
        waterList.add(new Water(1, 1));
        waterList.add(new Water(1, 8));
        waterList.add(new Water(1, 9));
        waterList.add(new Water(4, 5));
        waterList.add(new Water(4, 6));
        waterList.add(new Water(5, 5));
        waterList.add(new Water(5, 6));
        waterList.add(new Water(6, 5));
        waterList.add(new Water(6, 6));
        waterList.add(new Water(8, 3));
        waterList.add(new Water(8, 1));
        waterList.add(new Water(8, 2));
        waterList.add(new Water(8,8 ));
        waterList.add(new Water(8, 9));
        waterList.add(new Water(9, 3));
        waterList.add(new Water(9, 1));
        waterList.add(new Water(9, 2));
        waterList.add(new Water(9,8 ));
        waterList.add(new Water(9, 9));
    }

    @Override
    public void paintComponent(Graphics g) {
        Style style = new Style();
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);

        // Draw background of the map. Each square size is 50x50px (default) check Style.java
        g.setColor(new Color(255, 179, 102));
        g.fillRect(margin.x, margin.y, numColumns * squareSize, numRows * squareSize);


        // Draw all the sprites
        ammon.draw(g);

        for (Sheep sheep : sheepList) {
            sheep.draw(g);
        }

        for (Robber robber : robberList) {
            robber.draw(g);
        }

        for (Tree tree : treeList) {
            tree.draw(g);
        }

        for (Water water : waterList) {
            water.draw(g);
        }
        // Draw map border
        g.setColor(Color.BLACK);
        g.drawRect(margin.x, margin.y, numColumns * squareSize, numRows * squareSize);
    }

    public static void main(String[] args) {
        Style style = new Style();
        JFrame window = new JFrame("Ammon");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(style.windowSize.x, style.windowSize.y);
        window.setContentPane(new Main());
        window.setVisible(true);

    }
}