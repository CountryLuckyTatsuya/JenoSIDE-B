package service;

import static util.DBUtil.commit;
import static util.DBUtil.getConnection;
import static util.DBUtil.rollback;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.Users;
import dao.UsersDao;

public class UsersService {
	
	public static Users selectLoginUsers(String loginId, String password) throws SQLException {
		// TODO 自動生成されたメソッド・スタブ
		
		Connection conn = null;
		
		try {
			conn = getConnection();
			Users users = UsersDao.selectLoginUsers(loginId, password, conn);
			return users;
		} catch (SQLException e) {
			rollback(conn);
			throw e;
		} finally {
			close (conn);
		}
	}	
}