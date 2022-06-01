import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Panel extends JPanel {

    private String figureType;

    // polygon stuff
    private int[] polyX;
    private int[] polyY;
    private int polyPoints;

    /**
     * Polygon constructor
     * 
     * @param type
     * @param polyX
     * @param polyY
     * @param polyPoints
     */
    public Panel(String type, int[] polyX, int[] polyY, int polyPoints) {

        if (type != "polygon") return;

        this.figureType = type;

        this.polyX = polyX;
        this.polyY = polyY;
        this.polyPoints = polyPoints;

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                // moveFigure(e.getX(), e.getY());
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

        g.drawPolygon(polyX, polyY, polyPoints);
    }

    // private void moveFigure(int pX, int pY) {
    // int offset = 1;
    // if ((squareX != pX) || (squareY != pY)) {
    // repaint(squareX, squareY, squareW + offset, squareH + offset);
    // squareX = pX;
    // squareY = pY;
    // repaint(squareX, squareY, squareW + offset, squareH + offset);
    // }
    // }
}