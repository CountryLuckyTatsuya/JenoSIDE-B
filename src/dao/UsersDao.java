package dao;

import static util.DBUtil.commit;
import static util.DBUtil.getConnection;
import static util.DBUtil.rollback;

import static util.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Users;

//        } catch (Exception ex) {
//            //例外発生時の処理
//        	rollback(conn);       //ロールバックする
//            ex.printStackTrace();  //エラー内容をコンソールに出力する
//        } finally {
//            //クローズ処理
//            close(conn);
//        }
public class UsersDao {

	// public static void main(String[] args) throws Exception{
	//public static Users selectUsers(String loginID, String PASS) {
	public static Users selectLoginUsers(String loginID, String PASS, Connection conn) throws SQLException{	
	
		// 変数定義
		//Connection conn = null;
		PreparedStatement ps = null;
			
			// SQL文を定義する
			String sql = "SELECT * FROM USERS WHERE LOGIN_ID = ? AND PASSWORD = ?";
			
			try {
				// 実行するSQL文とパラメータを指定する
				ps = conn.prepareStatement(sql);
				ps.setString(1, loginID);
				ps.setString(2, PASS);

				ResultSet resultSet = ps.executeQuery();
				List<Users> users = mappingUsers(resultSet);
				
				if(users.isEmpty()==true ){
					return null ;
				}else {
					return users.get(0);
				}
			} catch (SQLException e) {
				throw e;
			} finally {
				ps.close();
			}
	}
	private static List<Users> mappingUsers(ResultSet resultSet) throws SQLException {

		try {
			List<Users> users = new ArrayList<Users>();
			Users user = new Users();
			
			while (resultSet.next()) {
				user.setId(resultSet.getInt("ID"));
				user.setName(resultSet.getString("NAME"));
				user.setName_kana(resultSet.getString("NAME_KANA"));
				user.setLogin_id(resultSet.getString("LOGIN_ID"));
				user.setPassword(resultSet.getString("PASSWORD"));
				user.setActor(resultSet.getString("ACTOR"));
				user.setIsnert_date(resultSet.getDate("INSERT_DATE"));
				user.setUpdate_date(resultSet.getDate("UPDATE_DATE"));
			users.add(user);
			}
			return users;
		} catch(SQLException e) {
			throw e;
		}
		finally {
			resultSet.close();
		}
	}
}