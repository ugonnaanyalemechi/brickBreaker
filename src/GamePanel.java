import java.awt.*; // for the CustomColor & Graphics classes
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private final int width;
    private final int height;
    private final GameEngine gameEngine;
    private final Bricks bricks;
    private final Paddle paddle;
    private final Ball ball;
    private final Timer timer;
    private boolean ongoingGame;


    public GamePanel(int width, int height) {
        ongoingGame = true;
        this.width = width;
        this.height = height;

        gameEngine = new GameEngine();
        bricks = new Bricks(4, 7);
        paddle = Paddle.getInstance();
        ball = Ball.getInstance();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        int delay = 8;
        timer = new Timer(delay, this);
        timer.start();
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

        bricks.paintComponent((Graphics2D) g2d);
        paddle.paintComponent((Graphics2D) g2d);
        ball.paintComponent((Graphics2D) g2d);

        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (ongoingGame) {
            ball.ballPosX -= ball.ballDirX;
            ball.ballPosY += ball.ballDirY;
            if (ball.ballPosX < 0)
                ball.ballDirX = -ball.ballDirX;
            if (ball.ballPosX > width - 10)
                ball.ballDirY -= ball.ballDirY;
            if (ball.ballPosY < 0)
                ball.ballDirY = -ball.ballDirY;
        }

        repaint(); // calls the paint over and over again to update the screen
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (paddle.paddleX >= width - 110)
                paddle.paddleX = width - 110;
            else
                gameEngine.moveRight();
        }

        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddle.paddleX < 10)
                paddle.paddleX = 0;
            else
                gameEngine.moveLeft();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
