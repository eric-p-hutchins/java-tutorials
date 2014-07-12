import java.awt.Color;

public class Bullet extends GameObject {

    public Bullet (int x, int y, int width, int height, Color color) {
        super (x, y, width, height, color);
        affectedByGravity = false;
    }

}
