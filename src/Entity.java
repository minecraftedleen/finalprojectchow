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
    private double xCoord;
    private double yCoord;
    private static boolean[][] walls;

    public Entity(String leftImg, String rightImg, String upImg, String downImg) {
        direction = "right";
        try {
            left = ImageIO.read(new File(leftImg));
            right = ImageIO.read(new File(rightImg));
            up = ImageIO.read(new File(upImg));
            down = ImageIO.read(new File(downImg));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getxCoord() {
        return (int) xCoord;
    }

    public int getyCoord() {
        return (int) yCoord;
    }

    public void setXCoord(double x) {
        xCoord += x;
    }

    public void setYCoord(double y) {
        yCoord += y;
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
        if (xCoord + MOVE_AMT <= 920) {
            xCoord += MOVE_AMT;
        }
    }


    public void moveLeft() {
        if (xCoord - MOVE_AMT >= 0) {
            xCoord -= MOVE_AMT;
        }
    }

    public void moveUp() {
        if (yCoord - MOVE_AMT >= 0) {
            yCoord -= MOVE_AMT;
        }
    }

    public void moveDown() {
        if (yCoord + MOVE_AMT <= 435) {
            yCoord += MOVE_AMT;
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

    // we use a "bounding Rectangle" for detecting collision
    public Rectangle entityRect() {
        int imageHeight = getEntityImage().getHeight();
        int imageWidth = getEntityImage().getWidth();
        Rectangle rect = new Rectangle((int) xCoord, (int) yCoord, imageWidth, imageHeight);
        return rect;
    }
}
