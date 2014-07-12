import java.awt.Graphics;
import java.awt.Color;

public class GameObject {

    private int x;
    private int y;
    private Direction direction;
    private int dx;
    private int dy;
    private int width;
    private int height;
    private Color color;
    protected boolean affectedByGravity;

    public GameObject (int x, int y, int width, int height, Color color) {
        dx = dy = 0;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        affectedByGravity = true;
    }

    public void setDx (int dx) {
        this.dx = dx;
        if (dx > 0) {
            direction = Direction.RIGHT;
        } else if (dx < 0) {
            direction = Direction.LEFT;
        }
    }

    public void setDy (int dy) {
        this.dy = dy;
    }

    public int getX () { return x; }
    public int getY () { return y; }
    public int getWidth () { return width; }
    public int getHeight () { return height; }

    public void draw (Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public void update () {
        x += dx;
        y += dy;
        if (affectedByGravity) {
            dy++;
        }
        if (y + height > 240) {
            y = 240 - height;
        }
    }

    public GameObject shootBullet() {
        Bullet bullet = new Bullet (x, y, 4, 4, Color.WHITE);
        bullet.setDx((direction == Direction.RIGHT)?8:-8);
        return bullet;
    }
}
