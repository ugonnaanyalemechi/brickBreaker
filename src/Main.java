import javax.swing.*;

public class Main {
    public static int width = 700;
    public static int height = 700;

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        GameEngine gameEngine = new GameEngine(width, height);
        frame.setSize(width, height);
        frame.setTitle("Brick Breaker");
        frame.setResizable(false);
        frame.add(gameEngine);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}