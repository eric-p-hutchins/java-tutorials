import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JumpTestKeyAdapter extends KeyAdapter {

    public void keyReleased (KeyEvent e) {
        System.out.println("You released key " + e.getKeyCode());
    }

    public void keyPressed (KeyEvent e) {
        System.out.println("You pressed key " + e.getKeyCode());
    }

}
