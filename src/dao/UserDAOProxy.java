package dao;

import dbc.DatabaseConnection;
import vo.StudentUser;
import vo.TeacherUser;

public class UserDAOProxy implements UserDAO {
	private DatabaseConnection dbc=null;
	private UserDAO dao=null;
	public UserDAOProxy() {
		try {
			this.dbc=new DatabaseConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.dao=new UserDAOImpl(this.dbc.getConnection());
	}
	@Override
	public boolean loginCheck(StudentUser studentuser) throws Exception {
		// TODO Auto-generated method stub
		boolean flag=false;
		try {
			flag=this.dao.loginCheck(studentuser);
		}catch(Exception e) {
			throw e;
		}finally {
			this.dbc.close();
		}
		return flag;
	}
	public boolean loginCheck(TeacherUser teacheruser)throws Exception{
		//TODO
		boolean flag=false;
		return flag;
	}

}
