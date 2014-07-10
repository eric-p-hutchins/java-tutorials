import java.awt.Graphics;
import java.awt.Color;

public class GameObject {

    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;

    public GameObject (int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw (Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public void update (int t) {
        y = t % 224;
    }
}
