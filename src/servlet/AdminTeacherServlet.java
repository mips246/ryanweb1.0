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

import vo.TeacherUser;
import dao.AdminDAO;

public class AdminTeacherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public AdminTeacherServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void init() throws ServletException {
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
		String method = request.getParameter("method");
		
		if(method.equals("deleteTeacher")) {
			System.out.println("< Addmin Delete Teacher >");
		}
		
		else if(method.equals("insertTeacher")) {
			System.out.println("< Addmin Insert Teacher >");
			
			TeacherUser u = new TeacherUser();
			String teacherid = request.getParameter("teacherid");
			String teachername = request.getParameter("teachername");
			String password = request.getParameter("password");
			String description = request.getParameter("description");
			u.setUserid(teacherid);
			u.setName(teachername);
			u.setPassword(password);
			u.setDescription(description);
			
			if(AdminDAO.insert(u)) {
				System.out.println("< Addmin Insert Teacher Successful >");
			}
			else {
				System.out.println("< Addmin Insert Teacher Failed >");
			}
        	
		}
		
		else if(method.equals("upgradeTeacher")) {
			System.out.println("< Addmin Upgrade Teacher >");
		}
		
		else if(method.equals("selectAllTeacher")) {
			System.out.println("< Addmin Select All Teacher >");
			
			try {
        		jsonArray = AdminDAO.getTeacherList();
    		} catch (SQLException | JSONException e) {
    			e.printStackTrace();
    		}
        	out.println(jsonArray);
		}
		
		out.close();
	}

	

}
