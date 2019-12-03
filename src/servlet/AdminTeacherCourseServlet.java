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
import dao.TeacherDAO;
import vo.CourseTeacher;

public class AdminTeacherCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AdminTeacherCourseServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void init() throws ServletException {
		// Put your code here
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
		String method = request.getParameter("method");
		System.out.println("< AdminTeacherCourseServlet >");
		
		if("selectCourse".equals(method)) {
			System.out.println("< Addmin Teacher Select Course >");
			
			CourseTeacher ct = new CourseTeacher();
			String teacherid = request.getParameter("teacherid");
			String teachername = request.getParameter("teachername");
			String courseid = request.getParameter("courseid");
			String coursename = request.getParameter("coursename");
			ct.setTeacherid(teacherid);
			ct.setTeachername(teachername);
			ct.setCourseid(courseid);
			ct.setCoursename(coursename);
			
			if(AdminTeacherCourseDAO.insert(ct)) {
				System.out.println("< Addmin Teacher Select Course Successful >");
				try {
					jsonObject.append("message", "Successful");
					jsonArray.put(jsonObject);
					out.println(jsonArray);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {
				System.out.println("< Addmin Teacher Select Course Failed >");
			}
        	
		}
		
		else if("loadCourse".equals(method)) {
			System.out.println("< Addmin Teacher Load All Course Table >");
			String teacherid = request.getParameter("teacherid");
			try{
				jsonArray = TeacherDAO.getCourseListIsSelected(teacherid);
			}catch(SQLException|JSONException e) {
				e.printStackTrace();
			}
			out.println(jsonArray);
		}
		
		else if("selectTheTeacherCourse".equals(method)) {//遍历给定teacherid的老师的课
			System.out.println("< Load The Teacher Course Table >");
			
			String teacherid = request.getParameter("teacherid");
			
			try{
				jsonArray = AdminTeacherCourseDAO.getTheTeacherCourseList(teacherid);
			}catch(SQLException|JSONException e) {
				e.printStackTrace();
			}
			out.println(jsonArray);
		}
	}
}
