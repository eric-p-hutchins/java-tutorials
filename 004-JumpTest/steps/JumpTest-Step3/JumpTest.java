import java.awt.Frame;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.Timer;

public class JumpTest extends Frame {

    private GameObject player = null;

    private void clearScreen (Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 320, 240);
    }

    public void draw () {
        BufferStrategy bf = this.getBufferStrategy();
        Graphics g = null;
        try {
            g = bf.getDrawGraphics();
            clearScreen (g);
            player.draw (g);
        } finally {
            g.dispose();
        }

        bf.show();

        Toolkit.getDefaultToolkit().sync();
    }

    public void update () {
        player.update ();
    }

    public JumpTest() {
        this.addWindowListener(new JumpTestWindowAdapter());
        this.setUndecorated(true);
        this.setSize(320, 240);
        this.setVisible(true);
        this.createBufferStrategy(2);

        player = new GameObject (48, 0, 16, 16, Color.WHITE);

        Timer t = new Timer(20, new JumpTestActionListener(this));
        t.start();
    }

    public static void main (String[] args) {
        new JumpTest();
    }

}
