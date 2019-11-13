package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vo.CourseSelect;

public class StudentCourseDAO extends BaseDAO{
	public StudentCourseDAO() {}
	public static boolean insertCourse(CourseSelect cs) throws SQLException {
		openConnection();
		String sql="insert into courseselect values(?,?,null,?);";
		pstmt=getPStatement(sql);
		pstmt.setString(1, cs.getCourseid());
		pstmt.setString(2, cs.getUserid());
		pstmt.setString(3, cs.getTeacherid());
		int flag=pstmt.executeUpdate();
		closeConnect();
		if(flag==1) {
			return true;
		}
		else return false;
		
	}
	public static JSONArray getSelectedCourseTable(String userid) throws SQLException, JSONException {
		openConnection();
		//String sql = "SELECT * FROM courseselect;";
		String sql ="select * from courseselect where studentid = ?;";
		pstmt=getPStatement(sql);
		//pstmt.setString(1, "courseid");
		//pstmt.setString(2, "grade");
		pstmt.setString(1, userid);
		ResultSet result=pstmt.executeQuery();
		JSONArray selectedcoursetable=new JSONArray();
		JSONObject obj=new JSONObject();
		while(result.next()) {
			if(result.getString("studentid").equals(userid)) {
				obj.append("studentid", result.getString("studentid"));
				obj.append("courseid", result.getString("courseid"));
				obj.append("grade", result.getString("grade"));
				obj.append("teacherid",result.getString("teacherid"));
				selectedcoursetable.put(obj);
			}
		}
		return selectedcoursetable;
	}
}
