package book.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.business.User;
import book.modal.UserDB;
import book.util.UserUtil;

/**
 * Servlet Filter implementation class HomeFilter
 */
@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    public AdminFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("Admin filter");
		
		HttpServletRequest  req	 = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		User user = (User) UserUtil.getLoginedUser(session);
		
		if(user != null && UserUtil.isAdmin(user)) {
			chain.doFilter(request, response);
		} else {
			System.out.println("Admin authoriazation fail -> send to /home");
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
