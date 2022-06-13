import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
// import java.awt.event.MouseEvent;
// import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel {

    private int[] xCoordinates;
    private int[] yCoordinates;

    /**
     * Polygon constructor
     * 
     * @param pX An array of x coordinates
     * @param pY An array of y coordinates
     */
    public Panel(int[] pX, int[] pY) {

        this.xCoordinates = pX;
        this.yCoordinates = pY;

        setBorder(BorderFactory.createLineBorder(Color.black));

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Util.cry("keyPressed=" + e.getKeyCode());
            }
        
            @Override
            public void keyPressed(KeyEvent e) {
                Util.cry("keyPressed=" + e.getKeyCode());
            }
        
            @Override
            public void keyReleased(KeyEvent e) {
                Util.cry("keyPressed=" + e.getKeyCode());
            }
        });
        setFocusable(true);

        // addMouseMotionListener(new MouseAdapter() {
        //     public void mouseDragged(MouseEvent e) {
        //         Util.debugMouseLocation(e.getX(), e.getY());
        //     }
        // });
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