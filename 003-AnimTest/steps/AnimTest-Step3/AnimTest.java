import java.awt.Frame;

import javax.swing.Timer;

public class AnimTest extends Frame {

    public AnimTest() {
        this.addWindowListener(new AnimTestWindowAdapter());
        this.setUndecorated(true);
        this.setSize(320, 240);
        this.setVisible(true);

        Timer t = new Timer(1000, new AnimTestActionListener());
        t.start();
    }

    public static void main (String[] args) {
        new AnimTest();
    }

}
