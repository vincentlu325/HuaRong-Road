
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

	private static UserDao dao = new UserDao();

	private UserDao() {

	}

	public static UserDao getInstance() {
		return dao;
	}

	private Connection conn;
	private PreparedStatement prep;
	private ResultSet rs;

	public boolean add(User u) {

		try {
			conn = JDBCUtils.connDb();

			String sql = "insert into t_users VALUES(null,?,?)";
			prep = conn.prepareStatement(sql);
			prep.setString(1, u.getUserName());
			prep.setString(2, u.getPassword());

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

	/**
	 * 
	 * @param u
	 * @return
	 */
	public User login(User u) {
		try {
			conn = JDBCUtils.connDb();
			String sql = "select * from t_users where userName = ? and password = ?";
			prep = conn.prepareStatement(sql);
			prep.setString(1, u.getUserName());
			prep.setString(2, u.getPassword());
			rs = prep.executeQuery();
			if (rs.next()) {
				User s = new User(rs.getInt("id"), rs.getString("userName"),
						rs.getString("password"));
				return s;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.closeDb(conn, prep, rs);
		}
		return null;
	}


}
