import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Panel extends JPanel {

    private int[] xCoordinates;
    private int[] yCoordinates;
    private int pointCount;

    /**
     * Polygon constructor
     * 
     * @param pX      An array of x coordinates
     * @param pY      An array of y coordinates
     * @param pPoints The total number of points in the figure
     */
    public Panel(int[] pX, int[] pY, int pPoints) {

        this.xCoordinates = pX;
        this.yCoordinates = pY;
        this.pointCount = pPoints;

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                Util.debugMouseLocation(e.getX(), e.getY());
            }
        });

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Util.frameWidth, Util.frameHeight);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillPolygon(xCoordinates, yCoordinates, pointCount);
    }
}