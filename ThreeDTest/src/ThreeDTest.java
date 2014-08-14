import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GL2;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import javax.media.opengl.awt.GLCanvas;

public class ThreeDTest implements GLEventListener {
 
    static float camX = 0;
    static float camZ = 0;
    static float direction = 90;

    static boolean useLighting = false;

    public void init(GLAutoDrawable drawable) {
	GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glDepthFunc(GL.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
    }

    public void dispose(GLAutoDrawable drawable) {
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        float aspect = (float)width / (float)height;
        float dw = aspect / 2;
        gl.glFrustum(-dw, dw, -0.5f, 0.5f, 1.0f, 1000.0f);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void drawSquare(GL2 gl, float x, float y, float z) {
        gl.glLoadIdentity();
        gl.glTranslatef(x, y, z);
        gl.glBegin(GL2.GL_QUADS);       
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 0.0f);
        gl.glVertex3f( 1.0f, 1.0f, 0.0f);
        gl.glVertex3f( 1.0f,-1.0f, 0.0f);
        gl.glVertex3f(-1.0f,-1.0f, 0.0f);
        gl.glEnd();
    }

    public void drawCube(GL2 gl, float x, float y, float z, float radius, float r, float g, float b) {
        gl.glLoadIdentity();
        gl.glRotatef(-(direction - 90), 0.0f, 1.0f, 0.0f);
        gl.glTranslatef(x - camX, y, z - camZ);
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(r, g, b);
        float[] color = { r, g, b, 1.0f };
        float[] specular = { r / 2, g / 2, b / 2, 1.0f };
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_DIFFUSE, color, 0);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, color, 0);

        gl.glNormal3f(0.0f, 0.0f, -1.0f);
        gl.glVertex3f(-radius/2, -radius/2, -radius/2);
        gl.glVertex3f(+radius/2, -radius/2, -radius/2);
        gl.glVertex3f(+radius/2, +radius/2, -radius/2);
        gl.glVertex3f(-radius/2, +radius/2, -radius/2);

        gl.glNormal3f(0.0f, 0.0f, +1.0f);
        gl.glVertex3f(-radius/2, -radius/2, +radius/2);
        gl.glVertex3f(+radius/2, -radius/2, +radius/2);
        gl.glVertex3f(+radius/2, +radius/2, +radius/2);
        gl.glVertex3f(-radius/2, +radius/2, +radius/2);

        gl.glNormal3f(-1.0f, 0.0f, 0.0f);
        gl.glVertex3f(-radius/2, -radius/2, +radius/2);
        gl.glVertex3f(-radius/2, -radius/2, -radius/2);
        gl.glVertex3f(-radius/2, +radius/2, -radius/2);
        gl.glVertex3f(-radius/2, +radius/2, +radius/2);

        gl.glNormal3f(+1.0f, 0.0f, 0.0f);
        gl.glVertex3f(+radius/2, -radius/2, +radius/2);
        gl.glVertex3f(+radius/2, -radius/2, -radius/2);
        gl.glVertex3f(+radius/2, +radius/2, -radius/2);
        gl.glVertex3f(+radius/2, +radius/2, +radius/2);

        gl.glNormal3f(0.0f, -1.0f, 0.0f);
        gl.glVertex3f(-radius/2, -radius/2, +radius/2);
        gl.glVertex3f(-radius/2, -radius/2, -radius/2);
        gl.glVertex3f(+radius/2, -radius/2, -radius/2);
        gl.glVertex3f(+radius/2, -radius/2, +radius/2);

        gl.glNormal3f(0.0f, +1.0f, 0.0f);
        gl.glVertex3f(-radius/2, +radius/2, +radius/2);
        gl.glVertex3f(-radius/2, +radius/2, -radius/2);
        gl.glVertex3f(+radius/2, +radius/2, -radius/2);
        gl.glVertex3f(+radius/2, +radius/2, +radius/2);

        gl.glEnd();
    }

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        float [] lightPosition = { 25.5f - camX, 5.0f, -25.5f - camZ, 1.0f };
        gl.glLoadIdentity();
        gl.glRotatef(-(direction - 90), 0.0f, 1.0f, 0.0f);
        if (useLighting) {
            gl.glEnable(GL2.GL_LIGHTING);
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPosition, 0);
            gl.glEnable(GL2.GL_LIGHT0);
        } else {
            gl.glDisable(GL2.GL_LIGHTING);
        }

        float r, g, b;
        for (int j = 0; j < 50; ++j) {
            for (int i = 0; i < 50; ++i) {
                if ((i + j) % 2 == 0) {
                    r = 0.2f;
                    g = 0.2f;
                    b = 0.2f;
                } else {
                    r = 0.8f;
                    g = 0.8f;
                    b = 0.8f;
                }
                drawCube(gl, 0.5f + i, -2.5f, 0.5f - j, 1.0f, r, g, b);
            }
        }
        drawCube(gl, 25.5f, -1.5f, -25.5f, 1.0f, 0.0f, 0.0f, 1.0f);
        drawCube(gl, 28.5f, -1.5f, -29.5f, 1.0f, 1.0f, 0.0f, 0.0f);
        drawCube(gl, 22.5f, -1.5f, -23.5f, 1.0f, 0.0f, 1.0f, 0.0f);
    }

    public static void main(String[] args) {
        GLCanvas canvas = new GLCanvas();
        final Frame frame = new Frame("3D Test");
        final FPSAnimator animator = new FPSAnimator(canvas, 60);
        canvas.addGLEventListener(new ThreeDTest());
        frame.add(canvas);
        frame.setSize(640, 480);
        frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    animator.stop();
                    frame.dispose();
                    System.exit(0);
                }
            });
        canvas.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        direction -= 2.0f;
                        break;
                    case KeyEvent.VK_LEFT:
                        direction += 2.0f;
                        break;
                    case KeyEvent.VK_UP:
                        camX += Math.cos(2 * 3.1415 * direction / 360.0);
                        camZ -= Math.sin(2 * 3.1415 * direction / 360.0);
                        break;
                    case KeyEvent.VK_DOWN:
                        camX -= Math.cos(2 * 3.1415 * direction / 360.0);
                        camZ += Math.sin(2 * 3.1415 * direction / 360.0);
                        break;
                    case KeyEvent.VK_L:
                        useLighting = !useLighting;
                        break;
                    }
                }
            });
        frame.setVisible(true);
        animator.start();
        canvas.requestFocus();
    }
}
