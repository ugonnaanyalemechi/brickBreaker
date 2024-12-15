import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;

public class Bricks extends JComponent {
    private int bricks[][];
    private int brickWidth;
    private int brickHeight;
    private int rowSize;
    private int colSize;
    private HashMap<String, Color> colorMap;
    private String[] colors = new String[] {"Red", "Yellow", "Green", "Teal"};

    public Bricks(int rowSize, int colSize) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        bricks = new int[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++)
                bricks[i][j] = 1;
        }

        brickWidth = 540/colSize;
        brickHeight = 140/rowSize;

        colorMap = new HashMap<>();
        colorMap.put("Red", new Color(205, 0, 0));
        colorMap.put("Yellow", new Color(223, 189, 0));
        colorMap.put("Green", new Color(9, 211, 0));
        colorMap.put("Teal", new Color(31, 187, 223));
    }

    protected void paintComponent(Graphics2D g2d) {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (bricks[i][j] > 0) {
                    g2d.setColor(colorMap.get(colors[i]));
                    RoundRectangle2D.Double rr = new RoundRectangle2D.Double(50, 50, j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    g2d.fillRoundRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight, 10, 10);

                    g2d.setStroke(new BasicStroke(3)); // for setting the border for each rect
                    g2d.setColor(Color.black);
                    g2d.drawRoundRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight, 10, 10);
                }
            }
        }
    }
}
