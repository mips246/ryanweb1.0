package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAO;
import dao.TeacherDAO;
import vo.StudentUser;
import vo.TeacherUser;

public class LoginServletNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String errorCode = "-1";
    private final String successCode = "0";
    String studentpath="main.jsp";
    String teacherpath="teachermain.jsp";
	String pathfalse="login.jsp";
    public LoginServletNew() {
    	super();
    }
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
    	req.getRequestDispatcher("/errorpage.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
    	System.out.println("login操作");
    	resp.setContentType("application/json");
    	resp.setCharacterEncoding("utf-8");
    	resp.setStatus(resp.SC_MOVED_TEMPORARILY);
    	String role=req.getParameter("role");
    	String username=req.getParameter("userid");
    	String password=req.getParameter("userpass");
    	System.out.println("Login User: "+username+" password: "+password+" 角色:"+role);
    	if(role.equals("student")) {
    		System.out.println("进入student分支");
    		StudentUser user=StudentDAO.login(username, password);
    		if(user==null) {
    			System.out.println("login fail");
    			req.setAttribute("u_error", errorCode);
    			req.getRequestDispatcher(pathfalse).forward(req,resp);
    		}else {
    			req.setAttribute("u_error", successCode);
    			req.setAttribute("u_id", user.getUserid());
    			req.getRequestDispatcher(studentpath).forward(req,resp);
    		}
    	}else if(role.equals("teacher")){
    		TeacherUser user=TeacherDAO.login(username, password);
    		if(user==null) {
    			System.out.println("login failed :"+username);
				req.setAttribute("u_error", errorCode);
				getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
				return ;
    		}else {
    			req.setAttribute("u_error", successCode);
    			req.setAttribute("u_name", user.getName());
    			req.getRequestDispatcher(teacherpath).forward(req,resp);
    		}
    	}else if(role.equals("admin")) {
    		
    	}
    }
}
