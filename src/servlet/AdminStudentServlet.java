package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dao.AdminDAO;
import vo.StudentUser;

public class AdminStudentServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("../errorpage.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("½øÈëadmin_student post");
		resp.setContentType("application/json");
    	resp.setCharacterEncoding("utf-8");
		PrintWriter out=resp.getWriter();
		String method=req.getParameter("method");
		JSONArray returninfo=new JSONArray();
		if(method.equals("loadStudentTable")) {
			JSONArray studentlist=new JSONArray();
			try {
				studentlist=AdminDAO.getStudentList();
			} catch (SQLException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(studentlist);
		}
		else if(method.equals("addStudent")) {
			String sid=req.getParameter("studentid");
			String sname=req.getParameter("name");
			String password=req.getParameter("password");
			System.out.println("id:"+sid);
			StudentUser s=new StudentUser();
			s.setUserid(sid);
			s.setName(sname);
			s.setPassword(password);
			AdminDAO.insert(s);
			JSONObject obj=new JSONObject();
			try {
				obj.append("id", s.getUserid());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			returninfo.put(obj);
			out.println(returninfo);
		}
		else if(method.equals("deleteStudent")) {
			String sid=req.getParameter("studentid");
			StudentUser s=new StudentUser();
			s.setUserid(sid);
			AdminDAO.delete(s);
			JSONObject obj=new JSONObject();
			try {
				obj.append("id", s.getUserid());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			returninfo.put(obj);
			out.println(returninfo);
		}
	}
}
