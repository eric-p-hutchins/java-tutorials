import java.awt.Frame;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.Timer;

public class AnimTest extends Frame {

    public void draw () {
        BufferStrategy bf = this.getBufferStrategy();
        Graphics g = null;
        try {
            g = bf.getDrawGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 320, 240);
        } finally {
            g.dispose();
        }

        bf.show();

        Toolkit.getDefaultToolkit().sync();
    }

    public AnimTest() {
        this.addWindowListener(new AnimTestWindowAdapter());
        this.setUndecorated(true);
        this.setSize(320, 240);
        this.setVisible(true);
        this.createBufferStrategy(2);

        Timer t = new Timer(100, new AnimTestActionListener(this));
        t.start();
    }

    public static void main (String[] args) {
        new AnimTest();
    }

}
