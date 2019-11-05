package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import common.Info;
import vo.Admin;
import vo.Course;
import vo.StudentUser;
import vo.TeacherUser;

public class AdminDAO extends BaseDAO{
	private static final String TAG = AdminDAO.class.getSimpleName();
	
	public static boolean insert(StudentUser u) {
		if (u.getUserid()==null) {
			return false;
		}
		openConnection();
		String sql = "INSERT INTO student values(?,?,?);";
		pstmt = getPStatement(sql);
		String pwd = u.getPassword();
		if (pwd.isEmpty()) {
			pwd = Info.DefaultPassword;
		}
		try {
			pstmt.setString(1, u.getUserid());
			pstmt.setString(2, u.getName());
			pstmt.setString(3, u.getPassword());
			int ret = pstmt.executeUpdate();
			if (ret == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				closeConnect();
		}
		return false;
	}
	public static boolean insert(TeacherUser u) {
		if(u.getUserid()==null) {
			return false;
		}
		openConnection();
		String sql = "INSERT INTO teacher values(?, ?, ?, ?); ";
		pstmt = getPStatement(sql);
		String pwd = u.getPassword();
		if (pwd.isEmpty()) {
			pwd = Info.DefaultPassword;
		}
        try {
        	pstmt.setString(1,u.getUserid());  
			pstmt.setString(2,u.getName());
			pstmt.setString(3, pwd);
			pstmt.setString(4, u.getDescription());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnect();
		}
		return true;
	}
	public static boolean insert(Course c) {
		if(c.getCourseid()==null) {
			return false;
		}
		openConnection();
		String sql = "INSERT INTO course values(?, ?, ?, ?);";
		pstmt = getPStatement(sql);
		try {
	        	pstmt.setString(1,c.getCourseid());
				pstmt.setString(2,c.getCoursename());
				pstmt.setInt(3, c.getStudentcount());
				pstmt.setString(4,c.getCreatetime());
				pstmt.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			closeConnect();
		}
		return true;
	}
	public static boolean delete(StudentUser u) {
		if (u == null) {
			return false;
		}
    	openConnection();
    	String sql = "DELETE FROM student where  userid = ?;";
    	pstmt = getPStatement(sql);
    	
    	try {
			pstmt.setString(1, u.getUserid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect();
		}
    	
    	return true;
	}
	public static boolean delete(TeacherUser u) {
    	if (u == null) {
			return false;
		}
    	openConnection();
    	String sql = "DELETE FROM ES_teacher where tno = ?;";
    	pstmt = getPStatement(sql);
    	
    	try {
			pstmt.setString(1, u.getUserid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect();
		}
    	
    	return true;
	}
	public static boolean delete(Course c) {
    	if (c == null) {
			return false;
		}
    	openConnection();
    	String sql = "DELETE FROM course where courseid = ?;";
    	pstmt = getPStatement(sql);
    	
    	try {
			pstmt.setString(1, c.getCourseid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect();
		}
    	
    	return true;
	}
	public static boolean updata(StudentUser u) {
    	String sql= "UPDATE student SET username =?,password=? WHERE userid = ?;";
    	
    	openConnection();
    	pstmt = getPStatement(sql);
    	
    	try {
	    		pstmt.setString(1, u.getName());
	    		pstmt.setString(2, u.getPassword());
	    		pstmt.setString(3, u.getUserid());
    			int ret = pstmt.executeUpdate(); 
    			if(ret == 1) {
    				System.out.println("< ���³ɹ� >");
    				return true;  
    			}
    			 		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}finally{
    		closeConnect();
    	}
    	System.out.println("< ����ʧ�� >");
    	return false;
    }
	public static boolean updata(TeacherUser u) {
    	
    	String sql= "UPDATE ES_teacher set name=?,password=? WHERE teacherid=?; ";
    	openConnection();
    	pstmt = getPStatement(sql);
    	
    	try {
    			pstmt.setString(3, u.getUserid());
    			pstmt.setString(1,u.getName());
    			pstmt.setString(2,u.getPassword());
    			pstmt.executeUpdate();    		
    			System.out.println("< ���³ɹ� >");
    			return true;   		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}finally{
    		closeConnect();
    	}
    	System.out.println("< ����ʧ�� >");
    	return false;
    }
	public static boolean updata(Course u) {
    	
    	String sql= "UPDATE ES_course set coursename=? studentcount=? createtime=? WHERE courseid=?; ";
    	openConnection();
    	pstmt = getPStatement(sql);
    	
    	try {
    			pstmt.setString(1,u.getCoursename());
    			pstmt.setInt(2,u.getStudentcount());
    			pstmt.setString(3,u.getCreatetime());
    			pstmt.setString(4, u.getCourseid());
    			pstmt.executeUpdate();    		
    			System.out.println("< ���³ɹ� >");
    			return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}finally{
    		closeConnect();
    	}
    	System.out.println("< ����ʧ�� >");
    	return false;
    }
	
	public static JSONArray getTeacherList() throws SQLException, JSONException {
		JSONArray teacherlist = new JSONArray();
    	String sql = "SELECT * FROM teacher;";
    	openConnection();
		pstmt = getPStatement(sql);
		ResultSet result = pstmt.executeQuery();
		while(result.next()){
			JSONObject obj= new JSONObject ();
			obj.put("teacherid", result.getString("teacherid"));
			obj.put("teachername", result.getString("teachername"));
			obj.put("password", result.getString("password"));
			obj.put("description", result.getString("description"));
			teacherlist.put(obj);
		}
		result.close();
		closeConnect();
		return teacherlist;
	}
	
	public static Admin login(String id, String pwd){
    	String sql = "SELECT * FROM admin WHERE userid=?;";
    	openConnection();
    	Admin u = new Admin();
    	
    	try {
    		pstmt = getPStatement(sql);
    		pstmt.setString(1, id);
    		ResultSet result = pstmt.executeQuery();
    		if(result.next()){
    			String spwd = result.getString("password");
    			u.setPassword(spwd);
    			u.setName(result.getString("username"));
    			if(u.isValid(pwd)){
    				u.setUserid(id);
    				System.out.println("User Login:"+u.getName());
    				//setCacheMap(name, "student");
        			return u;
    			}
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}finally{
    		closeConnect();
    	}
    	return u;
	}
	public static JSONArray getCourseList() throws SQLException, JSONException {
		JSONArray courseList=new JSONArray();
		String sql="select * from course;";
		openConnection();
		pstmt=getPStatement(sql);
		ResultSet result=pstmt.executeQuery();
		while(result.next()) {
			JSONObject obj=new JSONObject();
			obj.append("courseid", result.getString("courseid"));
			obj.append("coursename", result.getString("coursename"));
			obj.append("studentcount", result.getInt("studentcount"));
			obj.append("createtime", result.getString("createtime"));
			courseList.put(obj);
		}
		return courseList;
	}
	
}
