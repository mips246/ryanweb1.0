package dao;

import java.sql.SQLException;

import common.Info;
import vo.Course;
import vo.StudentUser;
import vo.TeacherUser;

public class AdminDAO extends BaseDAO{
	private static final String TAG = AdminDAO.class.getSimpleName();
	public static boolean insert(StudentUser u) {
		if (u.getUserid()==null) {
			return false;
		}
		openConnection();
		String sql = "INSERT INTO student values(?,?,?);";
		pstmt = getPStatement(sql);
		String pwd = u.getPassword();
		if (pwd.isEmpty()) {
			pwd = Info.DefaultPassword;
		}
		try {
			pstmt.setString(1, u.getUserid());
			pstmt.setString(2, u.getName());
			pstmt.setString(3, u.getPassword());
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
	public static boolean insert(TeacherUser u) {
		if(u.getUserid()==null) {
			return false;
		}
		openConnection();
		String sql = "INSERT INTO teacher values(?, ?, ?); ";
		pstmt = getPStatement(sql);
		String pwd = u.getPassword();
		if (pwd.isEmpty()) {
			pwd = Info.DefaultPassword;
		}
        try {
        	pstmt.setString(1,u.getUserid());  
			pstmt.setString(2,u.getName());
			pstmt.setString(3, pwd);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnect();
		}
		return true;
	}
	public static boolean insert(Course c) {
		if(c.getCourseid()==null) {
			return false;
		}
		openConnection();
		String sql = "INSERT INTO course values(?, ?, ?, ?);";
		pstmt = getPStatement(sql);
		try {
	        	pstmt.setString(1,c.getCourseid());
				pstmt.setString(2,c.getCoursename());
				pstmt.setInt(3, c.getStudentcount());
				pstmt.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			closeConnect();
		}
		return true;
	}
	public static boolean delete(StudentUser u) {
		if (u == null) {
			return false;
		}
    	openConnection();
    	String sql = "DELETE FROM student where  userid = ?;";
    	pstmt = getPStatement(sql);
    	
    	try {
			pstmt.setString(1, u.getUserid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect();
		}
    	
    	return true;
	}
	public static boolean delete(TeacherUser u) {
    	if (u == null) {
			return false;
		}
    	openConnection();
    	String sql = "DELETE FROM ES_teacher where tno = ?;";
    	pstmt = getPStatement(sql);
    	
    	try {
			pstmt.setString(1, u.getUserid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect();
		}
    	
    	return true;
	}
	public static boolean delete(Course c) {
    	if (c == null) {
			return false;
		}
    	openConnection();
    	String sql = "DELETE FROM course where courseid = ?;";
    	pstmt = getPStatement(sql);
    	
    	try {
			pstmt.setString(1, c.getCourseid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect();
		}
    	
    	return true;
	}
	public static boolean updata(StudentUser u) {
    	String sql= "UPDATE student SET username =?,password=? WHERE userid = ?;";
    	
    	openConnection();
    	pstmt = getPStatement(sql);
    	
    	try {
	    		pstmt.setString(1, u.getName());
	    		pstmt.setString(2, u.getPassword());
	    		pstmt.setString(3, u.getUserid());
    			int ret = pstmt.executeUpdate(); 
    			if(ret == 1) {
    				System.out.println("< 更新成功 >");
    				return true;  
    			}
    			 		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}finally{
    		closeConnect();
    	}
    	System.out.println("< 更新失败 >");
    	return false;
    }
	public static boolean updata(TeacherUser u) {
    	
    	String sql= "UPDATE ES_teacher set name=?,password=? WHERE teacherid=?; ";
    	openConnection();
    	pstmt = getPStatement(sql);
    	
    	try {
    			pstmt.setString(3, u.getUserid());
    			pstmt.setString(1,u.getName());
    			pstmt.setString(2,u.getPassword());
    			pstmt.executeUpdate();    		
    			System.out.println("< 更新成功 >");
    			return true;   		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}finally{
    		closeConnect();
    	}
    	System.out.println("< 更新失败 >");
    	return false;
    }
	public static boolean updata(Course u) {
    	
    	String sql= "UPDATE ES_course set coursename=? studentcount=? createtime=? WHERE courseid=?; ";
    	openConnection();
    	pstmt = getPStatement(sql);
    	
    	try {
    			pstmt.setString(1,u.getCoursename());
    			pstmt.setInt(2,u.getStudentcount());
    			pstmt.setString(3,u.getCreatetime());
    			pstmt.setString(4, u.getCourseid());
    			pstmt.executeUpdate();    		
    			System.out.println("< 更新成功 >");
    			return true;
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}finally{
    		closeConnect();
    	}
    	System.out.println("< 更新失败 >");
    	return false;
    }
	
}
