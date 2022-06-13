import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Program /*implements KeyListener*/ {

    private JFrame frame;

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
                        System.out.println("Width: " + cE.getComponent().getWidth() + " Height: " + cE.getComponent().getHeight());
                    }
                });
                frame.setVisible(true);
            }
        });
    }

    public void testSquare() {

        Matrix m1 = Util.createVector(0, 0);
        Matrix m2 = Util.createVector(0, 200);
        Matrix m3 = Util.createVector(200, 200);
        Matrix m4 = Util.createVector(200, 0);

        Matrix[] matrices = { m1, m2, m3, m4 };

        this.draw(matrices);
        Util.cry("painted");

        Util.wait(2);

        double _45d = (Math.PI / 4);

        Matrix m1a = Util.transform(m1, Util.createVector(100, 100));
        Matrix m2a = Util.transform(m2, Util.createVector(100, 100));
        Matrix m3a = Util.transform(m3, Util.createVector(100, 100));
        Matrix m4a = Util.transform(m4, Util.createVector(100, 100));

        Matrix[] matrices2 = { m1a, m2a, m3a, m4a };

        this.draw(matrices2);
        Util.cry("did stuff");
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