import javax.swing.*;
import java.awt.*;

public class Paddle extends JComponent {
    private static Paddle instance;
    public int paddleX;

    private Paddle() {
        paddleX = 310;
    }

    public static Paddle getInstance() {
        if (instance == null)
            instance = new Paddle();
        return instance;
    }

    public void paintComponent(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(paddleX, 630, 100, 8);
    }

    public void moveRight() {
        paddleX += 10;
    }

    public void moveLeft() {
        paddleX -= 10;
    }
}
