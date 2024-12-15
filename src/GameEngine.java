import java.awt.*;

public class GameEngine {
    private static GameEngine instance;
    public boolean ongoingGame;
    private int score;
    private int totalBricks;
    private final Ball ball;
    private final Paddle paddle;

    private GameEngine() {
        score = 0;
        totalBricks = 28;
        ball = Ball.getInstance();
        paddle = Paddle.getInstance();
    }

    public static GameEngine getInstance() {
        if (instance == null)
            instance = new GameEngine();
        return instance;
    }

    public void handleBallBorderCollisions() {
        if (ongoingGame) {
            ball.ballPosX += ball.ballDirX;
            ball.ballPosY += ball.ballDirY;
            if (ball.ballPosX < 0)
                ball.ballDirX = -ball.ballDirX;
            if (ball.ballPosX > Main.width - 40)
                ball.ballDirX = -ball.ballDirX;
            if (ball.ballPosY < 0)
                ball.ballDirY = -ball.ballDirY;
        }
    }

    public void handleBallPaddleCollisions() {
        Rectangle ballCollisionBox = new Rectangle(ball.ballPosX, ball.ballPosY, 20, 20);
        Rectangle paddleCollisionBox = new Rectangle(paddle.paddleX, 630, 100, 8);

        if (ballCollisionBox.intersects(paddleCollisionBox))
            ball.ballDirY = -ball.ballDirY;
    }


}
