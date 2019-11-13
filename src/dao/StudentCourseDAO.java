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
		
		while(result.next()) {
			if(result.getString("studentid").equals(userid)) {
				JSONObject obj=new JSONObject();
				obj.append("studentid", result.getString("studentid"));
				obj.append("courseid", result.getString("courseid"));
				obj.append("grade", result.getString("grade"));
				obj.append("teacherid",result.getString("teacherid"));
				selectedcoursetable.put(obj);
			}
		}
		return selectedcoursetable;
	}
	public static JSONArray getTable(String userid) throws SQLException, JSONException {
		openConnection();
		String sql="select courseselect.*,courseteacher.* from courseselect,courseteacher where courseselect.courseid=courseteacher.courseid and courseselect.teacherid=courseteacher.teacherid and courseselect.studentid=?;";
		pstmt=getPStatement(sql);
		pstmt.setString(1, userid);
		ResultSet result=pstmt.executeQuery();
		JSONArray table=new JSONArray();
		while(result.next()) {
			JSONObject obj1=new JSONObject();
			obj1.append("courseid", result.getString("courseid"));
			obj1.append("coursename", result.getString("coursename"));
			obj1.append("teacherid", result.getString("teacherid"));
			obj1.append("teachername", result.getString("teachername"));
			table.put(obj1);
		}
		closeConnect();
		return table;
		
	}
}
