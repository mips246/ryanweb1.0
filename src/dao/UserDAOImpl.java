package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.StudentUser;
import vo.TeacherUser;

public class UserDAOImpl implements UserDAO {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	public UserDAOImpl(Connection conn) {
		this.conn=conn;
	}
	public boolean loginCheck(StudentUser studentuser)throws Exception{
		boolean flag=false;
		try {
			String sql="select name from student where userid=? and password=?";
			this.pstmt=this.conn.prepareStatement(sql);
			this.pstmt.setString(1, studentuser.getUserid());//第一个参数是该参数在语句中的索引。参数标记具有从 1 开始的编号。 通常指的是第一个“？”
														//第二个参数是要对第一个参数设置的值。
			this.pstmt.setString(2, studentuser.getPassword());
			ResultSet rs=this.pstmt.executeQuery();//executeQuery方法是数据库查询函数，它会将数据库查询的结果存储在ResultSet对象中
			if(rs.next()) {
				studentuser.setName(rs.getString(1));
				flag=true;
			}
		}catch(Exception e) {
			throw e;
		}finally {
			if(this.pstmt!=null) {
				try {
					this.pstmt.close();
				}catch(Exception e) {
					throw e;
				}
			}
		}
		return flag;
	}
	public boolean loginCheck(TeacherUser teacheruser)throws Exception{
		boolean flag=false;
		//TODO
		return flag;
	}
}
