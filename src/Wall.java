import java.awt.*;

public class Wall {
    int xCoord;
    int yCoord;
    int width;
    int height;

    public Wall(int x, int y) {
        xCoord = x * 16;
        yCoord = y * 16;
        width = 16;
        height = 16;
    }

    public Wall(int x, int y, int w, int h) {
        xCoord = x * 16;
        yCoord = y * 16;
        width = w * 16;
        height = h * 16;

    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle wallRect() {
        Rectangle rect = new Rectangle((int) xCoord, (int) yCoord, width, height);
        return rect;
    }
}
