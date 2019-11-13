package dao;

import java.sql.SQLException;

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
			
			System.out.println("< Writr Into DataBase >");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				closeConnect();
		}
	}

}
