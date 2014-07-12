import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BulletTestKeyAdapter extends KeyAdapter {

    private BulletTest jt;

    public BulletTestKeyAdapter (BulletTest jt) {
        this.jt = jt;
    }

    public void keyReleased (KeyEvent e) {
        jt.releaseKey (e.getKeyCode ());
    }

    public void keyPressed (KeyEvent e) {
        jt.pressKey (e.getKeyCode ());
    }

}
