import javax.swing.JLabel;

public class Clock implements Runnable {
	/**
	 * 
	 */
	Thread clock;
	int tt = 0;
	int hour = 0; 
	int minute = 0; 
	int second = 0; 

	JLabel clockJLabel;

	private String time;

	public Clock(JLabel clockJLabel) {
		this.clockJLabel = clockJLabel;

	}

	public void start() { 
		if (clock == null) { 
			clock = new Thread(this); 
			clock.start(); 
		}
	}

	public void run() {
		while (clock != null) {
			tt = tt + 1;
			refresh(); 
			try {
				Thread.sleep(1000); // Thread to suspend 1 second
			} catch (InterruptedException ex) {
				ex.printStackTrace(); 
			}
		}
	}

	public void stop() {
		clock = null;
	}

	public void refresh() { 
		String timeInfo = "";
		second = tt;

		if (second >= 60) {
			tt = 0;
			second = tt;
			minute++;

		} else if (minute >= 60) {
			minute = 0;
			hour++;
		}

		if (hour <= 9)
			timeInfo += "0" + hour + ":"; // format
		else
			timeInfo += hour + ":";
		if (minute <= 9)
			timeInfo += "0" + minute + ":";
		else
			timeInfo += minute + ":";
		if (second <= 9)
			timeInfo += "0" + second;
		else
			timeInfo += second;

		time = timeInfo;
		clockJLabel.setText("Time: " + timeInfo);

	}

	public String getTime() {
		return time;
	}
	
	

}