package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import vo.Admin;
import vo.StudentUser;
import vo.TeacherUser;

public class LoginServletNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final String errorCode = "-1";
    private final String successCode = "0";
    String studentpath="student/student_main.jsp";
    String teacherpath="teacher/teacher.jsp";
	String pathfalse="login.jsp";
	String adminpath="admin/admin.jsp";
	
    public LoginServletNew() {
    	super();
    }
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
    	req.getRequestDispatcher("/errorpage.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException {
    	System.out.println("login操作");
    	List<String>info=new ArrayList<String>();
    	resp.setContentType("application/json");
    	resp.setCharacterEncoding("utf-8");
    	String role=req.getParameter("role");
    	String userid=req.getParameter("userid");
    	String password=req.getParameter("userpass");
    	System.out.println("Login User: "+userid+" password: "+password+" 角色:"+role);
    	if(role.equals("student")) {
    		System.out.println("进入student分支");
    		StudentUser user=StudentDAO.login(userid, password);
    		if(user==null) {
    			System.out.println("login fail");
    			req.setAttribute("u_error", errorCode);
    			info.add("登陆失败");
    			//req.getRequestDispatcher(pathfalse).forward(req,resp);
    			resp.sendRedirect(pathfalse);
    			return;
    			
    		}else {
    			req.setAttribute("u_error", successCode);
    			req.setAttribute("u_id", user.getUserid());
    			info.add("登陆成功，欢迎"+user.getName());
				HttpSession hs=req.getSession();
				hs.setAttribute("userid", userid);
				hs.setAttribute("role", role);
    			//req.getRequestDispatcher(studentpath).forward(req,resp);
    			resp.sendRedirect(studentpath);
    		}
    	}else if(role.equals("teacher")){
    		TeacherUser user=TeacherDAO.login(userid, password);
    		if(user==null) {
    			System.out.println("login failed :"+userid);
				req.setAttribute("u_error", errorCode);
				//getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
				resp.sendRedirect(pathfalse);
				return ;
    		}else {
    			//req.setAttribute("u_error", successCode);
    			//req.setAttribute("u_name", user.getName());
    			//req.setAttribute("u_description", user.getDescription());
    			info.add("登陆成功，欢迎"+user.getName());
				HttpSession hs=req.getSession();
				hs.setAttribute("userid", userid);
				hs.setAttribute("role", role);
				hs.setAttribute("u_error", successCode);
				hs.setAttribute("u_name", user.getName());
				hs.setAttribute("u_description", user.getDescription());
    			//req.getRequestDispatcher(teacherpath).forward(req,resp);
    			resp.sendRedirect(teacherpath);
    		}
    	}else if(role.equals("admin")) {
    		Admin user=AdminDAO.login(userid,password);
    		System.out.println(user);
    		if(user==null) {
    			System.out.println("login failed :"+userid);
				req.setAttribute("u_error", errorCode);
				getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
				return ;
    		}else {
    			req.setAttribute("u_error", successCode);
    			req.setAttribute("u_name", user.getName());
    			info.add("登陆成功，欢迎"+user.getName());
				HttpSession hs=req.getSession();
				hs.setAttribute("userid", userid);
				hs.setAttribute("role", role);
    			//req.getRequestDispatcher(adminpath).forward(req,resp);
    			resp.sendRedirect(adminpath);
    		}
    	}
    }
}
