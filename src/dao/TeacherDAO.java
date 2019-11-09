package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import vo.TeacherUser;

public class TeacherDAO extends BaseDAO{
	public static TeacherUser login(String userid,String pwd) {
		String sql = "SELECT * FROM teacher WHERE teacherid=?;";
		openConnection();
    	
    	TeacherUser u = new TeacherUser();
    	
    	try {
    		pstmt = getPStatement(sql);
    		pstmt.setString(1, userid);
    		ResultSet result = pstmt.executeQuery();
    		
    		if(result.next()){
    			String spwd = result.getString("password");
    			u.setPassword(spwd);
    			
    			if(u.isValid(pwd)){
    				u.setName(result.getString("teachername"));
    				u.setDescription(result.getString("description"));
    				u.setUserid(userid);
    				System.out.println("User Login:"+u.getName());
    				//setCacheMap(userid, "teacher");
    				
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
	
	public static boolean updatePwd(String tid, String password, String newpwd){
		// 检查密码是否正确
		if (login(tid, password) == null) {
			return false;
		}
		
		// 执行更新密码
		String sql = "UPDATE teacher SET password= ? WHERE teacherid = ?";
		openConnection();
		pstmt = getPStatement(sql);
		
		try {
			pstmt.setString(1, newpwd);
			pstmt.setString(2, tid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
			closeConnect();
		}
		
		return true;
	}
	
}
