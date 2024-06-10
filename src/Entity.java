import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

public class Entity {
    private final double MOVE_AMT = 16;
    private BufferedImage right;
    private BufferedImage left;
    private BufferedImage up;
    private BufferedImage down;
    private String direction;
    private int health;
    private double xCoord;
    private double yCoord;
    private ArrayList<Wall> walls;


    public Entity(String leftImg, String rightImg, String upImg, String downImg, ArrayList<Wall> w) {
        direction = "right";
        try {
            left = ImageIO.read(new File(leftImg));
            right = ImageIO.read(new File(rightImg));
            up = ImageIO.read(new File(upImg));
            down = ImageIO.read(new File(downImg));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        walls = w;
        health = 2;
    }

    public int getxCoord() {
        return (int) xCoord;
    }

    public int getyCoord() {
        return (int) yCoord;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth() {
        health--;
    }

    public void setXCoord(double x) {
        xCoord += x;
    }

    public void setYCoord(double y) {
        yCoord += y;
    }

    public String getDirection() {
        return direction;
    }


    public void faceRight() {
        direction = "right";
    }

    public void faceLeft() {
        direction = "left";
    }

    public void faceUp() {
        direction = "up";
    }

    public void faceDown() {
        direction = "down";
    }

    public void moveRight() {
        if (xCoord + MOVE_AMT <= 480) {
            xCoord += MOVE_AMT;
        }
        if (wallCollideCheck()) {
            xCoord -= MOVE_AMT;
        }
    }


    public void moveLeft() {
        if (xCoord - MOVE_AMT >= 16) {
            xCoord -= MOVE_AMT;
        }
        if (wallCollideCheck()) {
            xCoord += MOVE_AMT;
        }
    }

    public void moveUp() {
        if (yCoord - MOVE_AMT >= 16) {
            yCoord -= MOVE_AMT;
        }
        if (wallCollideCheck()) {
            yCoord += MOVE_AMT;
        }
    }

    public void moveDown() {
        if (yCoord + MOVE_AMT <= 480) {
            yCoord += MOVE_AMT;
        }
        if (wallCollideCheck()) {
            yCoord -= MOVE_AMT;
        }
    }



    public BufferedImage getEntityImage() {
        if (direction == "right") {
            return right;
        } else if (direction == "left") {
            return left;
        } else if (direction == "up") {
            return up;
        } else {
            return down;
        }
    }

    private boolean wallCollideCheck() {
        for (Wall wall : walls) {
            if (entityRect().intersects(wall.wallRect())) {
                return true;
            }
        }
        return false;
    }

    // we use a "bounding Rectangle" for detecting collision
    public Rectangle entityRect() {
        int imageHeight = getEntityImage().getHeight();
        int imageWidth = getEntityImage().getWidth();
        Rectangle rect = new Rectangle((int) xCoord, (int) yCoord, imageWidth, imageHeight);
        return rect;
    }

}
