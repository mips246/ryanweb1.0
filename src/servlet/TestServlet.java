package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection conn = null;
    protected static  PreparedStatement pstmt = null;
    
    public static void openConnection() {
    	String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/EducationSystem";
		String username = "root";
		String password = "0623";
		try{
		    Class.forName(driver);
		    conn = DriverManager.getConnection(url,username,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Connect Database Success.");
		return ;
	}
    
    public static PreparedStatement getPStatement(String sql) {
    	try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
    
    public static void closeConnect() {
    	try {
    		if (conn != null) {
    			conn.close();
    			conn = null;
			}
    		if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
		} catch (SQLException e) {
			System.out.println("Close Connect Failed.");
			e.printStackTrace();
		}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String message = request.getParameter("message");
		System.out.println(message);
		/*
		String sql = "SELECT * FROM test;";
		openConnection();
		ResultSet result = null;
		pstmt = getPStatement(sql);
		try {
			result= pstmt.executeQuery();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		JSONArray info = new JSONArray();
		try {
			while(result.next()) {
				JSONObject obj= new JSONObject ();
				obj.append("sname", result.getString("name"));
				obj.append("sno", result.getString("no"));
				obj.append("sage", result.getString("age"));
				info.put(obj);
			}
			out.println(info);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		closeConnect();
		*/
		
		String name = "RenChengKun";
		String no = "1930779";
		String age = "22";
		
		PrintWriter out = response.getWriter();
		JSONArray info = new JSONArray();
		try {
			for(int i=0;i<2;i++) {
				JSONObject obj= new JSONObject ();
				obj.append("sname", name);
				obj.append("sno", no);
				obj.append("sage", age);
				info.put(obj);
			}
			
			out.println(info);
			
			System.out.println("Enter the Servlet!");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

}
