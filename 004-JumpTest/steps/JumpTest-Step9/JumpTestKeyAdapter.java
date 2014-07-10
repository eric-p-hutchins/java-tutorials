import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JumpTestKeyAdapter extends KeyAdapter {

    private JumpTest jt;

    public JumpTestKeyAdapter (JumpTest jt) {
        this.jt = jt;
    }

    public void keyReleased (KeyEvent e) {
        jt.releaseKey (e.getKeyCode ());
    }

    public void keyPressed (KeyEvent e) {
        jt.pressKey (e.getKeyCode ());
    }

}
