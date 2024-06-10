import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Bullet {
    private final double MOVE_AMT = 16;
    private BufferedImage bulletImage;
    private Player shooter;
    private String direction;
    private double xCoord;
    private double yCoord;

    public Bullet(String img, String direction, Player shooter, double xCoord, double yCoord) {
        this.shooter = shooter;
        this.direction = direction;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        try {
            bulletImage = ImageIO.read(new File(img));
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

    public Player getShooter() {
        return shooter;
    }

    public void move() {
        if (direction == "right") {
            xCoord += MOVE_AMT;
        } else if (direction == "left") {
            xCoord -= MOVE_AMT;
        } else if (direction == "up") {
            yCoord -= MOVE_AMT;
        } else {
            yCoord += MOVE_AMT;
        }
    }

    public BufferedImage getBulletImage() {
        return bulletImage;
    }

    public Rectangle bulletRect() {
        int imageHeight = getBulletImage().getHeight();
        int imageWidth = getBulletImage().getWidth();
        Rectangle rect = new Rectangle((int) xCoord, (int) yCoord, imageWidth, imageHeight);
        return rect;
    }


}
