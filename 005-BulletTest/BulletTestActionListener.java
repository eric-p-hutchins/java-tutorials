import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BulletTestActionListener implements ActionListener {

    private BulletTest at;

    public BulletTestActionListener (BulletTest at) {
        this.at = at;
    }

    public void actionPerformed (ActionEvent e) {
        at.draw ();
        at.handleEvents ();
        at.update ();
    }
}
