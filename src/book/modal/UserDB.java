package book.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import book.business.User;

public class UserDB {

	public static User selectUser(String userName, String password) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getMySqlConnection();
		ResultSet rs = null;
		PreparedStatement pstm = null;
		
		String sql = "SELECT * FROM user WHERE userName = ? and password = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, userName);
			pstm.setString(2, password);
			
			 rs = pstm.executeQuery();
			
			if(rs.next()) {
				String _userName = rs.getString("userName");
				String _role 	= rs.getString("role");
				User user = new User(_userName, _role);
				return user;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePrepareStatement(pstm);
			pool.freeConnection(conn);
		}
		return null;
	}
	
	//TODO createUser deleteUser ... 
}
