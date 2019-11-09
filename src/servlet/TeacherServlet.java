package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dao.TeacherDAO;

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
		out.close();
	}
}
