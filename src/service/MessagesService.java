package service;

import static util.DBUtil.*;
import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.Messages;
import dao.MessagesDao;

public class MessagesService {

	public static void insertPostMessages(String message) {

		Connection conn = null;
		try {
			// DBへのコネクションを作成する
			conn = getConnection();
			// メッセージをDBに格納
			MessagesDao.insertPostMessages(message, conn);
			// コミット
			commit(conn);
		} catch (SQLException e) {
			rollback(conn);
		} finally {
			close(conn);
		}
	}

	public static List<Messages> selectAllMessages() {

		Connection conn = null;
		try {
			// DBへのコネクションを作成する
			conn = getConnection();
			// メッセージ情報を取得
			List<Messages> messages = MessagesDao.selectAllMessages(conn);
			
			if (messages == null) {
				return null;
			} else {
				return messages;
			}

		} catch (RuntimeException e) {
			rollback(conn);
			throw e;
		} catch (Error e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
	}
}
