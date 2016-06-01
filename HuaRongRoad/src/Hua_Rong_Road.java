import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class Hua_Rong_Road extends JFrame implements MouseListener, KeyListener,
		ActionListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Person person[] = new Person[10];
	private JButton left, right, above, below;
	private ImagePanel panel;

	private JButton mRestart;
	private JButton mExit;

	private ImageIcon caocaoImageIcon;
	private ImageIcon zhangfeiImageIcon;
	private ImageIcon caorenImageIcon;
	private ImageIcon zhangliaoImageIcon;
	private ImageIcon liubeiIcon;
	private ImageIcon guanyuIcon;
	private ImageIcon bingIcon;
	private ImageIcon newIcon;
	private ImageIcon exitIcon;

	private JLabel clockJLabel;
	private JLabel scoreJLabel;
	private Clock clock;
	private int score;

	private JLabel iconJLabel;
	private JLabel descriptionJLabel;
	private JLabel exitJLabel;

	private JLabel selectJLabel;
	private JComboBox<String> jcb;
	private String[] str = new String[] { "The first level",
			"The second level", "The third level", "The fourth level",
			"The fifth level" };
	private String currSelect = str[0];

	// menu
	private JMenuBar jmb = null;
	private JMenu jmGame = null;
	private JMenu jmScore = null;
	private JMenu jmMusic = null;
	private JMenu jmHelp = null;
	private JMenuItem jMItemNewGame = null;
	private JMenuItem jMItemExit = null;
	private JMenuItem jMItemMyScore = null;
	private JMenuItem jMItemStart = null;
	private JMenuItem jMItemStop = null;
	private JMenuItem jMItemRanking = null;
	private JMenuItem jMItemDirections = null;

	// bg music
	private AudioClip bgMusic;

	@SuppressWarnings("deprecation")
	public Hua_Rong_Road(String s) {
		super(s);
		Image iconImage = null;
		try {
			iconImage = ImageIO.read(new File("images/caocao.jpg"));
			this.setIconImage(iconImage);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		createMenu();
		// According to the background
		panel = new ImagePanel("images/bk.jpg");
		panel.setBounds(0, 0, Config.WIDTH, Config.HEIGHT);
		add(panel);
		panel.setLayout(null);
		caocaoImageIcon = new ImageIcon(System.getProperty("user.dir")
				+ "/images/caocao.jpg");
		zhangfeiImageIcon = new ImageIcon(System.getProperty("user.dir")
				+ "/images/zhangfei.jpg");
		caorenImageIcon = new ImageIcon(System.getProperty("user.dir")
				+ "/images/caoren.jpg");
		zhangliaoImageIcon = new ImageIcon(System.getProperty("user.dir")
				+ "/images/zhangliao.jpg");
		liubeiIcon = new ImageIcon(System.getProperty("user.dir")
				+ "/images/liubei.jpg");
		guanyuIcon = new ImageIcon(System.getProperty("user.dir")
				+ "/images/guanyu.jpg");
		bingIcon = new ImageIcon(System.getProperty("user.dir")
				+ "/images/bing.jpg");
		newIcon = new ImageIcon("images/new.jpg");
		exitIcon = new ImageIcon("images/exit.jpg");

		URL cb = null;
		File f = new File("sounds/14215672211.mid");
		try {
			cb = f.toURL();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		bgMusic = Applet.newAudioClip(cb);

		mRestart = new JButton(newIcon);
		mRestart.addActionListener(this);

		mExit = new JButton(exitIcon);
		mExit.addActionListener(this);

		mRestart.setBounds(115, 380, 70, 30);
		mExit.setBounds(213, 380, 70, 30);
		panel.add(mRestart);
		panel.add(mExit);
		setButton(mRestart);
		setButton(mExit);

		clockJLabel = new JLabel();
		clockJLabel.setBounds(20, 10, 150, 30);
		clockJLabel.setFont(Config.F);
		clockJLabel.setForeground(Config.ORANGE);
		panel.add(clockJLabel);

		scoreJLabel = new JLabel("Score: " + score);
		scoreJLabel.setBounds(510, 10, 180, 30);
		scoreJLabel.setForeground(Config.ORANGE);
		scoreJLabel.setFont(Config.F);
		panel.add(scoreJLabel);

		iconJLabel = new JLabel("Hua Rong Road");
		iconJLabel.setFont(Config.F_BIG);
		iconJLabel.setForeground(Config.GREEN);
		iconJLabel.setBounds(330, 80, 200, 35);
		panel.add(iconJLabel);

		descriptionJLabel = new JLabel(
				"<html>Gameplay: move piece by two small<br/>Spaces, move the Boss out of the<br/>game hua-rong-road (i.e, the largest square moved to the bottom of the<br/>central position).The user can move piece by two ways: one is the mouse to<br/> click, another is by moving selected square, and then press the keyboard up, down, left, right.</html>");
		descriptionJLabel.setFont(Config.F);
		descriptionJLabel.setForeground(Config.ORANGE);
		descriptionJLabel.setBounds(310, 110, 260, 200);
		panel.add(descriptionJLabel);

		exitJLabel = new JLabel("exit");
		exitJLabel.setFont(Config.F_BIG);
		exitJLabel.setForeground(Config.RED);
		exitJLabel.setBounds(175, 235, 100, 150);
		panel.add(exitJLabel);

		selectJLabel = new JLabel("Select: ");
		selectJLabel.setFont(Config.F);
		selectJLabel.setForeground(Config.ORANGE);
		selectJLabel.setBounds(310, 245, 60, 150);
		panel.add(selectJLabel);

		jcb = new JComboBox<String>(str);
		jcb.setBounds(370, 305, 150, 30);
		jcb.addItemListener(this);
		panel.add(jcb);

		left = new JButton();
		right = new JButton();
		above = new JButton();
		below = new JButton();
		panel.add(left);
		panel.add(right);
		panel.add(above);
		panel.add(below);
		setButton(left);
		setButton(right);
		setButton(above);
		setButton(below);

		init();
		int[] locate = ScreenUtils.getCenterLocation(Config.WIDTH,
				Config.HEIGHT);
		this.setSize(locate[2], locate[3]);
		this.setLocation(locate[0], locate[1]);
		this.setResizable(false);
		this.setVisible(true);
		validate();
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exit();
			}
		});
	}

	private void createMenu() {
		jmb = new JMenuBar();
		jmGame = new JMenu("Game");
		jmScore = new JMenu("Score");
		jmMusic = new JMenu("Music");
		jmHelp = new JMenu("History");
		jMItemNewGame = new JMenuItem("New Game");
		jMItemExit = new JMenuItem("Quit Game");
		jMItemRanking = new JMenuItem("Ranking");
		jMItemMyScore = new JMenuItem("My Score");
		jMItemStart = new JMenuItem("Start");
		jMItemStop = new JMenuItem("Stop");
		jMItemRanking = new JMenuItem("Ranking");
		jMItemDirections = new JMenuItem("Game History");

		jmGame.add(jMItemNewGame);
		jmGame.add(jMItemExit);
		jmScore.add(jMItemMyScore);
		jmScore.add(jMItemRanking);
		jmMusic.add(jMItemStart);
		jmMusic.add(jMItemStop);
		jmHelp.add(jMItemDirections);

		jmb.add(jmGame);
		jmb.add(jmScore);
		jmb.add(jmMusic);
		jmb.add(jmHelp);

		this.setJMenuBar(jmb);

		jMItemExit.addActionListener(this);
		jMItemNewGame.addActionListener(this);
		jMItemMyScore.addActionListener(this);
		jMItemRanking.addActionListener(this);
		jMItemStart.addActionListener(this);
		jMItemStop.addActionListener(this);
		jMItemDirections.addActionListener(this);

	}

	public void init() {

		clock = new Clock(clockJLabel);// Digital timer
		clock.start();

		// scoreJLabel = new JLabel("Score: " + score);
		// scoreJLabel.setBounds(180, 10, 150, 30);
		// scoreJLabel.setForeground(Config.ORANGE);
		// scoreJLabel.setFont(Config.F);
		// panel.add(scoreJLabel);

		jcb.setSelectedItem(currSelect);

		person[0] = new Person(0, "Boss", caocaoImageIcon);
		person[1] = new Person(1, "Guan yu", guanyuIcon);
		person[2] = new Person(2, "Zhang fei", zhangfeiImageIcon);
		person[3] = new Person(3, "Liu bei", liubeiIcon);
		person[4] = new Person(4, "Zhang liao", zhangliaoImageIcon);
		person[5] = new Person(5, "Cao Ren", caorenImageIcon);
		person[6] = new Person(6, "Soldier", bingIcon);
		person[7] = new Person(7, "Soldier", bingIcon);
		person[8] = new Person(8, "Soldier", bingIcon);
		person[9] = new Person(9, "Soldier", bingIcon);

		int gapX = 45;
		int gapY = 0;

		if (currSelect.equals(str[0])) {// The first level
			person[0].setBounds(104 + gapX, 84 + gapY, 100, 100);
			person[1].setBounds(104 + gapX, 184 + gapY, 100, 50);
			person[2].setBounds(54 + gapX, 84 + gapY, 50, 100);			
			person[3].setBounds(204 + gapX, 184 + gapY, 50, 100);
			person[4].setBounds(54 + gapX, 184 + gapY, 50, 100);
			person[5].setBounds(204 + gapX, 84 + gapY, 50, 100);
			person[6].setBounds(54 + gapX, 284 + gapY, 50, 50);
			person[7].setBounds(204 + gapX, 284 + gapY, 50, 50);
			person[8].setBounds(104 + gapX, 234 + gapY, 50, 50);
			person[9].setBounds(154 + gapX, 234 + gapY, 50, 50);

		} else if (currSelect.equals(str[1])) {// The second level

			person[0].setBounds(104 + gapX, 84 + gapY, 100, 100);
			person[1].setBounds(104 + gapX, 184 + gapY, 100, 50);
			person[2].setBounds(54 + gapX, 234 + gapY, 50, 100);
			person[3].setBounds(204 + gapX, 234 + gapY, 50, 100);
			person[4].setBounds(54 + gapX, 84 + gapY, 50, 100);
			person[5].setBounds(204 + gapX, 84 + gapY, 50, 100);
			person[6].setBounds(54 + gapX, 184 + gapY, 50, 50);
			person[7].setBounds(204 + gapX, 184 + gapY, 50, 50);
			person[8].setBounds(104 + gapX, 234 + gapY, 50, 50);
			person[9].setBounds(154 + gapX, 234 + gapY, 50, 50);

		} else if (currSelect.equals(str[2])) {// The third level
			
			person[0].setBounds(104 + gapX, 84 + gapY, 100, 100);
			person[1].setBounds(104 + gapX, 234 + gapY, 100, 50);
			person[2].setBounds(54 + gapX, 234 + gapY, 50, 100);
			person[3].setBounds(204 + gapX, 234 + gapY, 50, 100);
			person[4].setBounds(54 + gapX, 84 + gapY, 50, 100);
			person[5].setBounds(204 + gapX, 84 + gapY, 50, 100);
			person[6].setBounds(54 + gapX, 184 + gapY, 50, 50);
			person[7].setBounds(204 + gapX, 184 + gapY, 50, 50);
			person[8].setBounds(104 + gapX, 184 + gapY, 50, 50);
			person[9].setBounds(154 + gapX, 184 + gapY, 50, 50);
			

		} else if (currSelect.equals(str[3])) {// The fourth level
			
			person[0].setBounds(104 + gapX, 84 + gapY, 100, 100);
			person[1].setBounds(104 + gapX, 184 + gapY, 100, 50);
			person[2].setBounds(54 + gapX, 234 + gapY, 50, 100);
			person[3].setBounds(204 + gapX, 234 + gapY, 50, 100);
			person[4].setBounds(54 + gapX, 134 + gapY, 50, 100);
			person[5].setBounds(204 + gapX, 134 + gapY, 50, 100);
			person[6].setBounds(54 + gapX, 84 + gapY, 50, 50);
			person[7].setBounds(204 + gapX, 84 + gapY, 50, 50);
			person[8].setBounds(104 + gapX, 234 + gapY, 50, 50);
			person[9].setBounds(154 + gapX, 234 + gapY, 50, 50);


		} else if (currSelect.equals(str[4])) {// The fifth level
			person[0].setBounds(54 + gapX, 84 + gapY, 100, 100);
			person[1].setBounds(54 + gapX, 184 + gapY, 100, 50);
			person[2].setBounds(54 + gapX, 234 + gapY, 50, 100);
			person[3].setBounds(104 + gapX, 234 + gapY, 50, 100);
			person[4].setBounds(154 + gapX, 84 + gapY, 50, 100);
			person[5].setBounds(204 + gapX, 84 + gapY, 50, 100);
			person[6].setBounds(154 + gapX, 184 + gapY, 50, 50);
			person[7].setBounds(154 + gapX, 234 + gapY, 50, 50);
			person[8].setBounds(204 + gapX, 184 + gapY, 50, 50);
			person[9].setBounds(204 + gapX, 234 + gapY, 50, 50);
			
		}

		for (int k = 0; k < person.length; k++) {
			panel.add(person[k]);
			person[k].addMouseListener(this);
			person[k].setToolTipText(person[k].name);
			person[k].addKeyListener(this);
		}

		left.setBounds(49 + gapX, 79 + gapY, 5, 260);
		right.setBounds(254 + gapX, 79 + gapY, 5, 260);
		above.setBounds(49 + gapX, 79 + gapY, 210, 5);
		below.setBounds(49 + gapX, 334 + gapY, 210, 5);

		validate();
	}

	private void setButton(JButton button) {
		button.setFocusPainted(true);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);

	}

	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {
		playMusic("sounds/SOUND528.WAV");

		Person man = (Person) arg0.getSource();
		int x = -1, y = -1;
		x = arg0.getX();
		y = arg0.getY();
		int w = man.getBounds().width;
		int h = man.getBounds().height;
		if (y > h / 2) {
			go(man, below);
		}
		if (y < h / 2) {
			go(man, above);
		}
		if (x > w / 2) {
			go(man, right);
		}
		if (x < w / 2) {
			go(man, left);
		}

	}

	public void mouseReleased(MouseEvent arg0) {

	}

	public void keyPressed(KeyEvent arg0) {
		Person man = (Person) arg0.getSource();
		playMusic("sounds/SOUND16.WAV");
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			go(man, below);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			go(man, above);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			go(man, left);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			go(man, right);
		}

	}

	private void go(Person man, JButton direction) {

		// Every step, score add 10
		
		boolean move = true;
		Rectangle manRect = man.getBounds();
		int x = man.getBounds().x;
		int y = man.getBounds().y;
		if (direction == below) {
			y = y + 50;
		} else if (direction == above) {
			y = y - 50;
		} else if (direction == left) {
			x = x - 50;
		} else if (direction == right) {
			x = x + 50;
		}
		manRect.setLocation(x, y);
		Rectangle directionRect = direction.getBounds();
		for (int k = 0; k < 10; k++) {
			Rectangle personRect = person[k].getBounds();
			if ((manRect.intersects(personRect)) && (man.number != k)) {
				move = false;
			}
		}
		if (manRect.intersects(directionRect)) {
			move = false;
		}
		if (move == true) {
			man.setLocation(x, y);
			score += 10;
			scoreJLabel.setText("Score: " + score);
		}

	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == mRestart || e.getSource() == jMItemNewGame) {
			// The data recorded
			new Thread(new Runnable() {

				@Override
				public void run() {

					if (score != 0) {
						if (ScoreDao.getInstance().add(
								new Score(score, DateUtils.getNowTime(), clock
										.getTime(), LoginUI.user.getId()))) {
							restart();
						}
					} else {
						restart();
					}

				}

			}).start();

		} else if (e.getSource() == mExit || e.getSource() == jMItemExit) {
			playMusic("sounds/SOUND528.WAV");
			exit();

		} else if (e.getSource() == jMItemMyScore) {
			playMusic("sounds/SOUND528.WAV");

			new Thread(new Runnable() {

				@Override
				public void run() {
					List<Score> all = ScoreDao.getInstance().getAllByUser(
							LoginUI.user.getId());
					if (all.size() > 0) {
						JOptionPane.showMessageDialog(null, getAll(all),
								"My record", JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"Temporarily do not have any information!");
					}

				}
			}).start();

		} else if (e.getSource() == jMItemRanking) {
			playMusic("sounds/SOUND528.WAV");

			new Thread(new Runnable() {

				@Override
				public void run() {
					List<Score> all = ScoreDao.getInstance().getAll();
					if (all.size() > 0) {
						// Display list
						JOptionPane.showMessageDialog(null, getAll(all),
								"Ranking", JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null,
								"Temporarily do not have any information!");
					}

				}
			}).start();

		} else if (e.getSource() == jMItemDirections) {
			playMusic("sounds/SOUND528.WAV");
			JOptionPane
					.showMessageDialog(
							null,
							"HuaRong Road is an ancient Chinese puzzle game, \r\nwhich emerged in the Three Kingdoms period. "
                                                                + "The \r\nmajor and largest block,called Cao Cao(Boss),which \r\nis supposed to escape from "
                                                                + "other surrounding blocks.\r\nTo pass the game you must move the Boss to the exit.\r\nUse "
                                                                + "your spatial imagination and logical thinking abilities\r\nto find a solution.");

		} else if (e.getSource() == jMItemStart) {
			bgMusic.loop();
		} else if (e.getSource() == jMItemStop) {
			bgMusic.stop();
		}

	}

	private void playMusic(String audioPath) {
		Thread startAudio = new Thread(new Audio(audioPath));
		startAudio.start();// Open voice thread
	}

	public void exit() {
		int exi = JOptionPane.showConfirmDialog(null, "To quit the game?",
				"Helpful hints", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (exi == JOptionPane.YES_OPTION) {
			System.exit(0);
		} else {
			return;
		}
	}

	public void restart() {
		score = 0;
		scoreJLabel.setText("Score: " + score);

		// Refresh the interface
		if (clock != null) {
			clock.stop();
			clock = null;
		}

		for (int k = 0; k < person.length; k++) {
			panel.remove(person[k]);
		}

		init();
		panel.updateUI();

		// play music
		playMusic("sounds/wewe.wav");
	}

	// Output charts for the JOptionPane dialog shown in the list
	public JPanel[] getAll(List<Score> scores) {
		JLabel[][] allLabel = new JLabel[scores.size() + 1][5];

		// header
		JLabel rankingLabel = new JLabel("Rank", SwingConstants.CENTER);
		allLabel[0][0] = rankingLabel;
		JLabel nameLabel = new JLabel("Name", SwingConstants.CENTER);
		allLabel[0][1] = nameLabel;
		JLabel scoresLabel = new JLabel("Score", SwingConstants.CENTER);
		allLabel[0][2] = scoresLabel;
		JLabel timeLengthLabel = new JLabel("Duration", SwingConstants.CENTER);
		allLabel[0][3] = timeLengthLabel;
		JLabel timeLabel = new JLabel("Date", SwingConstants.CENTER);
		allLabel[0][4] = timeLabel;

		allLabel[0][0].setFont(Config.F);
		allLabel[0][1].setFont(Config.F);
		allLabel[0][2].setFont(Config.F);
		allLabel[0][3].setFont(Config.F);
		allLabel[0][4].setFont(Config.F);
		allLabel[0][0].setForeground(Config.ORANGE2);
		allLabel[0][1].setForeground(Config.ORANGE2);
		allLabel[0][2].setForeground(Config.ORANGE2);
		allLabel[0][3].setForeground(Config.ORANGE2);
		allLabel[0][4].setForeground(Config.ORANGE2);

		// record
		for (int i = 0; i < scores.size(); i++) {
			Score r = scores.get(i);
			allLabel[i + 1][0] = new JLabel(Integer.toString(i + 1),
					SwingConstants.CENTER);
			allLabel[i + 1][1] = new JLabel(r.getUser().getUserName(),
					SwingConstants.CENTER);
			allLabel[i + 1][2] = new JLabel(r.getScore() + "",
					SwingConstants.CENTER);
			allLabel[i + 1][3] = new JLabel(r.getDuration(),
					SwingConstants.CENTER);
			allLabel[i + 1][4] = new JLabel(r.getTime(), SwingConstants.CENTER);

			allLabel[i + 1][0].setFont(Config.F);
			allLabel[i + 1][1].setFont(Config.F);
			allLabel[i + 1][2].setFont(Config.F);
			allLabel[i + 1][3].setFont(Config.F);
			allLabel[i + 1][4].setFont(Config.F);
			allLabel[i + 1][0].setForeground(Config.ORANGE);
			allLabel[i + 1][1].setForeground(Config.ORANGE);
			allLabel[i + 1][2].setForeground(Config.ORANGE);
			allLabel[i + 1][3].setForeground(Config.ORANGE);
			allLabel[i + 1][4].setForeground(Config.ORANGE);

		}

		// Set the format size
		int height = 15;
		Dimension rankingLabelDimension = new Dimension(50, height);
		Dimension nameLabelDimension = new Dimension(75, height);
		Dimension scoresLabelDimension = new Dimension(40, height);
		Dimension timeLenghtLabelDimension = new Dimension(70, height);
		Dimension timeLabelDimension = new Dimension(150, height);

		for (int i = 0; i < scores.size() + 1; i++) {
			allLabel[i][0].setPreferredSize(rankingLabelDimension);
			allLabel[i][1].setPreferredSize(nameLabelDimension);
			allLabel[i][2].setPreferredSize(scoresLabelDimension);
			allLabel[i][3].setPreferredSize(timeLenghtLabelDimension);
			allLabel[i][4].setPreferredSize(timeLabelDimension);
		}

		// Records are added to the JPanel
		JPanel[] allPanel = new JPanel[scores.size() + 1];
		for (int i = 0; i < scores.size() + 1; i++) {
			allPanel[i] = new JPanel();
			allPanel[i].add(allLabel[i][0]);
			allPanel[i].add(allLabel[i][1]);
			allPanel[i].add(allLabel[i][2]);
			allPanel[i].add(allLabel[i][3]);
			allPanel[i].add(allLabel[i][4]);
		}
		return allPanel;
	}

	public static void main(String[] args) {
		new Hua_Rong_Road("");
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		if (ItemEvent.SELECTED == e.getStateChange()) {
			currSelect = jcb.getSelectedItem().toString();
			if (score != 0) {
				if (ScoreDao.getInstance().add(
						new Score(score, DateUtils.getNowTime(), clock
								.getTime(), LoginUI.user.getId()))) {
					restart();
				}
			} else {
				restart();

			}
		}

	}
}
