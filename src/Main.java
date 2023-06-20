import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends JPanel implements KeyListener {
    // fields
    Style style = new Style();
    private final int numRows = style.numRows;
    private final int numColumns = style.numColumns;
    private final int squareSize = style.squareSize;
    private final Point margin = style.margin;
    private final Ammon ammon;
    private final ArrayList<Sheep> sheepList;
    private final ArrayList<Robber> robberList;
    private final ArrayList<Tree> treeList;
    private final ArrayList<Water> waterList;

    //this is used as a polymorphism.
    private final ArrayList<Sprite> objects;
    private int numSheep;
    private int numRobbers;


    // Constructor
    public Main() {

        // Array
        sheepList = new ArrayList<>();
        robberList = new ArrayList<>();
        treeList = new ArrayList<>();
        waterList = new ArrayList<>();
        objects = new ArrayList<>();

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
        robberList.add(new Robber(1, 0));
        robberList.add(new Robber(7, 2));
        robberList.add(new Robber(5, 5));
        robberList.add(new Robber(9, 7));
        robberList.add(new Robber(2, 9));
        addKeyListener(this);
        setFocusable(true);

        //tell our class to handle its own key events...
        requestFocusInWindow();

        // add all objects from sprite lists.
        objects.add(ammon);
        objects.addAll(sheepList);
        objects.addAll(treeList);
        objects.addAll(waterList);
        objects.addAll(robberList);


        // count how many sheep in the map.
        numSheep = sheepList.size();
        System.out.println("Sheep is "+ numSheep);
        numRobbers = robberList.size();
        System.out.println("robbers are " +numRobbers);

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


        // Draw map border
        g.setColor(Color.BLACK);
        g.drawRect(margin.x, margin.y, numColumns * squareSize, numRows * squareSize);

        // Draw all the sprites
        for(Sprite object: objects){
            object.draw(g);
        }
        g.setColor(Color.BLACK);
        g.drawString("Sheep Remaining: "+ numSheep, margin.x,numColumns*squareSize+margin.y*2 );
        g.drawString("Robbers Remaining: "+ numRobbers, margin.x+ numRows*squareSize/2+margin.x*4,numColumns*squareSize+margin.y*2 );

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
        String status = null;
        Point nextPos = ammon.getter();
        switch (key) {
            case KeyEvent.VK_UP -> {
                nextPos.y--;
                status = "up";
            }
            case KeyEvent.VK_DOWN -> {
                nextPos.y++;
                status = "down";
            }
            case KeyEvent.VK_LEFT -> {
                nextPos.x--;
                status = "left";
            }
            case KeyEvent.VK_RIGHT -> {
                nextPos.x++;
                status = "right";
            }
            case KeyEvent.VK_W -> {
                shoot("north");
                System.out.println("press W");
            }
            case KeyEvent.VK_S -> {
                shoot("south");
                System.out.println("press S");
            }
            case KeyEvent.VK_D -> {
                System.out.println("press D");
                shoot("east");
            }
            case KeyEvent.VK_A -> {
                System.out.println("press A");
                shoot("west");
            }
            default -> {
            }
        }

        if (isWithinMapBounds(nextPos)) {
        /*
        When the boolean is true, this sheep will disappear.
        Also, the counter of sheep will negative one.
        when the counter is zero, the game will be finished.
        In terms of water and robber, it is same methods with sheep.
        when the status is true, some action will happen.
        */
            if (!(hitATree(nextPos))) {
                ammon.setLocation(nextPos); // finalize
                repaint(); // update the location of image
                for (Sheep sheep : sheepList) {
                    if (ammon.isTouching(sheep)) {
                        sheepList.remove(sheep);
                        sheep.setLocation(null);
                        numSheep--;
                        if (numSheep == 0) {
                            int option = JOptionPane.showConfirmDialog(this, "Congratulations! You rescued all the sheep! \n Do you want to continue?", "you clear!", JOptionPane.YES_NO_OPTION);
                            if (option == 0) {
                                reset();
                                break;
                            } else if (option == 1) {
                                System.exit(0);
                            }
                        }
                    }
                }
                for (Water water : waterList) {
                    if (ammon.isTouching(water)) {

                        int option = JOptionPane.showConfirmDialog(this, "Sorry, Ammon died in the water. \n Do you want to continue?", "you are dead", JOptionPane.YES_NO_OPTION);
                        if (option == 0) {
                            reset();
                            break;
                        } else if (option == 1) {
                            System.exit(0);
                        }
                    }
                }
                for (Robber robber : robberList) {
                    if (ammon.isNear(robber)) {
                        int option = JOptionPane.showConfirmDialog(this, "Sorry, Ammon died near a robber. \n Do you want to continue?", "you are dead", JOptionPane.YES_NO_OPTION);
                        if (option == 0) {
                            reset();
                            break;
                        } else if (option == 1) {
                            System.exit(0);
                        }
                    }
                }
            }else{
                if(Objects.equals(status, "up")){
                    nextPos.y++;
                }
                else if(Objects.equals(status, "down")){
                    nextPos.y--;
                }
                else if(Objects.equals(status, "left")){
                    nextPos.x++;
                }
                else if(Objects.equals(status, "right")){
                    nextPos.x--;
                }
            }
        }
    }
    private void shoot(String direction){
        // get Ammon's relative position
        int x = ammon.getter().x;
        int y = ammon.getter().y;

        // check which sprite objects there are in the same line.
        if (Objects.equals(direction, "north")){
            for (int i = y; i >= 0; i--) {
                Point pos = new Point(x, i);
                if (shootRobber(pos)) {
                    return;
                }
                if (shootSheep(pos)) {
                    return;
                }
                if (hitATree(pos)) {
                    return;
                }
            }
        }
        else if(Objects.equals(direction, "south")){
            for (int i = y; i < numRows; i++) {
                Point pos = new Point(x, i);
                if (shootRobber(pos)) {
                    return;
                }
                if (shootSheep(pos)) {
                    return;
                }
                if (hitATree(pos)) {
                    return;
                }
            }

        }
        else if(Objects.equals(direction, "east")){
            for (int i = x; i < numColumns; i++) {
                Point pos = new Point(i, y);
                if (shootRobber(pos)) {
                    return;
                }
                if (shootSheep(pos)) {
                    return;
                }
                if (hitATree(pos)) {
                    return;
                }
            }
        }
        else if(Objects.equals(direction, "west")){
            for (int i = x; i >= 0; i--) {
                Point pos = new Point(i, y);
                if (shootRobber(pos)) {
                    return;
                }
                if (shootSheep(pos)) {
                    return;
                }
                if (hitATree(pos)) {
                    return;
                }
            }
        }
    }

    // if Ammon shoot robbers and there is no tree
    private boolean shootRobber(Point pos) {
        for (Robber robber : robberList) {
            if (robber.getter().equals(pos)) {
                robberList.remove(robber);
                robber.setLocation(null);
                numRobbers--;
                if (numRobbers == 0) {
                    int option = JOptionPane.showConfirmDialog(this, "Congratulations! You have eliminated all the robbers. \n Do you want to continue?", "you clear", JOptionPane.YES_NO_OPTION);
                    if (option == 0) {
                        reset();
                        break;
                    } else if (option == 1) {
                        System.exit(0);
                    }
                }
                return true;
            }
        }
        return false;
    }
    // if Ammon shoot sheep
    private boolean shootSheep(Point pos) {
        for (Sheep sheep : sheepList) {
            if (sheep.getter().equals(pos)) {
                int option = JOptionPane.showConfirmDialog(this, "Oops! You killed a sheep. \n Do you want to continue?", "you killed a sheep.", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    reset();
                    break;
                } else if (option == 1) {
                    System.exit(0);
                }
                return true;
            }
        }
        return false;
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

    /*
    when it is called, all sheep will set in the default position.
    then sheep counter will be default number.
    After those sheep will be added into sheepList and objects List.
     */
    private void reset(){
        for (Sheep sheep: sheepList){
            sheep.setLocation(null);
        }
        for (Robber robber: robberList){
            robber.setLocation(null);
        }
        sheepList.clear();
        robberList.clear();
        sheepList.add(new Sheep(4, 0));
        sheepList.add(new Sheep(9, 0));
        sheepList.add(new Sheep(0, 3));
        sheepList.add(new Sheep(5, 3));
        sheepList.add(new Sheep(9, 5));
        sheepList.add(new Sheep(2, 6));
        sheepList.add(new Sheep(0, 7));
        sheepList.add(new Sheep(7, 9));
        robberList.add(new Robber(1, 0));
        robberList.add(new Robber(7, 2));
        robberList.add(new Robber(5, 5));
        robberList.add(new Robber(9, 7));
        robberList.add(new Robber(2, 9));
        ammon.setLocation(5,8);
        objects.addAll(sheepList);
        objects.addAll(robberList);
        numSheep = sheepList.size();
        numRobbers = robberList.size();
        repaint();
        System.out.println("everything is reset");
        System.out.println("Sheep is: "+numSheep);

    }
    public static void main(String[] args) {
        Style style = new Style();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(style.windowSize.x, style.windowSize.y);
        window.setContentPane(new Main());
        window.setVisible(true);

    }
}