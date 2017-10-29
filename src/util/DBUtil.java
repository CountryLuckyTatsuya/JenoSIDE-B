package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.SQLRuntimeException;

public class DBUtil {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/JenoSide_db";
	private static String user = "root";
	private static String password = "";

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	/**
	 * コネクションの取得
	 * @return
	 */
	public static Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
			return con;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	/**
	 * コネクションをコミット
	 * @param con
	 * @throws SQLException
	 */
	public static void commit(Connection con) throws SQLException {
		try {
			if (con != null) {
				con.commit();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	/**
	 * ロールバックを行う
	 * @param con
	 * @throws SQLException
	 */
	public static void rollback(Connection con) {
		try {
			if (con != null) {
				con.rollback();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	/**
	 * クローズ処理（コネクションの解放）を行う
	 * @param con
	 * @throws SQLException
	 */
	public static void close(Connection con) {

		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

}
