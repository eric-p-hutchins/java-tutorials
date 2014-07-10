import java.awt.Frame;

public class AnimTest extends Frame {

    public AnimTest() {
        this.addWindowListener(new AnimTestWindowAdapter());
        this.setUndecorated(true);
        this.setSize(320, 240);
        this.setVisible(true);
    }

    public static void main (String[] args) {
        new AnimTest();
    }

}
