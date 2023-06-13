import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Main extends JPanel implements KeyListener {
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
        addKeyListener(this);
        setFocusable(true);

        //tell our class to handle its own key events...
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);

        /*
        Draw background of the map. Each square size is 50x50px (default) check Style.java
        The Number of Squares is 10x10
        The size of Rectangle is 50px x 10 squares = 500px (default)
        */
        g.setColor(new Color(222, 132, 20));
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
//    Don't use those
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }


    // setting up Dpad
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Arrow key handling
        if (key == KeyEvent.VK_LEFT) {
            moveAmmon(-1, 0);
        } else if (key == KeyEvent.VK_RIGHT) {
            moveAmmon(1, 0);
        } else if (key == KeyEvent.VK_UP) {
            moveAmmon(0, -1);
        } else if (key == KeyEvent.VK_DOWN) {
            moveAmmon(0, 1);
        }
    }
    //method of moving Ammon
    private void moveAmmon(int dx, int dy) {
        Point nextPos = ammon.getter();
        nextPos.translate(dx, dy); // update relative position

        if (isWithinMapBounds(nextPos) && !hitATree(nextPos)) {
            ammon.setLocation(nextPos); // finalize
            repaint(); // update the location of image
            System.out.println("True");
        }
        else{
            nextPos.translate(-dx, -dy); // reset position.
            System.out.println("False");
        }
    }

    // prevent going to out of the map
    private boolean isWithinMapBounds(Point p) {
        return p.x >= 0 && p.x < numColumns && p.y >= 0 && p.y < numRows;
    }

    // prevent going into the trees.
    private boolean hitATree(Point p) {
        for (Tree tree : treeList) {
            if (tree.getter().equals(p)) {
                return true;
            }
        }
        return false;
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