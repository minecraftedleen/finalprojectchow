import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener, ActionListener {
    private BufferedImage background;
    private Player player;
    private Player player2;
    private boolean[] pressedKeys;
    private Timer playerMoveCooldownTimer;
    private Timer shootCooldownTimer;
    private ArrayList<Wall> walls;
    private ArrayList<Bullet> bullets;



    public GraphicsPanel() {
        try {
            background = ImageIO.read(new File("src/map.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        walls = new ArrayList<Wall>();
        createWalls();
        bullets = new ArrayList<Bullet>();
        player = new Player("src/playerLeft.png", "src/playerRight.png", "src/playerUp.png", "src/playerDown.png", walls);
        player2 = new Player("src/player2Left.png", "src/player2Right.png", "src/player2Up.png", "src/player2Down.png", walls);
        player.setXCoord(16);
        player.setYCoord(480);
        player2.setXCoord(480);
        player2.setYCoord(16);
        pressedKeys = new boolean[128]; // 128 keys on keyboard, max keycode is 127
        playerMoveCooldownTimer = new Timer (100, this);
        playerMoveCooldownTimer.start();
        shootCooldownTimer = new Timer (100, this);
        shootCooldownTimer.start();
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true); // this line of code + one below makes this panel active for keylistener events
        requestFocusInWindow(); // see comment above
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // just do this
        g.drawImage(background, 0, 0, null);  // the order that things get "painted" matter; we put background down first
        g.drawImage(player.getEntityImage(), player.getxCoord(), player.getyCoord(), null);
        g.drawImage(player2.getEntityImage(), player2.getxCoord(), player2.getyCoord(), null);
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            g.drawImage(bullet.getBulletImage(), bullet.getxCoord(), bullet.getyCoord(), null);
            bullet.move();
        }

        // for seeing collidable walls
        /*
        for (Wall wall: walls) {
            g.fillRect(wall.getxCoord(), wall.getyCoord(), wall.getWidth(), wall.getHeight());
        }*/
    }

    public void move() {
        if (pressedKeys[65]) {
            player.faceLeft();
            player.moveLeft();
        }

        // player moves right (D)
        if (pressedKeys[68]) {
            player.faceRight();
            player.moveRight();
        }

        // player moves up (W)
        if (pressedKeys[87]) {
            player.faceUp();
            player.moveUp();
        }

        // player moves down (S)
        if (pressedKeys[83]) {
            player.faceDown();
            player.moveDown();
        }

        if (pressedKeys[37]) {
            player2.faceLeft();
            player2.moveLeft();
        }


        if (pressedKeys[39]) {
            player2.faceRight();
            player2.moveRight();
        }


        if (pressedKeys[38]) {
            player2.faceUp();
            player2.moveUp();
        }

        if (pressedKeys[40]) {
            player2.faceDown();
            player2.moveDown();
        }


    }

    public void shoot() {
        if (pressedKeys[32]) {
            bullets.add(new Bullet("src/bullet.png", player.getDirection(), player, player.getxCoord(), player.getyCoord()));
        }

        if (pressedKeys[80]) {
            bullets.add(new Bullet("src/bullet.png", player2.getDirection(), player2, player2.getxCoord(), player2.getyCoord()));
        }
    }

    // ----- KeyListener interface methods -----
    public void keyTyped(KeyEvent e) { } // unimplemented

    public void keyPressed(KeyEvent e) {
        // see this for all keycodes: https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
        // A = 65, D = 68, S = 83, W = 87, left = 37, up = 38, right = 39, down = 40, space = 32, enter = 10
        int key = e.getKeyCode();
        pressedKeys[key] = true;
    }
    public Dimension getPreferredSize() {
        return new Dimension(512, 512);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys[key] = false;
    }

    // ----- MouseListener interface methods -----
    public void mouseClicked(MouseEvent e) { }  // unimplemented; if you move your mouse while clicking,
    // this method isn't called, so mouseReleased is best

    public void mousePressed(MouseEvent e) { } // unimplemented

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {  // left mouse click

        }
    }

    public void mouseEntered(MouseEvent e) { } // unimplemented

    public void mouseExited(MouseEvent e) { } // unimplemented

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(playerMoveCooldownTimer)) {
            move();
        }
        if (e.getSource().equals(shootCooldownTimer)) {
            shoot();
        }
    }

    public void createWalls() {
        walls.add(new Wall(2, 4));
        walls.add(new Wall(2, 5));
        walls.add(new Wall(3, 5));
        walls.add(new Wall(5, 3));
        walls.add(new Wall(6, 3));
        walls.add(new Wall(7, 3));
        walls.add(new Wall(8, 2));
        walls.add(new Wall(9, 2));
        walls.add(new Wall(9, 1));
        walls.add(new Wall(16, 1));
        walls.add(new Wall(16, 2));
        walls.add(new Wall(16, 4));
        walls.add(new Wall(16, 5));
        walls.add(new Wall(20, 5));
        walls.add(new Wall(20, 4));
        walls.add(new Wall(19, 4));
        walls.add(new Wall(20, 1));
        walls.add(new Wall(21, 1));
        walls.add(new Wall(25, 1, 2, 1));
        walls.add(new Wall(29, 3));
        walls.add(new Wall(30, 3));
        walls.add(new Wall(30, 4));
        walls.add(new Wall(7, 6, 21, 1));
        walls.add(new Wall(10, 7, 1, 2));
        walls.add(new Wall(13, 7, 1, 2));
        walls.add(new Wall(21, 7));
        walls.add(new Wall(30, 9));
        walls.add(new Wall(29, 12));
        walls.add(new Wall(28, 14, 3, 1));
        walls.add(new Wall(28, 16, 1, 4));
        walls.add(new Wall(26, 21));
        walls.add(new Wall(30, 22));
        walls.add(new Wall(28, 24));
        walls.add(new Wall(23, 26));
        walls.add(new Wall(13, 30));
        walls.add(new Wall(8, 26));
        walls.add(new Wall(8, 24));
        walls.add(new Wall(4, 23));
        walls.add(new Wall(1, 27, 1, 3));
        walls.add(new Wall(4, 29, 2, 1));
        walls.add(new Wall(14, 22));
        walls.add(new Wall(14, 19));
        walls.add(new Wall(14, 13, 2, 1));
        walls.add(new Wall(17, 15, 1, 2));
        walls.add(new Wall(17, 11, 2, 1));
        walls.add(new Wall(2, 13));
        walls.add(new Wall(4, 13));
        walls.add(new Wall(6, 15));
        walls.add(new Wall(3, 9, 1, 10));
        walls.add(new Wall(4, 18, 4, 1));
        walls.add(new Wall(7, 12, 1, 6));
        walls.add(new Wall(10, 12, 1, 7));
        walls.add(new Wall(11, 18, 4, 1));
        walls.add(new Wall(16, 12, 3, 3));
        walls.add(new Wall(18, 19, 4, 1));
        walls.add(new Wall(20, 18, 4, 1));
        walls.add(new Wall(23, 16, 1, 2));
        walls.add(new Wall(19, 22, 6, 1));
        walls.add(new Wall(25, 25, 1, 5));
        walls.add(new Wall(22, 25, 1, 5));
        walls.add(new Wall(28, 26, 3, 4));
        walls.add(new Wall(20, 25, 1, 6));
        walls.add(new Wall(13, 25, 6, 1));
        walls.add(new Wall(9, 24, 2, 3));
        walls.add(new Wall(8, 29, 12, 1));
        walls.add(new Wall(3, 21, 12, 1));
        walls.add(new Wall(3, 22, 1, 8));
        walls.add(new Wall(4, 29, 2, 1));
        walls.add(new Wall(1, 27, 1, 3));
        walls.add(new Wall(25, 10, 1, 13));
        walls.add(new Wall(26, 10, 1, 2));
        walls.add(new Wall(19, 12, 1, 2));

    }
}