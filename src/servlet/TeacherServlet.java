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
import dao.FileDAO;
import dao.TeacherDAO;
import vo.MyFile;

public class TeacherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		// Put your code here
	}
	
	public TeacherServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
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
		
		if("updatePwd".equals(method)) {
			System.out.println("< Teacher Modify Password >");
			String oldPassword  = request.getParameter("oldPassword");		//‘≠√‹¬Î
			String password     = request.getParameter("newPassword");		//–¬√‹¬Î
			String teacherid    = request.getParameter("userid");
			
			boolean ret = TeacherDAO.updatePwd(teacherid, oldPassword, password);
			
			if (ret) {
				System.out.println("< Teacher Modify Password Success >");
				try {
					//jsonObject.append("statusCode", 1);
					jsonObject.append("message", "success");
					jsonArray.put(jsonObject);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("< Teacher Modify Password Failed >");
				try {
					//jsonObject.append("statusCode", 0);
					jsonObject.append("message", "failed");
					jsonArray.put(jsonObject);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			out.println(jsonArray);
		}
		else if("selectCourseSelectAndStudentName".equals(method)) {
			System.out.println("< Teacher Load Course Select Table And Student Table >");
			String teacherid = request.getParameter("teacherid");
			String courseid = request.getParameter("courseid");
			
			try{
				jsonArray = TeacherDAO.getCourseSelectAndStudentNameList(teacherid, courseid);
			}catch(SQLException|JSONException e) {
				e.printStackTrace();
			}
			out.println(jsonArray);
			
		}
		else if("updateGrade".equals(method)) {
			System.out.println("< Teacher Update Grade >");
			String teacherid = request.getParameter("teacherid");
			String courseid = request.getParameter("courseid");
			String studentid = request.getParameter("studentid");
			int grade = Integer.parseInt(request.getParameter("grade"));
			boolean ret = TeacherDAO.updateGrade(teacherid, courseid, studentid, grade);
			
			if (ret) {
				System.out.println("< Teacher Update Grade Success >");
				try {
					//jsonObject.append("statusCode", 1);
					jsonObject.append("message", "success");
					jsonArray.put(jsonObject);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("< Teacher Update Grade Failed >");
				try {
					//jsonObject.append("statusCode", 0);
					jsonObject.append("message", "failed");
					jsonArray.put(jsonObject);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			out.println(jsonArray);
			
		}
		else if("updateHWGrade".equals(method)) {
			System.out.println("< Teacher Update Homework Grade >");
			/*
			String filename = request.getParameter("filename");
			String courseid = request.getParameter("courseid");
			String studentid = request.getParameter("studentid");
			*/
			int fileno = Integer.parseInt(request.getParameter("fileno"));
			int grade = Integer.parseInt(request.getParameter("grade"));
			
			//boolean ret = FileDAO.updateHWGrade(filename, courseid, studentid, grade);
			boolean ret = FileDAO.updateHWGrade(fileno, grade);
			
			if (ret) {
				System.out.println("< Teacher Update Homework Grade Success >");
				try {
					//jsonObject.append("statusCode", 1);
					jsonObject.append("message", "success");
					jsonArray.put(jsonObject);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("< Teacher Update Homework Grade Failed >");
				try {
					//jsonObject.append("statusCode", 0);
					jsonObject.append("message", "failed");
					jsonArray.put(jsonObject);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			out.println(jsonArray);
			
		}
		else if("selectStudentHomeworkList".equals(method)) {
			System.out.println("< Teacher Get Student Homework List >");
			String courseid = request.getParameter("courseid");
			String studentid = request.getParameter("studentid");
			
			MyFile file=new MyFile();
			file.setCourseid(courseid);
			file.setStudentid(studentid);
			file.setFiletype(0);
			
			try{
				jsonArray = FileDAO.teacherGetStudentHW(file);
			}catch(SQLException|JSONException e) {
				e.printStackTrace();
			}
			out.println(jsonArray);
		}
		out.close();
	}
}
