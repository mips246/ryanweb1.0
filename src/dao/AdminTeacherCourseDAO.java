package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vo.CourseTeacher;


public class AdminTeacherCourseDAO extends BaseDAO{
	public static boolean insert(CourseTeacher ct) {
		if (ct.getCourseid()==null || ct.getTeacherid()==null) {
			return false;
		}
		openConnection();
		String sql = "INSERT INTO courseteacher values(?,?,?,?);";
		pstmt = getPStatement(sql);
		
		try {
			pstmt.setString(1, ct.getCourseid());
			pstmt.setString(2, ct.getCoursename());
			pstmt.setString(3, ct.getTeacherid());
			pstmt.setString(4, ct.getTeachername());
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
	public static JSONArray getTeacherSelectedCourseList() throws SQLException, JSONException {
		openConnection();
		//String sql1="select courseselect.*,courseteacher.* from courseselect,courseteacher where courseselect.courseid=courseteacher.courseid and courseselect.teacherid=courseteacher.teacherid and courseselect.studentid=?;";
		//String sql2="select * from course;";
		String sql="select * from courseteacher;";
		pstmt=getPStatement(sql);
		//pstmt.setString(1, userid);
		ResultSet result1=pstmt.executeQuery(sql);
		JSONArray selectedCourseList =new JSONArray();
		
		while(result1.next()) {
			JSONObject obj1=new JSONObject();
			obj1.append("courseid", result1.getString("courseid"));
			obj1.append("coursename", result1.getString("coursename"));
			obj1.append("teacherid", result1.getString("teacherid"));
			obj1.append("teachername", result1.getString("teachername"));
			selectedCourseList.put(obj1);
		}
		closeConnect();
		return selectedCourseList;
		
	}
	
	//查询指定id的老师的课
	public static JSONArray getTheTeacherCourseList(String teacherid) throws SQLException, JSONException {
		JSONArray teachercourselist = new JSONArray();
    	String sql = "SELECT * FROM courseteacher where teacherid = ?;";
    	openConnection();
		pstmt = getPStatement(sql);
		
		pstmt.setString(1, teacherid);
		ResultSet result = pstmt.executeQuery();
		while(result.next()){
			JSONObject obj= new JSONObject ();
			obj.put("courseid", result.getString("courseid"));
			obj.put("coursename", result.getString("coursename"));
			teachercourselist.put(obj);
		}
		result.close();
		closeConnect();
		return teachercourselist;
	}
}
