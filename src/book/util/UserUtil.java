package book.util;

import javax.servlet.http.HttpSession;

import book.business.User;

public class UserUtil {
	
	private static final String USER_ROLE_ADMIN = "Admin";

	public static void storeLoginedUser(HttpSession session, User user) {
		session.setAttribute("loginedUser",	user);
	}
	
	public static User getLoginedUser(HttpSession session) {
		User user = (User) session.getAttribute("loginedUser");
		return user;
	}
	
	public static void deleteLoginedUser(HttpSession session) {
		session.setAttribute("loginedUser", null);
	}
	
	public static boolean isAdmin(User user) {
		if(user != null) {
			if(user.getRole().equals(USER_ROLE_ADMIN)) {
				return true;
			}
		} 
		return false;
	}
}
