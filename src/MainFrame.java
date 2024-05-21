import javax.swing.*;
public class MainFrame implements Runnable{
    private GraphicsPanel panel;

    public MainFrame() {
        JFrame frame = new JFrame("Edmund's Proyecto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocation(300, 50);

        panel = new GraphicsPanel();
        frame.add(panel);
        frame.setVisible(true);
        Thread myThread = new Thread(this);
        myThread.start();
    }

    public void run() {
        while (true) {
            panel.repaint();
        }
    }

}
