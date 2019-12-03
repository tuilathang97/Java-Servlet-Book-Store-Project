package book.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.business.User;
import book.util.UserUtil;

@WebFilter(urlPatterns = {"/home"})
public class HomeFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("Home Filter");
		
		HttpServletRequest req = (HttpServletRequest) request; 
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		User user = (User) UserUtil.getLoginedUser(session);
		if(user != null && UserUtil.isAdmin(user)) {
			resp.sendRedirect(req.getContextPath() + "/admin/home");
		} else {
			chain.doFilter(request, response);
		}
	}

}
