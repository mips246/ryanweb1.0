package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import vo.CourseSelect;
import vo.StudentUser;

public class StudentDAO extends BaseDAO{
	public StudentDAO() {
	}
	public static class SelectCourseRun implements Runnable{
		private String snumber;
		private String cnumber;
		public static int RET = 0;
		
		public SelectCourseRun(String sno, String cno) {
			this.snumber = sno;  this.cnumber = cno;
		}
		
		public void run() {
			synchronized (this) {
				selectCS(snumber, cnumber);
			}
		}
		
		private void selectCS(String sno, String cno) {
			try {
				pstmt.setString(1, sno);
				pstmt.setString(2, cno);
				pstmt.executeQuery();
				RET = 1;
			} catch (SQLException e) {
				RET = -1;
				StudentDAO.class.notify();
				e.printStackTrace();
			}
			StudentDAO.class.notify();
		}
	}
	
 	public static boolean updatePwd(String snumber, String password, String newpwd){
		snumber = ReformatBlank(snumber);
		password = ReformatBlank(password);
		newpwd = ReformatBlank(newpwd);
		
		String sql = "UPDATE ES_student SET spasswd= ? WHERE sno = ?";
		
		// 检查密码是否正确
		if (login(snumber, password) == null) {
			return false;
		}
		
		// 执行更新密码
		openConnection();
		pstmt = getPStatement(sql);
		
		try {
			pstmt.setString(1, newpwd);
			pstmt.setString(2, snumber);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
			closeConnect();
		}
		
		return true;
	}
 	
 	public static List<CourseSelect> selectedCourseResult(String number) throws SQLException {
 		if (number == null) {
			return null;
		}
 		List<CourseSelect> list = new ArrayList<>();
 		String sql = "SELECT course_cno from coursestudent where student_sno =?;";
 		String sql2 = "SELECT cno,cname,tname from coursestudent,teacher where cno =? and teacher_tno = tno;";
 		
 		openConnection();
 		pstmt = getPStatement(sql);
 		PreparedStatement pstmt2 = getPStatement(sql2);
 		pstmt.setString(1, number);
 		ResultSet resultSet = pstmt.executeQuery();
 		ResultSet result = null;
 		
 		while (resultSet.next()) {
 			pstmt2.setString(1, resultSet.getString("course_cno"));
 			result = pstmt2.executeQuery();
 			if (result.next()) {
 				CourseSelect s = new CourseSelect();
 				s.setCourseid(result.getString("courseid"));
				list.add(s);
			}
		}
 		if (result != null) {
			result.close();
		}
 		resultSet.close();
 		pstmt2.close();
 		closeConnect();
 		
 		return list;
 	}
 	
	public boolean selectCourse(String sno, String cno) {
		openConnection();
		String sql = "INSERT INTO ES_selected values (?, ?, null);";
		pstmt = getPStatement(sql);
		new Thread(new SelectCourseRun(sno, cno), "select-course-thread").start();
		try {
			StudentDAO.class.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		closeConnect();
		return SelectCourseRun.RET > 0;
	}
	
    public static StudentUser login(String id, String pwd){
    	//SQL model
    	String sql = "SELECT * FROM student WHERE userid=?;";
    	openConnection();
    	StudentUser u = new StudentUser();
    	
    	try {
    		pstmt = getPStatement(sql);
    		pstmt.setString(1, id);
    		ResultSet result = pstmt.executeQuery();
    		//显然，分别从数据库和前端取出password然后调用student类的invalid判断密码是否通过
    		if(result.next()){
    			String spwd = result.getString("password");
    			u.setPassword(spwd);
    			u.setName(result.getString("name"));
    			if(u.isValid(pwd)){
    				u.setUserid(id);
    				System.out.println("User Login:"+u.getName());
    				//setCacheMap(name, "student");
        			return u;
    			}
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}finally{
    		closeConnect();
    	}
    	return null;
	}
    
    public static ArrayList<JSONObject> getGrades(String snumber) {
    	System.out.println("< GET grades >");
       	String sql="SELECT * from ES_selected,ES_course,ES_teacher  "
    			+ "WHERE ES_selected.student_sno=? and ES_course.cno=ES_selected.course_cno and ES_course.teacher_tno=ES_teacher.tno;";
		
		openConnection();
		pstmt = getPStatement(sql);
		ArrayList<JSONObject> map = new ArrayList<>();
		try {
			pstmt.setString(1, snumber);
			ResultSet result =pstmt.executeQuery();
			while(result.next()) {
				JSONObject o = new JSONObject();
				try { 
					o.put("student_sno", snumber);
					o.put("cname", result.getString("cname"));
					o.put("tname", result.getString("tname"));
					o.put("score", result.getString("score"));
					System.out.println(o.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				map.add(o);
			}
			return map;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect();
		}
		System.out.println("< Get Grade failed >");
		return null;
	}
    
    public static ArrayList<JSONObject> LessonResult(String snumber) {
    	System.out.println("< view selected course >");
    	String sql="SELECT * from ES_selected,ES_course,ES_teacher  "
    			+ "WHERE ES_selected.student_sno=? and ES_course.cno=ES_selected.course_cno and ES_course.teacher_tno=ES_teacher.tno;";
		
		openConnection();
		pstmt = getPStatement(sql);
		ArrayList<JSONObject> map = new ArrayList<>();
		try {
			pstmt.setString(1, snumber);
			ResultSet result =pstmt.executeQuery();
			while(result.next()) {
				JSONObject o = new JSONObject();
				try { 
					o.put("cname", result.getString("cname"));
					o.put("tname", result.getString("tname"));
					o.put("limit_num", result.getInt("limit_num"));
					o.put("selected_num", result.getInt("selected_num"));
					o.put("semester", result.getInt("semester"));
					System.out.println(o.toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				map.add(o);
			}
			return map;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnect();
		}
		System.out.println("< selected course failed >");
		return null;
	}
}
