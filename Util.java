public class Util
{
    public static final String sOOBAccess = "You may not access an element out of bounds!";
    
    public static final int EXIT_FAILURE = -1;
    public static final int EXIT_SUCCESS = 0;
    public static final int NULL = 0;
    
    
    /** ================ josey's stuff ================== */
    
    /** The width of the window frame */
    public static final int frameWidth = 960;
    /** The height of the window frame */
    public static final int frameHeight = 540;

    /**
     * This method extracts all the x-coordinates from a given array of {@code Coordinate}s.
     * @param pCoordinates
     * @return
     */
    public static int[] extractXCoordinates(Coordinate[] pCoordinates) {
        int[] xCoordinates = new int[pCoordinates.length];

        for (int i = 0; i < pCoordinates.length; i++) {
            xCoordinates[i] = pCoordinates[i].x;
        }

        return xCoordinates;
    }


    /**
     * This method extracts all the y-coordinates from a given array of {@code Coordinate}s.
     * @param pCoordinates
     * @return
     */
    public static int[] extractYCoordinates(Coordinate[] pCoordinates) {
        int[] yCoordinates = new int[pCoordinates.length];

        for (int i = 0; i < pCoordinates.length; i++) {
            yCoordinates[i] = pCoordinates[i].y;
        }

        return yCoordinates;
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
