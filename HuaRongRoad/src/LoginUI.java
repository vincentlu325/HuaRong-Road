import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Login screen
 */
public class LoginUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	private JLabel picture, jl1, jl2, jl3, jl4;
	private JTextField jtUsername, jtCheckCode;
	private JPasswordField jtPassword;
	private JButton Ok, reg;
	public static User user;

	public LoginUI() {
		Image iconImage = null;
		try {
			iconImage = ImageIO.read(new File("images/caocao.jpg"));
			this.setIconImage(iconImage);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		picture = new JLabel(new ImageIcon("images/login_bg.jpg"));
		jl1 = new JLabel("Username:");
		jl2 = new JLabel("Password:");
		jl3 = new JLabel();
		jl4 = new JLabel("Switch");
		jtUsername = new JTextField();
		jtPassword = new JPasswordField();
		jtCheckCode = new JTextField();
		Ok = new JButton("Login");
		reg = new JButton("Register");
		launchFrame();
	}

	public void launchFrame() {
		JPanel p = (JPanel) getContentPane();

		jl3.setText(getcode());
		jl3.setForeground(Color.decode("#2DA4DC")); // Set the color of the
													// authentication code
		jl3.setBorder(BorderFactory.createRaisedBevelBorder());// The
																// verification
																// code
																// projection
																// display
		jl4.addMouseListener(new addEvent());
		Ok.addActionListener(this);
		reg.addActionListener(this);

		// Set the background image
		p.setLayout(null);
		picture.setBounds(0, 0, 500, 110);
		p.add(picture);
		p.setOpaque(false);
		getLayeredPane().add(picture, new Integer(Integer.MIN_VALUE));

		// Set the icon
		BufferedImage icon = null;
		try {
			icon = ImageIO.read(new File("images/icon.jpg"));
			this.setIconImage(icon);
		} catch (Exception e) {

		}

		jl1.setBounds(140, 120, 90, 25);
		p.add(jl1);
		jtUsername.setBounds(220, 120, 130, 25);
		p.add(jtUsername);
		jl2.setBounds(140, 160, 90, 25);
		p.add(jl2);
		jtPassword.setBounds(220, 160, 130, 25);
		p.add(jtPassword);
		jtCheckCode.setBounds(140, 200, 90, 25);
		p.add(jtCheckCode);
		jl3.setBounds(240, 200, 50, 25);
		p.add(jl3);
		jl4.setBounds(300, 200, 50, 25);
		p.add(jl4);
		Ok.setBounds(140, 260, 80, 30);
		p.add(Ok);
		reg.setBounds(250, 260, 100, 30);
		p.add(reg);

		setTitle("Login");
		int[] centerLocation = ScreenUtils.getCenterLocation(500, 330);
		this.setLocation(centerLocation[0], centerLocation[1]);
		this.setSize(centerLocation[2], centerLocation[3]);
		setResizable(false);// The window is not drag
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Get verification code
	public String getcode() {
		Random rd = new Random();
		String str = "";
		int a[] = new int[4];
		for (int i = 0; i < 4; i++) {
			a[i] = rd.nextInt(200);
			if ((a[i] >= 48 && a[i] <= 57) || (a[i] >= 65 && a[i] <= 90)
					|| (a[i] >= 97 && a[i] <= 122)) {
				str = str + (char) a[i];
			} else {
				i--; // i--��Ensure that eventually get to four verification code
			}
		}
		return str;
	}

	public boolean isEmpty(JTextField jt) {
		if (jt.getText().equals("")) {
			return true;
		}
		return false;
	}

	// Determine whether the illegal operation, mainly for the text box is
	// concerned; If the text box is empty, fill in information prompt
	public boolean isLegal() {
		if (isEmpty(jtUsername)) {
			JOptionPane.showMessageDialog(null, "Please type user name!");
			return false;
		} else if (isEmpty(jtPassword)) {
			JOptionPane.showMessageDialog(null, "Please enter password!");
			return false;
		} else if (isEmpty(jtCheckCode)) {
			JOptionPane.showMessageDialog(null, "Please enter the verification code!");
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		final String username = jtUsername.getText();
		final String password = jtPassword.getText().toString();
		final String code2 = jtCheckCode.getText();
		final String code1 = jl3.getText();
		if (source == Ok) {
			if (isLegal()) {
				if (code2.equalsIgnoreCase(code1)) {
					new Thread(new Runnable() {

						@Override
						public void run() {
							try {

								/**
								 * In the database query
								 */
								user = UserDao.getInstance().login(
										new User(username, password));

								if (user != null) {

									String tipMsg = "Login successful";
									JOptionPane.showMessageDialog(null, tipMsg,
											"Prompt", JOptionPane.WARNING_MESSAGE);
									dispose();

									/**
									 * Enter the main interface
									 */
									new Hua_Rong_Road("HuaRongRoad");

								} else {
									JOptionPane.showMessageDialog(null,
											"The user name or password error!", "Error message",
											JOptionPane.ERROR_MESSAGE);
								}

							} catch (HeadlessException e1) {
								e1.printStackTrace();
							}

						}
					}).start();
				} else {
					JOptionPane.showMessageDialog(null, "Verification code error, please input again!", "Error message",
							JOptionPane.ERROR_MESSAGE);
					jl3.setText(getcode());
				}

			}
		} else if (source == reg) {
			// jtUsername.setText("");
			// jtPassword.setText("");
			// jtCheckCode.setText("");
			new RegisterUI().setVisible(true);
		}
	}

	class addEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			jl3.setText(getcode());
		}
	}

}