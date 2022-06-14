import java.awt.event.KeyEvent;

public class Util {
    public static final String sOOBAccess = "You may not access an element out of bounds!";

    public static final int EXIT_FAILURE = -1;
    public static final int EXIT_SUCCESS = 0;
    public static final int NULL = 0;

    private static Matrix rotationMatrix;

    public static void rotate(Matrix matrixToRotate, double dRadian) {
        rotationMatrix = new Matrix(2, 1);
        rotationMatrix.set(0, 0, Math.cos(dRadian));
        rotationMatrix.set(1, 0, Math.sin(dRadian));

        matrixToRotate.multiply(rotationMatrix);
    }

    public static void transform(Matrix matrixToTransform, Matrix transformMatrix) {
        matrixToTransform.add(transformMatrix);
    }

    public static void scale(Matrix matrixToScale, Matrix scaleMatrix) {
        matrixToScale.multiply(scaleMatrix);
    }

    public static void scale(Matrix matrixToScale, double dScalar) {
        matrixToScale.multiply(dScalar);
    }

    public static Matrix createVector(double dX, double dY) {
        Matrix matrix = new Matrix(2, 1);

        matrix.set(0, 0, dX);
        matrix.set(1, 0, dY);

        return matrix;
    }

    /** ================ josey's stuff ================== */

    /** The width of the window frame */
    public static final int frameWidth = 960;
    /** The height of the window frame */
    public static final int frameHeight = 540;

    /**
     * Determine if the given key code corresponds to a key of the WASD group.
     * 
     * @param keyCode
     */
    public static boolean isWASD(int keyCode) {
        return keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_S
                || keyCode == KeyEvent.VK_D;
    }

    /**
     * Determine if the given key code corresponds to either the left or the right
     * arrow key.
     * 
     * @param keyCode
     */
    public static boolean isLR(int keyCode) {
        return keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT;
    }

    /**
     * Pauses the program
     * 
     * @param pSeconds How long should the program be paused?
     */
    public static void wait(int pSeconds) {
        try {
            Thread.sleep(pSeconds * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Log the current mouse pointer location to the console.
     * 
     * @param mouseX The x coordinate of the pointer
     * @param mouseY The y coordinate of the pointer
     */
    public static void debugMouseLocation(int mouseX, int mouseY) {
        System.out.println("[MOUSE_LOCATION_UPDATE]: " + mouseX + " : " + mouseY);
    }

    /**
     * It's for debugging
     * 
     * @param text The text to log to the console
     */
    public static void cry(String text) {
        System.out.println(text);
    }
};
