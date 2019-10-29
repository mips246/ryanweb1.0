package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import vo.TeacherUser;

public class TeacherDAO extends BaseDAO{
	public static TeacherUser login(String name,String pwd) {
		String sql = "SELECT * FROM teacher WHERE teacherid=?;";
		openConnection();
    	
    	TeacherUser u = new TeacherUser();
    	
    	try {
    		pstmt = getPStatement(sql);
    		pstmt.setString(1, name);
    		ResultSet result = pstmt.executeQuery();
    		
    		if(result.next()){
    			String spwd = result.getString("password");
    			u.setPassword(spwd);
    			
    			if(u.isValid(pwd)){
    				u.setName(result.getString("name"));
    				u.setUserid(name);
    				System.out.println("User Login:"+u.getName());
    				//setCacheMap(name, "teacher");
    				
        			return u;
    			}
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}finally{
    		closeConnect();
    	}
    	return u;
	}
	
}
