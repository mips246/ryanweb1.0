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
	public static JSONArray getSelectedCourseList() throws SQLException, JSONException {
		openConnection();
		String sql1="select * from courseteacher;";
		//String sql2="select * from course;";
		pstmt=getPStatement(sql1);
		ResultSet result1=pstmt.executeQuery(sql1);
		JSONArray selectedCourseList =new JSONArray();
		JSONObject obj1=new JSONObject();
		while(result1.next()) {
			obj1.append("courseid", result1.getString("courseid"));
			obj1.append("coursename", result1.getString("coursename"));
			obj1.append("teacherid", result1.getString("teacherid"));
			obj1.append("teachername", result1.getString("teachername"));
			selectedCourseList.put(obj1);
		}
		return selectedCourseList;
		
	}
}
