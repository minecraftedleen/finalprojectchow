import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity{

    public Player(String leftImg, String rightImg, String upImg, String downImg, ArrayList<Wall> walls) {
        super(leftImg, rightImg, upImg, downImg, walls);
    }

}