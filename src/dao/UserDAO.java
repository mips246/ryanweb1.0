package dao;

import vo.StudentUser;
import vo.TeacherUser;

public interface UserDAO {
	//user类本可以直接继承的，但为了逻辑简单写成了两个类，在UserDAO中设置两个接口。
	public boolean loginCheck(StudentUser studentuser)throws Exception;
	public boolean loginCheck(TeacherUser teacheruser)throws Exception;
}
