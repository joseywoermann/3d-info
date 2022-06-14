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
        // return matrixToRotate;
    }

    public static void transform(Matrix matrixToTransform, Matrix transformMatrix) {
        matrixToTransform.add(transformMatrix);
        // return matrixToTransform;
    }

    public static void scale(Matrix matrixToScale, Matrix scaleMatrix) {
        matrixToScale.multiply(scaleMatrix);
        // return matrixToScale;
    }

    public static void scale(Matrix matrixToScale, double dScalar) {
        matrixToScale.multiply(dScalar);
        // return matrixToScale;
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
     * Pauses the program
     * 
     * @param pMilliseconds How long should the program be paused?
     */
    public static void waitMS(int pMilliseconds) {
        try {
            Thread.sleep(pMilliseconds);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Log the current mouse pointer location to the console.
     * 
     * @param mouseX: The x coordinate of the pointer
     * @param mouseY: The y coordinate of the pointer
     */
    public static void debugMouseLocation(int mouseX, int mouseY) {
        System.out.println("[MOUSE_LOCATION_UPDATE]: " + mouseX + " : " + mouseY);
    }

    /**
     * It's for debugging
     * 
     * @param text: The text to log to the console
     */
    public static void cry(String text) {
        System.out.println(text);
    }
};

// // arrow coordinates
// int[] arrowX = { 244, 546, 546, 847, 546, 546, 244 };
// int[] arrowY = { 215, 215, 122, 306, 489, 396, 396 };

// // star coordinates
// int[] starX = { 423, 477, 643, 514, 562, 425, 287, 333, 200, 370 };
// int[] starY = { 50, 203, 209, 299, 451, 365, 455, 299, 208, 200 };
