package book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.business.Book;
import book.modal.BookDB;

/**
 * Servlet implementation class AdminController
 */
@WebServlet(urlPatterns = {"/admin/*", "/admin/function"})
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String URI = request.getRequestURI();
		
		String action = request.getParameter("action");
		if(URI.endsWith("/home")) {
			doGetAdminHome(request, response);
		} else if(URI.endsWith("/function")){
			if(action == null) {
				doGetAdminHome(request, response);
			} else if(action.equalsIgnoreCase("edit")) {
				doGetEdit(request, response);
			} else if(action.equalsIgnoreCase("delete")) {
				doGetDelete(request, response);
			} else if(action.equalsIgnoreCase("insert")) {
				doGetInsert(request, response);
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String URI = request.getRequestURI();
		String action = request.getParameter("action");
		if(URI.endsWith("/home")) {
			doGetAdminHome(request, response);
		} else if(URI.endsWith("/function")){
			if(action == null) {
				doGetAdminHome(request, response);
			} else if(action.equalsIgnoreCase("edit")) {
				doPostEdit(request, response);
			} else if(action.equalsIgnoreCase("insert")) {
				doPostInsert(request, response);
			}
		}
	}

	protected void doGetAdminHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> bookList = BookDB.selectActiveBooks();
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("/WEB-INF/views/HomeViewAdmin.jsp").forward(request, response);
	}
	
	protected void doGetEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println("Edit idbook: " + id);
		Book book = BookDB.selectBook(id);
		request.setAttribute("editBook", book);
		request.getRequestDispatcher("/WEB-INF/views/EditView.jsp").forward(request, response);
	}
	
	protected void doPostEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id	 	= request.getParameter("id");
		String title 	= request.getParameter("title");
		String image 	= request.getParameter("image");
		String desc 	= request.getParameter("desc");
		String author 	= request.getParameter("author");
		String price 	= request.getParameter("price");
		BookDB.updateBook(id, title, image, desc, author, price);
		response.sendRedirect("home");
	}
	
	protected void doGetDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("delete book");
		String id = request.getParameter("id");
		BookDB.deleteBook(id);
		response.sendRedirect("home");
	}
	
	protected void doGetInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/InsertView.jsp").forward(request, response);
	}
	
	protected void doPostInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title 	= request.getParameter("title");
		String image 	= request.getParameter("image");
		String desc 	= request.getParameter("desc");
		String author 	= request.getParameter("author");
		String price 	= request.getParameter("price");
		BookDB.insertBook(title, image, desc, author, price);
		response.sendRedirect("home");
	}
}
