package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.DAOFactory;
import vo.StudentUser;

public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
		req.getRequestDispatcher("/errorpage.jsp").forward(req,resp);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
		System.out.println("<login,进入loginservlet>");
		String pathtrue="main.jsp";
		String pathfalse="login.jsp";
		String userid=req.getParameter("userid");
		String userpass=req.getParameter("userpass");
		String role=req.getParameter("role");
		boolean flag=false;
		List<String>info=new ArrayList<String>();
		if(userid==null||"".equals(userpass)) {
			info.add("密码不能为空");
		}
		if(info.size()==0) {
			//if(role.equals("student")) {}按照es的架构，这里应该判断role来决定到底初始化哪个类
			StudentUser studentuser=new StudentUser();
			studentuser.setUserid(userid);
			studentuser.setPassword(userpass);
			try {
				if(DAOFactory.getIUserDAOInstance().loginCheck(studentuser)) {
					info.add("登陆成功，欢迎"+studentuser.getName());
					//flag=true;
					HttpSession hs=req.getSession();
					hs.setAttribute("userid", userid);
					hs.setAttribute("role", role);
				}else {
					info.add("登录失败");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		req.setAttribute("info", info);
		
		if(flag==true) {
			req.getRequestDispatcher(pathtrue).forward(req,resp);
			//resp.sendRedirect("main.jsp");
		}else {
			req.getRequestDispatcher(pathfalse).forward(req,resp);
		}
		
	}
}
