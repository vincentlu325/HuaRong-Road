import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

class Person extends JButton implements FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int number;
	ImageIcon imageIcon;
	String name;

	Person(int number, String name, ImageIcon imageIcon) {
		super(imageIcon);
		this.number = number;
		this.name = name;
		addFocusListener(this);
	}

	public void focusGained(FocusEvent arg0) {
	}

	public void focusLost(FocusEvent arg0) {
	}
}