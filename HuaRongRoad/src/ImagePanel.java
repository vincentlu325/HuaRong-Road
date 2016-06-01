

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * Panel can display image background
 * 
 * @author Administrator
 *
 */
public class ImagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImagePanel(String imagePath) {
		try {
			File f = new File(imagePath);
			img = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Image img;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if (img != null) {
			g2.drawImage(img, 0, 0, getWidth(), getHeight(), 0, 0,
					img.getWidth(null), img.getHeight(null), null);
		}
	}
}