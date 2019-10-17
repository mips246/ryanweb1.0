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
	public void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
		String pathtrue="main.jsp";
		String pathfalse="login.jsp";
		String userid=req.getParameter("userid");
		String userpass=req.getParameter("userpass");
		String usertype=req.getParameter("usertype");
		boolean flag=false;
		List<String>info=new ArrayList<String>();
		if(userid==null||"".equals(userpass)) {
			info.add("ÃÜÂë²»ÄÜÎª¿Õ");
		}
		if(info.size()==0) {
			StudentUser studentuser=new StudentUser();
			studentuser.setUserid(userid);
			studentuser.setPassword(userpass);
			try {
				if(DAOFactory.getIUserDAOInstance().loginCheck(studentuser)) {
					info.add("µÇÂ½³É¹¦£¬»¶Ó­"+studentuser.getName());
					//flag=true;
					HttpSession hs=req.getSession();
					hs.setAttribute("userid", userid);
				}else {
					info.add("µÇÂ¼Ê§°Ü");
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
	public void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
		this.doGet(req,resp);
	}
}
