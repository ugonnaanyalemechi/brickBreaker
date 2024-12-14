import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int width = 700;
        int height = 700;

        JFrame frame = new JFrame();
        GameMap gameMap = new GameMap(width, height);
        frame.setSize(width, height);
        frame.setTitle("Brick Breaker");
        frame.add(gameMap);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}