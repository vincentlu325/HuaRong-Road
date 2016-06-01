import javax.swing.UIManager;

public class Main {
		/**
		 * @param args
		 */
		public static void main(String[] args) {

			try {
				UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// windows
			} catch (Exception e) {
				e.printStackTrace();
			}


			new LoginUI().setVisible(true);
			
		}
}
