package book.modal;

import java.sql.*;
import java.util.*;
import book.business.*;


public class BookDB {
	
	public static void insertBook(String title, String image, String desc, String author, String price) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getMySqlConnection();
		PreparedStatement pstm = null;
		String sql = "INSERT INTO book(title, image, description, author, price) VALUES (?,?,?,?,?)";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, title);
			pstm.setString(2, image);
			pstm.setString(3, desc);
			pstm.setString(4, author);
			pstm.setString(5, price);
			
			int result = pstm.executeUpdate();
			if(result > 0) {
				System.out.println("insert succesfully");
			} else {
				System.out.println("insert fail");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePrepareStatement(pstm);
			pool.freeConnection(conn);
		}
	}
	
	public static boolean deleteBook(String id) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getMySqlConnection();
		PreparedStatement pstm = null;
		String sql = "UPDATE book SET isActive = 0 WHERE idbook = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			int result = pstm.executeUpdate();
			if(result > 0) {
				return true;
			}
			
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.closePrepareStatement(pstm);
			pool.freeConnection(conn);
		}
	}

	
	public static void updateBook(String id, String title, String image, String desc, String author, String price) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getMySqlConnection();
		PreparedStatement pstm = null;
		String sql = "UPDATE book SET title=?, image=?, description=?, author=?, price=? WHERE idbook = ?";
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, title);
			pstm.setString(2, image);
			pstm.setString(3, desc);
			pstm.setString(4, author);
			pstm.setString(5, price);
			pstm.setString(6, id);
			
			int result = pstm.executeUpdate();
			if(result > 0) {
				System.out.println("update succesfully");
			} else {
				System.out.println("update fail");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closePrepareStatement(pstm);
			pool.freeConnection(conn);
		}
	}
	
	public static Book selectBook(String id){
			
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection conn = pool.getMySqlConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			String sql = "SELECT idbook, Title, Image, Description, Author, Price FROM book WHERE idbook = ?";
			
			try {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, id);
				rs = pstm.executeQuery();
				while(rs.next()) {
					Book book;
					int	   idbook 	= rs.getInt("idbook");
					String title 	= rs.getString("Title");
					String image 	= rs.getString("Image");
					String desc  	= rs.getString("Description");
					String author	= rs.getString("Author");
					double price 	= rs.getDouble("Price");
					book = new Book(idbook, title, image, desc, author, price);
					
					return book;
				}
				
				return null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} finally {
				DBUtil.closeResultSet(rs);
				DBUtil.closePrepareStatement(pstm);
				pool.freeConnection(conn);
			}
			
	}

	public static List<Book> selectAllBooks(){
		
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getMySqlConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT idbook, Title, Image, Description, Author, Price FROM book";
		
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			List<Book> list = new ArrayList<Book>();
			while(rs.next()) {
				Book book;
				int	   idbook 	= rs.getInt("idbook");
				String title 	= rs.getString("Title");
				String image 	= rs.getString("Image");
				String desc  	= rs.getString("Description");
				String author	= rs.getString("Author");
				double price 	= rs.getDouble("Price");
				book = new Book(idbook, title, image, desc, author, price);
				
				list.add(book);
			}
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePrepareStatement(pstm);
			pool.freeConnection(conn);
		}
		
	}
	
	public static List<Book> selectActiveBooks(){
			
			ConnectionPool pool = ConnectionPool.getInstance();
			Connection conn = pool.getMySqlConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			String sql = "SELECT idbook, Title, Image, Description, Author, Price FROM book WHERE isActive = 1";
			
			try {
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				List<Book> list = new ArrayList<Book>();
				while(rs.next()) {
					Book book;
					int	   idbook 	= rs.getInt("idbook");
					String title 	= rs.getString("Title");
					String image 	= rs.getString("Image");
					String desc  	= rs.getString("Description");
					String author	= rs.getString("Author");
					double price 	= rs.getDouble("Price");
					book = new Book(idbook, title, image, desc, author, price);
					
					list.add(book);
				}
				
				return list;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} finally {
				DBUtil.closeResultSet(rs);
				DBUtil.closePrepareStatement(pstm);
				pool.freeConnection(conn);
			}
			
		}
	
}
