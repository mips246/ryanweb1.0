package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vo.MyFile;

public class FileDAO extends BaseDAO{
	
	public static void insert(MyFile myfile) {
		openConnection();
		String sql = "INSERT INTO file(file_url,studentid,courseid,teacherid,file_type,course_section,create_time,file_name) values(?,?,?,?,?,?,?,?);";
		pstmt = getPStatement(sql);
		try {
			pstmt.setString(1, myfile.getFileurl());
			pstmt.setString(2, myfile.getStudentid());
			pstmt.setString(3, myfile.getCourseid());
			pstmt.setString(4, myfile.getTeacherid());
			pstmt.setInt(5, myfile.getFiletype());
			pstmt.setInt(6, myfile.getCoursesection());
			pstmt.setString(7, myfile.getCreatetime());
			pstmt.setString(8, myfile.getFilename());
			pstmt.executeUpdate();
			
			System.out.println("< Write Into DataBase >");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				closeConnect();
		}
	}
	public static JSONArray getCourseArchiveTable(MyFile file) throws SQLException, JSONException {
		openConnection();
		String courseid=file.getCourseid();
		String teacherid=file.getTeacherid();
		String sql="select * from file where courseid = ? and teacherid = ? and studentid = ? and filetype = ?;";
		pstmt=getPStatement(sql);
		pstmt.setString(1, courseid);
		pstmt.setString(2, teacherid);
		pstmt.setString(3, null);
		pstmt.setInt(4, file.getFiletype());
		ResultSet result=pstmt.executeQuery();
		JSONArray coursearchivetable =new JSONArray();
		JSONObject obj =new JSONObject();
		while(result.next()) {
			 obj.append("fileurl", result.getString("fileurl"));
			 obj.append("filename", result.getString("filename"));
			 obj.append("teacherid", result.getString("teacherid"));
			 coursearchivetable.put(obj);
		}
		return coursearchivetable;
	}
}
