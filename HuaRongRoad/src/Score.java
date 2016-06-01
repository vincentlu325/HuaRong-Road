public class Score {

	private int id;
	private int score;
	private String time;
	private String duration;
	private int userId;

	private User user;

	public Score() {
		super();
	}

	public Score(int score, String time, String duration, int userId) {
		super();
		this.score = score;
		this.time = time;
		this.duration = duration;
		this.userId = userId;
	}

	public Score(int id, int score, String time, String duration, int userId) {
		super();
		this.id = id;
		this.score = score;
		this.time = time;
		this.duration = duration;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
