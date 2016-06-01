
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Get tools location or size
 */
public class ScreenUtils {

	private static Dimension scrSize = getScreenSize();

	/**
	 * The width and height of the incoming, you can be displayed on the screen
	 * location information
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	public static int[] getCenterLocation(int width, int height) {
		int[] pos = new int[4];
		pos[0] = (scrSize.width - width) / 2;
		pos[1] = (scrSize.height - height) / 2;
		pos[2] = width;
		pos[3] = height;
		return pos;
	}

	/**
	 * Access to the screen size
	 * 
	 * @return
	 */
	public static Dimension getScreenSize() {
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
		return scrSize;
	}

}
