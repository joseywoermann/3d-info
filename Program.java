import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Program /* implements KeyListener */ {

    private JFrame frame;
    private Matrix[] matrices;

    /**
     * Construct a new Program
     */
    public Program() {

        // use SingUtilities.invokeLater() to run stuff asynchronously
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Fabrice wollte Lautschrift
                frame = new JFrame("2ˈdi ˈɑːbdʒɪkts");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(Util.frameWidth, Util.frameHeight);

                frame.addComponentListener(new ComponentAdapter() {
                    public void componentResized(ComponentEvent cE) {
                        System.out.println(
                                "Width: " + cE.getComponent().getWidth() + " Height: " + cE.getComponent().getHeight());
                    }
                });

                frame.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        // ...
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        // Util.cry(KeyEvent.getKeyText(e.getKeyCode()));
                        decodeKeyCodes(e.getKeyCode());
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
     * This method receives the integer code for the key that has been pressed on
     * the users keyboard and determines what to do with it, either rotate, scale or
     * move.
     * 
     * @param keyCode
     */
    public void decodeKeyCodes(int keyCode) {
        if (keyCode == 87 || keyCode == 65 || keyCode == 83 || keyCode == 68) {
            // movement
            this.moveFigure(keyCode);
        } else if (keyCode == 37 || keyCode == 39) {
            // rotation
            this.rotateFigure(keyCode);
        }
    }

    /**
     * Move the figure according to key strokes
     * 
     * @param keyCode
     */
    private void moveFigure(int keyCode) {
        /**
         * Key codes:
         * 
         * W = 87
         * A = 65
         * S = 83
         * D = 68
         */

        switch (keyCode) {
            case 87:
                // move up
                for (int i = 0; i < this.matrices.length; i++) {
                    Util.transform(this.matrices[i], Util.createVector(0, -10));
                }
                break;
            case 65:
                // move left
                for (int i = 0; i < this.matrices.length; i++) {
                    Util.transform(this.matrices[i], Util.createVector(-10, 0));
                }
                break;
            case 83:
                // move down
                for (int i = 0; i < this.matrices.length; i++) {
                    Util.transform(this.matrices[i], Util.createVector(0, 10));
                }
                break;
            case 68:
                // move right
                for (int i = 0; i < this.matrices.length; i++) {
                    Util.transform(this.matrices[i], Util.createVector(10, 0));
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
        /**
         * Key codes:
         * 
         * Left = 37
         * Right = 39
         */

        switch (keyCode) {
            case 37:
                // rotate counterclockwise
                for (int i = 0; i < this.matrices.length; i++) {
                    Util.rotate(this.matrices[i], (Math.PI / 10));
                }
                break;
            case 39:
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

    public void createSquare() {

        Matrix m1 = Util.createVector(0, 0);
        Matrix m2 = Util.createVector(0, 200);
        Matrix m3 = Util.createVector(200, 200);
        Matrix m4 = Util.createVector(200, 0);

        this.matrices = new Matrix[] { m1, m2, m3, m4 };

        this.draw(this.matrices);
        // Util.cry("painted");

        // Util.wait(2);

        // double _45d = (Math.PI / 4);

        // Matrix m1a = Util.transform(m1, Util.createVector(100, 100));
        // Matrix m2a = Util.transform(m2, Util.createVector(100, 100));
        // Matrix m3a = Util.transform(m3, Util.createVector(100, 100));
        // Matrix m4a = Util.transform(m4, Util.createVector(100, 100));

        // Matrix[] matrices2 = { m1a, m2a, m3a, m4a };

        // this.draw(matrices2);
        // Util.cry("did stuff");
    }

    /**
     * draw stuff based on matrix values
     * 
     * @param pMatrices
     */
    public void draw(Matrix[] pMatrices) {

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