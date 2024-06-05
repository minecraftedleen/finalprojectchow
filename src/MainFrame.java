import javax.swing.*;
public class MainFrame implements Runnable{
    private GraphicsPanel panel;

    public MainFrame() {
        JFrame frame = new JFrame("Edmund's Proyecto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(525, 550);
        frame.setLocation(300, 50);

        panel = new GraphicsPanel();
        frame.add(panel);
        frame.pack();
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
