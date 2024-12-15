
public class GameEngine {
    private boolean ongoingGame;
    private int score;
    private int totalBricks;
    private final Paddle paddle;

    public GameEngine() {
        ongoingGame = false;
        score = 0;
        totalBricks = 28;
        paddle = Paddle.getInstance();
    }

    public void moveRight() {
        ongoingGame = true;
        paddle.paddleX += 10;
    }

    public void moveLeft() {
        ongoingGame = true;
        paddle.paddleX -= 10;
    }

}
