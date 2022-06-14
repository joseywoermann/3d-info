import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Program {

    private JFrame frame;
    private Matrix[] matrices;

    /**
     * Construct a new Program
     */
    public Program() {

        // use SingUtilities.invokeLater() to run stuff asynchronously
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame = new JFrame("2ˈdi ˈɑːbdʒɪkts");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(Util.frameWidth, Util.frameHeight);

                frame.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        // ...
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        // Based on the received key code, move or rotate the figure

                        int keyCode = e.getKeyCode();
                        Util.cry(Integer.toString(keyCode));

                        if (Util.isWASD(keyCode)) {
                            moveFigure(keyCode);

                        } else if (Util.isLR(keyCode)) {
                            rotateFigure(keyCode);
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        // ...
                    }
                });

                frame.setVisible(true);
            }

        });
    }

    /**
     * This method creates a square and paints it onto the screen.
     */
    public void createSquare() {

        Matrix m1 = Util.createVector(0, 0);
        Matrix m2 = Util.createVector(0, 200);
        Matrix m3 = Util.createVector(200, 200);
        Matrix m4 = Util.createVector(200, 0);

        this.matrices = new Matrix[] { m1, m2, m3, m4 };

        this.draw(this.matrices);
    }

    /**
     * This method creates an arrow and paints it onto the screen.
     */
    public void createArrow() {

        Matrix m1 = Util.createVector(244, 215);
        Matrix m2 = Util.createVector(546, 215);
        Matrix m3 = Util.createVector(546, 122);
        Matrix m4 = Util.createVector(847, 306);
        Matrix m5 = Util.createVector(546, 489);
        Matrix m6 = Util.createVector(546, 396);
        Matrix m7 = Util.createVector(244, 396);

        this.matrices = new Matrix[] { m1, m2, m3, m4, m5, m6, m7 };

        this.draw(this.matrices);
    }

    /**
     * This method creates a star and paints it onto the screen.
     */
    public void createStar() {

        Matrix m1 = Util.createVector(425, 50);
        Matrix m2 = Util.createVector(477, 209);
        Matrix m3 = Util.createVector(643, 209);
        Matrix m4 = Util.createVector(514, 299);
        Matrix m5 = Util.createVector(562, 451);
        Matrix m6 = Util.createVector(425, 365);
        Matrix m7 = Util.createVector(287, 455);
        Matrix m8 = Util.createVector(333, 299);
        Matrix m9 = Util.createVector(200, 209);
        Matrix m10 = Util.createVector(370, 200);

        this.matrices = new Matrix[] { m1, m2, m3, m4, m5, m6, m7, m8, m9, m10 };

        this.draw(this.matrices);
    }

    /**
     * Move the figure according to key strokes
     * 
     * @param keyCode
     */
    private void moveFigure(int keyCode) {

        switch (keyCode) {
            case KeyEvent.VK_W:
                // move up
                for (int i = 0; i < this.matrices.length; i++) {
                    Util.transform(this.matrices[i], Util.createVector(0, -5));
                }
                break;
            case KeyEvent.VK_A:
                // move left
                for (int i = 0; i < this.matrices.length; i++) {
                    Util.transform(this.matrices[i], Util.createVector(-5, 0));
                }
                break;
            case KeyEvent.VK_S:
                // move down
                for (int i = 0; i < this.matrices.length; i++) {
                    Util.transform(this.matrices[i], Util.createVector(0, 5));
                }
                break;
            case KeyEvent.VK_D:
                // move right
                for (int i = 0; i < this.matrices.length; i++) {
                    Util.transform(this.matrices[i], Util.createVector(5, 0));
                }
                break;
            default:
                break;
        }

        this.draw(this.matrices);
    }

    /**
     * [COMPLETELY AND UTTERLY BROKEN, PLEASE FIX]
     * Rotate the drawn figure
     * 
     * @param keyCode
     */
    private void rotateFigure(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                // rotate counterclockwise
                for (int i = 0; i < this.matrices.length; i++) {
                    Util.rotate(this.matrices[i], -(Math.PI / 10));
                }
                break;
            case KeyEvent.VK_RIGHT:
                // rotate clockwise
                for (int i = 0; i < this.matrices.length; i++) {
                    Util.rotate(this.matrices[i], (Math.PI / 10));
                }
                break;
            default:
                break;
        }

        this.draw(this.matrices);
    }

    /**
     * draw stuff based on matrix values
     * 
     * @param pMatrices
     */
    private void draw(Matrix[] pMatrices) {

        int[] x = new int[pMatrices.length];
        int[] y = new int[pMatrices.length];

        // extract x and y coordinates from a 1 by 2 matrix (aka 2D vector)
        for (int i = 0; i < pMatrices.length; i++) {
            x[i] = (int) pMatrices[i].getValue(0, 0);
            y[i] = (int) pMatrices[i].getValue(1, 0);
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.add(new Panel(x, y));
                frame.pack();
            }
        });
    }
}