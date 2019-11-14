package filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminRoleFilter implements Filter {
	
	@Override
	public void destroy() {

	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		if ("admin".equals(session.getAttribute("role"))) {
			System.out.println("< AdminRoleFilter Redirect To teacher.jsp >");
			((HttpServletResponse) response).sendRedirect("/MIPS246/admin/admin.jsp");
			//request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

}
