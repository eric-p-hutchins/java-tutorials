import java.awt.Frame;

public class AnimTest {

    public static void main (String[] args) {
        Frame f = new Frame();
        f.addWindowListener(new AnimTestWindowAdapter());
        f.setSize(320, 240);
        f.setVisible(true);
    }

}
