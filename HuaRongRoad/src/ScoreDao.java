import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ScoreDao {

	private static ScoreDao dao = new ScoreDao();

	private ScoreDao() {

	}

	public static ScoreDao getInstance() {
		return dao;
	}

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet rs;

	public boolean add(Score u) {

		try {
			conn = JDBCUtils.connDb();

			String sql = "insert into t_scores VALUES(null,?,?,?,?)";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, u.getScore());
			prep.setString(2, u.getTime());
			prep.setString(3, u.getDuration());
			prep.setInt(4, u.getUserId());

			int count = prep.executeUpdate();
			if (count == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeDb(conn, prep, rs);
		}
		return false;
	}

	public List<Score> getAll() {
		List<Score> list = new LinkedList<Score>();
		try {
			conn = JDBCUtils.connDb();
			String sql = "select t1.id, t1.score, t1.time, t1.duration,t1.userId, t2.userName  "
					+ "from t_scores t1, t_users t2 where t1.userId = t2.id  order by t1.score desc";
			prep = conn.prepareStatement(sql);
			rs = prep.executeQuery();

			while (rs.next()) {
				Score score = new Score(rs.getInt("id"), rs.getInt("score"),
						rs.getString("time"), rs.getString("duration"),rs.getInt("userId"));

				User user = new User();
				user.setId(rs.getInt("userId"));;
				user.setUserName(rs.getString("userName"));
				score.setUser(user);

				list.add(score);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeDb(conn, prep, rs);
		}
		return list;
	}
	
	public List<Score> getAllByUser(int userId) {
		List<Score> list = new LinkedList<Score>();
		try {
			conn = JDBCUtils.connDb();
			String sql = "select t1.id, t1.score, t1.time, t1.duration, t1.userId, t2.userName  "
					+ "from t_scores t1, t_users t2 where t1.userId = t2.id and t1.userId=?  order by t1.score desc";
			prep = conn.prepareStatement(sql);
			prep.setInt(1, userId);
			rs = prep.executeQuery();

			while (rs.next()) {
				Score score = new Score(rs.getInt("id"), rs.getInt("score"),
						rs.getString("time"), rs.getString("duration"), rs.getInt("userId"));

				User user = new User();
				user.setId(rs.getInt("userId"));;
				user.setUserName(rs.getString("userName"));
				score.setUser(user);

				list.add(score);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeDb(conn, prep, rs);
		}
		return list;
	}

}
