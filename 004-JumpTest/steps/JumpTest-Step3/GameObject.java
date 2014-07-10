import java.awt.Graphics;
import java.awt.Color;

public class GameObject {

    private int x;
    private int y;
    private int dx;
    private int dy;
    private int width;
    private int height;
    private Color color;

    public GameObject (int x, int y, int width, int height, Color color) {
        dx = dy = 0;
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

    public void update () {
        x += dx;
        y += dy;
        dy++;
        if (y + height > 240) {
            y = 240 - height;
        }
    }
}
