import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.awt.event.ComponentAdapter;


public class Program {

    private JFrame frame;

    private Coordinate[] coordinates;

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

    /**
     * Test movement of figures
     */
    public void testProgrammedMovement() {
        int[] x = { 423, 477, 643, 514, 562, 425, 287, 333, 200, 370 };
        int[] y = { 50, 203, 209, 299, 451, 365, 455, 299, 208, 200 };

        this.drawPolygon(x, y, x.length);

        // move stuff around

        for (int k = 0; k < 50; k++) {
            // to the right
            for (int i = 0; i < 50; i++) {
                Util.waitMS(1000/60);
                
                for (int j = 0; j < x.length; j++) {
                    Array.setInt(x, j, (x[j] + 5));
                }
                
                this.drawPolygon(x, y, x.length);
            }
            
            // to the left
            for (int i = 0; i < 50; i++) {
                Util.waitMS(1000/60);
                
                for (int j = 0; j < x.length; j++) {
                    Array.setInt(x, j, (x[j] - 5));
                }
                
                this.drawPolygon(x, y, x.length);
            }
        }
 
    }


    /**
     * arrow
     */
    public void testArrow() {

        Coordinate[] coords = {
                new Coordinate(244, 215),
                new Coordinate(546, 215),
                new Coordinate(546, 122),
                new Coordinate(847, 306),
                new Coordinate(546, 489),
                new Coordinate(546, 396),
                new Coordinate(244, 396)
        };

        this.drawPolygon(Util.extractXCoordinates(coords), Util.extractYCoordinates(coords), 7);
    }

    /**
     * star
     */
    public void testStar() {
        int[] x = { 423, 477, 643, 514, 562, 425, 287, 333, 200, 370 };
        int[] y = { 50, 203, 209, 299, 451, 365, 455, 299, 208, 200 };

        this.drawPolygon(x, y, x.length);
    }


    /**
     * draw stuff based on matrix values
     * @param pMatrices
     */
    public void testMatrix(Matrix[] pMatrices) {

        int[] x = new int[pMatrices.length];
        int[] y = new int[pMatrices.length];

        // extract x and y coordinates from a 1 by 2 matrix
        for (int i = 0; i < pMatrices.length; i++) {
            x[i] = (int) pMatrices[i].getValue(0, 0);
            y[i] = (int) pMatrices[i].getValue(1, 0);
        }
        
        this.drawPolygon(x, y, x.length);

    }

    /**
     * Draw a polygon with the specified corner coordinates
     * 
     * @param x:      The x coordinates of the points of the polygon
     * @param y:      The y coordinates of the points of the polygon
     * @param points: The number of points of the polygon
     */
    public void drawPolygon(int[] x, int[] y, int points) {
        if (x.length != y.length) return;

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.add(new Panel(x, y, points));
                frame.pack();
            }
        });
    }
}