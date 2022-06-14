import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Panel extends JPanel {

    private int[] xCoordinates;
    private int[] yCoordinates;

    /**
     * Polygon constructor
     * 
     * @param pX: An array of x coordinates
     * @param pY: An array of y coordinates
     */
    public Panel(int[] pX, int[] pY) {
        this.xCoordinates = pX;
        this.yCoordinates = pY;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Util.frameWidth, Util.frameHeight);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillPolygon(xCoordinates, yCoordinates, xCoordinates.length);
    }
}