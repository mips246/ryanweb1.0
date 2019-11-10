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
		String sql="insert into courseselect values(?,?,null);";
		pstmt=getPStatement(sql);
		pstmt.setString(1, cs.getCourseid());
		pstmt.setString(2, cs.getUserid());
		int flag=pstmt.executeUpdate();
		closeConnect();
		if(flag==1) {
			return true;
		}
		else return false;
		
	}
	public static JSONArray getSelectedCourseTable(String userid) throws SQLException, JSONException {
		openConnection();
		String sql = "SELECT * FROM courseselect WHERE studentid=?;";
		//String sql ="select * from courseselect where studentid=?;";
		pstmt=getPStatement(sql);
		//pstmt.setString(1, "courseid");
		//pstmt.setString(2, "grade");
		pstmt.setString(1, userid);
		ResultSet result=pstmt.executeQuery(sql);
		JSONArray selectedcoursetable=new JSONArray();
		JSONObject obj=new JSONObject();
		while(result.next()) {
			obj.append("courseid", result.getString("courseid"));
			obj.append("grade", result.getString("grade"));
			selectedcoursetable.put(obj);
		}
		return selectedcoursetable;
	}
}
