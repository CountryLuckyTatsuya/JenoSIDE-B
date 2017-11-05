package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static util.CloseableUtil.*;

import bean.Messages;
import exception.SQLRuntimeException;

public class MessagesDao {

	public static void insertPostMessages(String message, Connection conn) {	
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
			throw new SQLRuntimeException(e);
		} finally {
			// クローズ処理
			close(ps);
		}
	}
	
	public static List<Messages> selectAllMessages(Connection conn) {
		// 変数定義
		PreparedStatement ps = null;
		// SQL文を定義する
		String sql = "SELECT * FROM MESSAGES";
		try {
			// new messages
			List<Messages> messages = new ArrayList<>();
			// 実行するSQL文とパラメータを指定する
			ps = conn.prepareStatement(sql);
			// INSERT文を実行する
			ps.executeQuery();

			return messages;

		} catch (SQLException e) {
			// 例外発生時の処理
			throw new SQLRuntimeException(e);
		} finally {
			// クローズ処理
			close(ps);
		}
	}

}
