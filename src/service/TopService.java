package service;

import static util.DBUtil.close;
import static util.DBUtil.commit;
import static util.DBUtil.getConnection;
import static util.DBUtil.rollback;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MessagesDao;

public class TopService {
	
	public static void insertMessage(String message) {		
		
		Connection conn = null;
		try {
			// DBへのコネクションを作成する
			conn = getConnection();
			MessagesDao.insertMessage(message, conn);
			// コミット
			commit(conn);
		} catch(SQLException e){
			rollback(conn);
		} finally{
			close(conn);
		}
	}
}
