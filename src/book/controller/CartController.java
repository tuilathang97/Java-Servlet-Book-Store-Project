package book.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.business.Book;
import book.business.Item;
import book.modal.BookDB;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action == null) {
			doGetView(req, resp);
		} else if(action.equalsIgnoreCase("buy")) {
			doGetBuy(req, resp);
		} else if(action.equalsIgnoreCase("remove")) {
			doGetRemove(req, resp);
		} else if(action.equalsIgnoreCase("update")) {
			doGetUpdate(req, resp);
		} 
	}
	
	protected void doGetView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/CartView.jsp").forward(req, resp);
	}
	
	protected void doGetBuy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		int quantity = 1;
		HttpSession session = req.getSession();
		List<Item> list = (List<Item>) session.getAttribute("cartList");
		
		if(list == null) {
			list = new ArrayList<Item>();
			Book book = (Book) BookDB.selectBook(id);
			Item item = new Item(book, quantity);
			list.add(item);
		} else {
			int index = isExist(list, id);
			if(index == -1) {
				System.out.println("add new book");
				Book book = (Book) BookDB.selectBook(id);
				Item item = new Item(book, quantity);
				list.add(item);
			} else {
				System.out.println("add existed book");
				quantity = list.get(index).getQuantity() + 1;
				list.get(index).setQuantity(quantity); 
			}
		}
		System.out.println("Book in cart atm");
		for(Item i : list) {
			System.out.println("Title: " + i.getBook().getTitle() + " - Quantity: " +  i.getQuantity());
		}
		session.setAttribute("cartList", list);
		resp.sendRedirect("home");
	}
	
	protected void doGetRemove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		List<Item> list = (List<Item>) session.getAttribute("cartList");
		
		if(list != null) {
			int index = isExist(list, id);
			if(index != -1) {
				list.remove(index);
				session.setAttribute("cartList", list);
			} 
		}

		resp.sendRedirect("cart");
	}
	
	protected void doGetUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String quantity = req.getParameter("updateQuantity");
		HttpSession session = req.getSession();
		List<Item> list = (List<Item>) session.getAttribute("cartList");
		
		if(list != null) {
			int index = isExist(list, id);
			if(index != -1) {
				list.get(index).setQuantity(Integer.parseInt(quantity));
				session.setAttribute("cartList", list);
			} 
		}

		resp.sendRedirect("cart");
	}
	
	protected int isExist(List<Item> list, String id) {
		
		for(int i = 0; i < list.size(); i++) {
			
			long Idbook = list.get(i).getBook().getIdbook();
			if(Long.toString(Idbook).equalsIgnoreCase(id)) {
				return i;
			}
		}
		
		return -1;
	}
	
}
