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
		String sql="select * from file where courseid = ? and teacherid = ? and studentid is null and file_type = ?;";
		pstmt=getPStatement(sql);
		pstmt.setString(1, courseid);
		pstmt.setString(2, teacherid);
		//pstmt.setString(3, null);
		pstmt.setInt(3, file.getFiletype());
		ResultSet result=pstmt.executeQuery();
		JSONArray coursearchivetable =new JSONArray();
		while(result.next()) {
			 JSONObject obj =new JSONObject();
			 obj.append("fileurl", result.getString("file_url"));
			 obj.append("filename", result.getString("file_name"));
			 obj.append("teacherid", result.getString("teacherid"));
			 obj.append("section", result.getInt("course_section"));
			 obj.append("courseid",result.getString("courseid"));
			 System.out.println(result.getString("file_url"));
			 coursearchivetable.put(obj);
		}
		
		return coursearchivetable;
	}
	
	public static JSONArray teacherGetStudentHW(MyFile file) throws SQLException, JSONException {
		openConnection();

		String sql="select * from file where courseid = ? and studentid = ? and file_type = ? and teacherid is null;";
		pstmt=getPStatement(sql);
		pstmt.setString(1, file.getCourseid());
		pstmt.setString(2, file.getStudentid());
		pstmt.setInt(3, file.getFiletype());
		ResultSet result=pstmt.executeQuery();
		
		JSONArray resultlist =new JSONArray();
		while(result.next()) {
			 JSONObject obj =new JSONObject();
			 
			 obj.append("fileno", 			result.getInt("file_no"));
			 obj.append("fileurl", 			result.getString("file_url"));
			 obj.append("filename", 		result.getString("file_name"));
			 obj.append("coursesection", 	result.getInt("course_section"));
			 obj.append("grade", 			result.getInt("grade"));
			 obj.append("createtime", 		result.getString("create_time"));
			 
			 System.out.println("< Get "+ result.getString("file_url") + " >");
			 resultlist.put(obj);
		}
		closeConnect();
		return resultlist;
	}
	
	public static boolean updateHWGrade(int fileno, int grade){

		String sql = "UPDATE file SET grade= ? WHERE file_no = ?;";
		openConnection();
		pstmt = getPStatement(sql);
		
		try {
			pstmt.setInt(1, grade);
			pstmt.setInt(2, fileno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
			closeConnect();
		}
		
		return true;
	}
	
	/*
	public static boolean updateHWGrade(String filename, String courseid, String studentid, int grade){
		//System.out.println("< Grade: "+ fileurl + " >");
		//因为file_url中含有“\”，SQL语句无法处理，需要将“\”转义为“\\”，不好处理。
		//String sql = "UPDATE file SET grade= ? WHERE file_url = ?;";
		String sql = "UPDATE file SET grade= ? WHERE file_name = ? and studentid = ? and courseid = ? and file_type = 0;";
		openConnection();
		pstmt = getPStatement(sql);
		
		try {
			pstmt.setInt(1, grade);
			pstmt.setString(2, filename);
			pstmt.setString(3, studentid);
			pstmt.setString(4, courseid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
			closeConnect();
		}
		
		return true;
	}
	*/
	
}
