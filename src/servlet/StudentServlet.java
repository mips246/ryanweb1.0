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
import dao.AdminTeacherCourseDAO;
import dao.StudentCourseDAO;
import vo.CourseSelect;

public class StudentServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("../errorpage.jsp").forward(req, resp);
		//doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("进入dopost了");
		resp.setContentType("application/json");
    	resp.setCharacterEncoding("utf-8");
		PrintWriter out=resp.getWriter();
		String method=req.getParameter("method");
		if("loadCourseTable".equals(method)) {
			JSONArray courseList=new JSONArray();
			String userid=req.getParameter("userid");
			try {
				courseList=AdminTeacherCourseDAO.getTeacherSelectedCourseList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(courseList);
		}
		else if("selectCourse".equals(method)) {
			System.out.println("进入了studentservlet的selectCourse分支");
			String selectedcourseid=req.getParameter("courseid");
			String selectedteacherid=req.getParameter("teacherid");
			String studentid=req.getParameter("studentid");
			CourseSelect cs= new CourseSelect();
			cs.setCourseid(selectedcourseid);
			cs.setUserid(studentid);
			cs.setTeacherid(selectedteacherid);
			cs.setGrade(0);
			boolean flag;
			try {
				flag=StudentCourseDAO.insertCourse(cs);
				if(flag==true) {
					System.out.println("选课成功");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JSONObject obj=new JSONObject();
			JSONArray info= new JSONArray();
			try {
				obj.append("courseid", cs.getCourseid());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			info.put(obj);
			out.println(info);
		}
		else if("loadSelectedCourse".equals(method)) {
			String userid=req.getParameter("userid");
			JSONArray selectedcoursetable=new JSONArray();
			try {
				selectedcoursetable=StudentCourseDAO.getSelectedCourseTable(userid);
			} catch (SQLException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(selectedcoursetable);
		}
		else if("x".equals(method)) {
			String userid= req.getParameter("userid");
			JSONArray table=new JSONArray();
			try {
				table=StudentCourseDAO.getTable(userid);
			} catch (SQLException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(table);
		}
	}
}
