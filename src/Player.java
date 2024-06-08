import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity{
    private int money;

    public Player(String leftImg, String rightImg, String upImg, String downImg, ArrayList<Wall> walls) {
        super(leftImg, rightImg, upImg, downImg, walls);
        money = 0;
    }

    public int getMoney() {
        return money;
    }

    public void increaseMoney(int m) {
        money += m;
    }
}