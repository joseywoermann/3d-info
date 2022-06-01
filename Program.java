import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

public class Program {

    private JFrame frame;

    private Coordinate[] coordinates;

    /**
     * Construct a new Program
     */
    public Program() {
        // this method scales the entire content of the window by a factor of 2
        // System.setProperty("sun.java2d.uiScale","2");
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
                createGUI(new Panel("polygon", x, y, points));
            }
        });
    }

    /**
     * Create a new GUI with set size and make it visible
     */
    private void createGUI(Panel panel) {
        this.frame = new JFrame("Fancy objects doing things");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(Util.frameWidth, Util.frameHeight);

        this.frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent cE) {
                System.out.println("Width: " + cE.getComponent().getWidth() + " Height: " + cE.getComponent().getHeight());
            }
        });

        this.frame.add(panel);
        this.frame.pack();
        this.frame.setVisible(true);
    }
}