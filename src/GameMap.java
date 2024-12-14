import java.awt.*; // for the Color & Graphics classes
import java.awt.geom.*; // for creating shapes and paths
import javax.swing.*;

public class GameMap extends JComponent {
    private int width;
    private int height;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // background
        Rectangle2D.Double r = new Rectangle2D.Double(0, 0, width, height);
        g2d.setColor(new Color(0, 0, 0));
        g2d.fill(r);
    }
}
