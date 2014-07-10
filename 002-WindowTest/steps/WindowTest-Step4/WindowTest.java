import java.awt.Frame;

public class WindowTest {

    public static void main (String[] args) {
        Frame f = new Frame();
        f.addWindowListener(new WindowTestWindowAdapter());
        f.setSize(320, 240);
        f.setVisible(true);
    }

}
