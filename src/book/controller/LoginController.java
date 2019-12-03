package book.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.business.User;
import book.modal.UserDB;
import book.util.UserUtil;

@WebServlet(urlPatterns = {"/login","/logout"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String URI = request.getRequestURI();
		
		if(URI.endsWith("/login")) {
			request.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(request, response);
		} else if (URI.endsWith("/logout")) {
			System.out.println("Log out");
			HttpSession session = request.getSession();
			UserUtil.deleteLoginedUser(session);
			response.sendRedirect(request.getContextPath() + "/home");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post LoginView.jsp");
		HttpSession session = request.getSession();
		
		String userName 	= request.getParameter("userName");
		String password 	= request.getParameter("password");
		String errorString 	= "";
		boolean hasError 	= false;
		if(userName != null || password != null) {
			User user = UserDB.selectUser(userName, password);
			if(user == null) {
				hasError 	= true;
				errorString = "Wrong user name or password";
 			} else {
				UserUtil.storeLoginedUser(session, user);
			}
		} else {
			hasError 	= true;
			errorString = "User name or Password cannot be empty";
		}
		
		if(hasError) {
			request.setAttribute("errorString", errorString);
			request.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}

}
