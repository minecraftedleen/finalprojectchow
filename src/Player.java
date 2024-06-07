import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{
    private int money;

    public Player(String leftImg, String rightImg, String upImg, String downImg) {
        super(leftImg, rightImg, upImg, downImg);
        money = 0;
        setXCoord(16);
        setYCoord(480);
    }

    public int getMoney() {
        return money;
    }

    public void increaseMoney(int m) {
        money += m;
    }
}