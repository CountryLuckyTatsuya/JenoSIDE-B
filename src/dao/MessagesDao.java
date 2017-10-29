package dao;

import static util.DBUtil.close;
import static util.DBUtil.commit;
import static util.DBUtil.getConnection;
import static util.DBUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessagesDao {

	public static void insertMessage(String message, Connection conn) throws SQLException{	
		
		// 変数定義
		PreparedStatement ps = null;

		// SQL文を定義する
		String sql = "INSERT INTO MESSAGES (MESSAGE) VALUES (?)";

		try {
			// 実行するSQL文とパラメータを指定する
			ps = conn.prepareStatement(sql);
			ps.setString(1, message);

			// INSERT文を実行する
			ps.execute();

		} catch (SQLException e) {
			// 例外発生時の処理
			throw e;
		} finally {
			// クローズ処理
			ps.close();
		}
	}

}
