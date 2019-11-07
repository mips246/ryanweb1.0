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
import vo.Course;

public class AdminCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
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
		JSONArray courseList=new JSONArray();
		String courseid=req.getParameter("courseid");
		String coursename=req.getParameter("coursename");
		
		//int sc=10;
		
		//System.out.println("新添课程名称是什么？:"+courseid);
		//System.out.println(studentcount);
		//int sc=(int)Integer.parseInt(studentcount);
		//System.out.println("deletecourse: "+courseid);

//--------------------------------------------------测试用数据代码
//		JSONObject obj=new JSONObject();
//		try {
//			System.out.println("进入TRY");
//			obj.append("courseid", "123");
//			obj.append("coursename", "计算机组成原理");
//			obj.append("studentcount", "50");
//			obj.append("createtime", "20191001");
//			courseList.put(obj);
//			out.println(courseList);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//---------------------------------------------------
		if("loadCourse".equals(method)) {
			System.out.println("load course中");
			try{
				courseList=AdminDAO.getCourseList();
			}catch(SQLException|JSONException e) {
				e.printStackTrace();
			}
			out.println(courseList);
		}
		else if("deleteCourse".equals(method)) {
			Course c=new Course();
			c.setCourseid(courseid);
			AdminDAO.delete(c);
			System.out.println("成功删除");
			JSONObject obj=new JSONObject();
			JSONArray deletecourse=new JSONArray();
			try {
				obj.append("courseid", c.getCourseid());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			deletecourse.put(obj);
			out.println(deletecourse);
		}
		else if("addCourse".equals(method)) {
			String studentcount=req.getParameter("studentcount");
			System.out.println("学生人数是："+studentcount);
			int sc= Integer.parseInt(studentcount);
			String createtime=req.getParameter("createtime");
			Course course=new Course();
			course.setCourseid(courseid);
			course.setCoursename(coursename);
			course.setStudentcount(sc);
			course.setCreatetime(createtime);
			AdminDAO.insert(course);
			JSONObject obj=new JSONObject();
			JSONArray addcourse=new JSONArray();
			try {
				obj.append("courseid", course.getCourseid());
				System.out.println("成功添加");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addcourse.put(obj);
			out.println(addcourse);
		}
		
	}
}
