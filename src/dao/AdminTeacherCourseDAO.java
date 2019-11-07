package dao;

import java.sql.SQLException;

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
}
