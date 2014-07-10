import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JumpTestActionListener implements ActionListener {

    private JumpTest at;

    public JumpTestActionListener (JumpTest at) {
        this.at = at;
    }

    public void actionPerformed (ActionEvent e) {
        at.draw ();
        at.update ();
    }
}
