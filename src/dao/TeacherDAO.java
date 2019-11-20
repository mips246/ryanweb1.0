package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vo.TeacherUser;

public class TeacherDAO extends BaseDAO{
	public static TeacherUser login(String userid,String pwd) {
		String sql = "SELECT * FROM teacher WHERE teacherid=?;";
		openConnection();
    	
    	TeacherUser u = new TeacherUser();
    	
    	try {
    		pstmt = getPStatement(sql);
    		pstmt.setString(1, userid);
    		ResultSet result = pstmt.executeQuery();
    		
    		if(result.next()){
    			String spwd = result.getString("password");
    			u.setPassword(spwd);
    			
    			if(u.isValid(pwd)){
    				u.setName(result.getString("teachername"));
    				u.setDescription(result.getString("description"));
    				u.setUserid(userid);
    				System.out.println("User Login:"+u.getName());
    				//setCacheMap(userid, "teacher");
    				
        			return u;
    			}
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}finally{
    		closeConnect();
    	}
    	return null;
	}
	
	public static boolean updatePwd(String tid, String password, String newpwd){
		// 检查密码是否正确
		if (login(tid, password) == null) {
			return false;
		}
		
		// 执行更新密码
		String sql = "UPDATE teacher SET password= ? WHERE teacherid = ?";
		openConnection();
		pstmt = getPStatement(sql);
		
		try {
			pstmt.setString(1, newpwd);
			pstmt.setString(2, tid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
			closeConnect();
		}
		
		return true;
	}
	
	public static JSONArray getCourseSelectAndStudentNameList(String teacherid, String courseid) throws SQLException, JSONException {
		JSONArray resultlist = new JSONArray();
    	String sql = "SELECT courseselect.grade,courseselect.studentid,student.name"
    			+ " FROM courseselect,student"
    			+ " where courseselect.teacherid = ? and courseselect.courseid = ? and courseselect.studentid = student.userid;";
    	openConnection();
		pstmt = getPStatement(sql);
		
		pstmt.setString(1, teacherid);
		pstmt.setString(2, courseid);
		ResultSet result = pstmt.executeQuery();
		while(result.next()){
			JSONObject obj= new JSONObject ();
			obj.put("stuid", result.getString("studentid"));
			obj.put("name", result.getString("name"));
			obj.put("grade", result.getInt("grade"));
			resultlist.put(obj);
		}
		result.close();
		closeConnect();
		return resultlist;
	}
	
	public static boolean updateGrade(String teacherid, String courseid, String studentid, int grade){
		String sql = "UPDATE courseselect SET grade= ? WHERE teacherid = ? and courseid = ? and studentid = ?;";
		openConnection();
		pstmt = getPStatement(sql);
		
		try {
			pstmt.setInt(1, grade);
			pstmt.setString(2, teacherid);
			pstmt.setString(3, courseid);
			pstmt.setString(4, studentid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
			closeConnect();
		}
		
		return true;
	}
	
}
