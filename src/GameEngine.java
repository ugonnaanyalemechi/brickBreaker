import java.awt.*;
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
    public boolean ongoingGame;

    public GameEngine(int width, int height) {
        this.width = width;
        this.height = height;

        gameLogic = GameLogic.getInstance();
        bricks = Bricks.getInstance(4, 7);
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
        Rectangle background = new Rectangle(0, 0, width, height);
        g2d.setColor(Color.black);
        g2d.fill(background);

        // game elements
        bricks.paintComponent((Graphics2D) g2d);
        paddle.paintComponent((Graphics2D) g2d);
        ball.paintComponent((Graphics2D) g2d);

        // score
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.BOLD, 25));
        g2d.drawString("Score: " + gameLogic.score, 555, 30);

        // game events
        if (ball.ballPosY > 700)
            handleLossEvent((Graphics2D) g2d, background);
        if (gameLogic.totalBricks == 0)
            handleWinEvent((Graphics2D) g2d, background);

        g2d.dispose();
    }

    public void handleLossEvent(Graphics2D g2d, Rectangle background) {
        ongoingGame = false;
        g2d.setColor(Color.black);
        g2d.fill(background);

        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        g2d.drawString("Game Over", 210, 350);

        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Press Any Key to Reset", 240, 390);
    }

    public void handleWinEvent(Graphics2D g2d, Rectangle background) {
        ongoingGame = false;
        g2d.setColor(Color.black);
        g2d.fill(background);

        ImageIcon image = new ImageIcon("src/../images/veerasamy.png");
        image.paintIcon(this, g2d, 240, 150);

        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.BOLD, 50));
        g2d.drawString("You Won!", 230, 500);

        g2d.setColor(Color.white);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Press Any Key to Reset", 240, 540);
    }

    public void resetGame() {
        ball.ballPosX = 120;
        ball.ballPosY = 270;
        ball.ballDirX = -3;
        ball.ballDirY = -3;
        paddle.paddleX = 310;
        gameLogic.score = 0;
        gameLogic.totalBricks = 28;
        bricks.resetBricks();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (ongoingGame) {
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
                ongoingGame = true;
                paddle.moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddle.paddleX < 10)
                paddle.paddleX = 0;
            else {
                ongoingGame = true;
                paddle.moveLeft();
            }
        }

        if (!ongoingGame)
            resetGame();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
