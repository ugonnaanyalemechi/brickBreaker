import java.awt.*;

public class GameLogic {
    private static GameLogic instance;
    public int score;
    public int totalBricks;
    private final Ball ball;
    private final Paddle paddle;
    private final Bricks bricks;

    private GameLogic() {
        score = 0;
        totalBricks = 28;
        ball = Ball.getInstance();
        paddle = Paddle.getInstance();
        bricks = Bricks.getInstance(4, 7);
    }

    public static GameLogic getInstance() {
        if (instance == null)
            instance = new GameLogic();
        return instance;
    }

    public void handleBallBorderCollisions() {
        ball.ballPosX += ball.ballDirX;
        ball.ballPosY += ball.ballDirY;
        if (ball.ballPosX < 0)
            ball.ballDirX = -ball.ballDirX;
        if (ball.ballPosX > Main.width - 40)
            ball.ballDirX = -ball.ballDirX;
        if (ball.ballPosY < 0)
            ball.ballDirY = -ball.ballDirY;
    }

    public void handleBallPaddleCollisions() {
        Rectangle paddleCollisionBox = new Rectangle(paddle.paddleX, 630, 100, 8);
        Rectangle ballCollisionBox = new Rectangle(ball.ballPosX, ball.ballPosY, 20, 20);

        if (ballCollisionBox.intersects(paddleCollisionBox))
            ball.ballDirY = -ball.ballDirY;
    }

    public void handleBallBrickCollisions() {
        boolean ballHitBrick = false;
        for (int i = 0; i < bricks.bricks.length; i++) {
            for (int j = 0; j < bricks.bricks[0].length; j++) {
                if (bricks.bricks[i][j] > 0) {
                    int brickX = j * bricks.brickWidth + 80;
                    int brickY = i * bricks.brickHeight + 50;
                    int brickWidth = bricks.brickWidth;
                    int brickHeight = bricks.brickHeight;

                    Rectangle brickCollisionBox = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                    Rectangle ballCollisionBox = new Rectangle(ball.ballPosX, ball.ballPosY, 20, 20);

                    if (ballCollisionBox.intersects(brickCollisionBox)) {
                        bricks.makeBrickDisappear(0, i, j);
                        totalBricks--;
                        score += 5;

                        if (ball.ballPosX + 19 <= brickCollisionBox.x || ball.ballPosX + 1 >= brickCollisionBox.x + brickCollisionBox.width)
                            ball.ballDirX = -ball.ballDirX;
                        else
                            ball.ballDirY = -ball.ballDirY;

                        ballHitBrick = true;
                        break;
                    }
                }
            }
            if (ballHitBrick)
                break;
        }
    }
}
