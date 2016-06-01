

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RegisterUI extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPanel;

	private JTextField loginName;
	private JTextField password;

	private JButton ok;
	private JButton cancel;

	/**
	 * 
	 */
	public RegisterUI() {


		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		JLabel jLabel = new JLabel("Username");
		jLabel.setBounds(10, 10, 80, 30);
		contentPanel.add(jLabel);

		loginName = new JTextField();
		loginName.setBounds(100, 10, 200, 30);
		contentPanel.add(loginName);

		jLabel = new JLabel("Password");
		jLabel.setBounds(10, 50, 80, 30);
		contentPanel.add(jLabel);
		password = new JTextField();
		password.setBounds(100, 55, 200, 30);
		contentPanel.add(password);





		ok = new JButton("Ok");
		ok.setBounds(70, 120, 90, 35);
		contentPanel.add(ok);

		cancel = new JButton("Cancel");
		cancel.setBounds(180, 120, 90, 35);
		contentPanel.add(cancel);

		ok.addActionListener(this);
		cancel.addActionListener(this);

		this.add(contentPanel);
		this.setTitle("Register");
		this.setResizable(false);
		int[] centerLocation = ScreenUtils.getCenterLocation(
				330, 200);
		this.setBounds(centerLocation[0], centerLocation[1], centerLocation[2],
				centerLocation[3]);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == cancel) {
			dispose();
		} else {


			if (loginName.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter your user name!");
				return;
			}

			if (password.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter the password!");
				return;
			}

			new Thread(new Runnable() {

				@Override
				public void run() {
						if (UserDao.getInstance().add(
								new User(loginName.getText().toString(),
										password.getText().toString()))) {
							JOptionPane.showMessageDialog(null, "Registered successfully");
							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Registration failed");

						}
					

				}
			}).start();

		}

	}
}
