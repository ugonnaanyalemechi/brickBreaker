import javax.swing.*;
import java.awt.*;

public class Ball extends JComponent {
    private static Ball instance;
    public int ballPosX;
    public int ballPosY;
    public int ballDirX;
    public int ballDirY;

    private Ball() {
        ballPosX = 120;
        ballPosY = 270;
        ballDirX = -3;
        ballDirY = -3;
    }

    public static Ball getInstance() {
        if (instance == null)
            instance = new Ball();
        return instance;
    }

    public void paintComponent(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillOval(ballPosX, ballPosY, 20, 20);
    }
}