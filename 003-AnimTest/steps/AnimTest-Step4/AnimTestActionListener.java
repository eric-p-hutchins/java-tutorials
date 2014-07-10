import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnimTestActionListener implements ActionListener {

    private AnimTest at;

    public AnimTestActionListener (AnimTest at) {
        this.at = at;
    }

    public void actionPerformed (ActionEvent e) {
        at.draw ();
    }
}
