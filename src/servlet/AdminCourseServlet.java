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

public class AdminCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("����dopost");
		resp.setContentType("application/json");
    	resp.setCharacterEncoding("utf-8");
		PrintWriter out=resp.getWriter();
		String method=req.getParameter("method");
		JSONArray courseList=new JSONArray();
		JSONObject obj=new JSONObject();
		try {
			System.out.println("����TRY");
			obj.append("courseid", "123");
			obj.append("coursename", "��������ԭ��");
			obj.append("studentcount", "50");
			obj.append("createtime", "20191001");
			courseList.put(obj);
			out.println(courseList);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(method.equals("loadCourse")) {
			System.out.println("load course��");
			try{
				courseList=AdminDAO.getCourseList();
			}catch(SQLException|JSONException e) {
				e.printStackTrace();
			}
		}else if(method.equals("addCourse")) {
			try {
				
			}catch() {
				
			}
		}
		
	}
}
