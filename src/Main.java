import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Main extends JPanel {
    private int numRows;
    private int numColumns;
    private Ammon ammon;
    private ArrayList<Sheep> sheepList;
    private ArrayList<Robber> robberList;
    private ArrayList<Tree> treeList;
    private ArrayList<Water> waterList;

    public Main() {
        numRows = 10;
        numColumns = 10;
        sheepList = new ArrayList<>();
        robberList = new ArrayList<>();
        treeList = new ArrayList<>();
        waterList = new ArrayList<>();

        ammon = new Ammon(2, 3);
        sheepList.add(new Sheep(4, 2));
        sheepList.add(new Sheep(6, 5));
        robberList.add(new Robber(1, 4));
        robberList.add(new Robber(8, 7));
        treeList.add(new Tree(0, 0));
        treeList.add(new Tree(1, 0));
        treeList.add(new Tree(2, 0));
        treeList.add(new Tree(3, 0));
        treeList.add(new Tree(4, 0));
        treeList.add(new Tree(5, 0));
        treeList.add(new Tree(6, 0));
        treeList.add(new Tree(7, 0));
        treeList.add(new Tree(8, 0));
        treeList.add(new Tree(9, 0));
        treeList.add(new Tree(9, 9));
        waterList.add(new Water(3, 6));
        waterList.add(new Water(7, 1));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);

        // Draw background of the map
        g.setColor(new Color(255, 179, 102)); // Earthy color
        g.fillRect(15, 15, numColumns * 50, numRows * 50);

        // Draw map border
        g.setColor(Color.BLACK);
        g.drawRect(15, 15, numColumns * 50, numRows * 50);

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
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sprite Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(530, 560);

        Main mainPanel = new Main();
        frame.add(mainPanel);

        frame.setVisible(true);
    }
}