
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Using the database connection and close the database connection pool
 */
public class JDBCUtils {

	/**
	 * Obtain a database connection, through c3p0 connection pool. Read
	 * automatically c3p0 - config. XML
	 */
	private static DataSource dataSource = new ComboPooledDataSource();

	/**
	 * Returns the data source
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Get a connection
	 * 
	 * @return
	 */
	public static Connection connDb() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Release the connection
	 * 
	 * @param conn
	 * @param stat
	 * @param rs
	 */
	public static void closeDb(Connection conn, Statement stat, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stat != null) {
				stat.close();
				stat = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
