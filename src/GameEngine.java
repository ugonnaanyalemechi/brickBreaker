import java.awt.*; // for the CustomColor & Graphics classes
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameEngine extends JPanel implements KeyListener, ActionListener {
    private final int width;
    private final int height;
    private final GameLogic gameLogic;
    private final Bricks bricks;
    private final Paddle paddle;
    private final Ball ball;
    private final Timer timer;

    public GameEngine(int width, int height) {
        this.width = width;
        this.height = height;

        bricks = Bricks.getInstance(4, 7);
        gameLogic = GameLogic.getInstance();
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

        // game elements
        bricks.paintComponent((Graphics2D) g2d);
        paddle.paintComponent((Graphics2D) g2d);
        ball.paintComponent((Graphics2D) g2d);

        // score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("Score: " + gameLogic.score, 555, 30);

        if (ball.ballPosY > 700) {
            gameLogic.ongoingGame = false;
            g2d.setColor(Color.black);
            g2d.fill(r);

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", 210, 350);

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press Any Key to Reset", 240, 390);
        }

        g2d.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (gameLogic.ongoingGame) {
            gameLogic.handleBallBorderCollisions();
            gameLogic.handleBallPaddleCollisions();
            gameLogic.handleBallBrickCollisions();
        }

        repaint(); // calls the paint method over and over again to update the screen
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (paddle.paddleX >= width - 110)
                paddle.paddleX = width - 110;
            else {
                gameLogic.ongoingGame = true;
                paddle.moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddle.paddleX < 10)
                paddle.paddleX = 0;
            else {
                gameLogic.ongoingGame = true;
                paddle.moveLeft();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
