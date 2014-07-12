import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import java.awt.Frame;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

public class BulletTest extends Frame {

    private Set<Integer> keys = new HashSet<Integer>();
    private Set<Integer> prevKeys = new HashSet<Integer>();

    private List<GameObject> objects = new ArrayList<GameObject>();
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
	    for (GameObject obj : objects) {
		obj.draw (g);
	    }
        } finally {
            g.dispose();
        }

        bf.show();

        Toolkit.getDefaultToolkit().sync();
    }

    public void pressKey (int code) {
        keys.add (code);
    }

    public void releaseKey (int code) {
        keys.remove (code);
    }

    private boolean keyIsDown (int code) {
        return keys.contains(code);
    }

    private boolean keyWasJustPressed (int code) {
        return keys.contains(code) && !prevKeys.contains(code);
    }

    public void handleEvents () {
        if (keys.contains(KeyEvent.VK_ESCAPE)) {
            System.exit (0);
        }
        if (keyIsDown(KeyEvent.VK_RIGHT) && !keyIsDown(KeyEvent.VK_LEFT)) {
            player.setDx (3);
        } else if (keyIsDown(KeyEvent.VK_LEFT) && !keyIsDown(KeyEvent.VK_RIGHT)) {
            player.setDx (-3);
        } else {
            player.setDx (0);
        }
        if (keyWasJustPressed(KeyEvent.VK_UP)
            && player.getY() == 240 - player.getHeight()) {
            player.setDy (-8);
        }
	if (keyWasJustPressed(KeyEvent.VK_Z)) {
            objects.add(player.shootBullet());
	}
        prevKeys.clear();
        for (Integer code : keys) {
            prevKeys.add (code);
        }
    }

    public void update () {
	for (GameObject obj : objects) {
	    obj.update ();
	}
        for (int i = 0; i < objects.size(); ++i) {
            if (objects.get(i) instanceof Bullet) {
                if (objects.get(i).getX() > 320 || objects.get(i).getX() < -objects.get(i).getWidth()) {
                    objects.remove(i);
                }
            }
        }
    }

    public BulletTest() {
        this.addWindowListener(new BulletTestWindowAdapter());
        this.addKeyListener(new BulletTestKeyAdapter(this));
        this.setUndecorated(true);
        this.setSize(320, 240);
        this.setVisible(true);
        this.createBufferStrategy(2);

        player = new GameObject (48, 0, 16, 16, Color.WHITE);
	objects.add (player);

        Timer t = new Timer(20, new BulletTestActionListener(this));
        t.start();
    }

    public static void main (String[] args) {
        new BulletTest();
    }

}
