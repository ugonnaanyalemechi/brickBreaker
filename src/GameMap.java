import java.awt.*; // for the CustomColor & Graphics classes
import javax.swing.*;

public class GameMap extends JPanel {
    private final int width;
    private final int height;
    private final Bricks bricks;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        bricks = new Bricks(4, 7);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints( // used to make graphics smoother
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        // background
        Rectangle r = new Rectangle(0, 0, width, height);
        g2d.setColor(Color.black);
        g2d.fill(r);

        // bricks
        bricks.paintComponent((Graphics2D) g2d);

    }
}
