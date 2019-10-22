package vo;

import java.util.Date;
import java.util.List;

public class Course {
	private String coursename;
	private String coursenumber;
	private int studentcount;
	private Date createtime;
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public int getStudentcount() {
		return studentcount;
	}
	public void setStudentcount(int studentcount) {
		this.studentcount = studentcount;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getCoursenumber() {
		return coursenumber;
	}
	public void setCoursenumber(String coursenumber) {
		this.coursenumber = coursenumber;
	}
	
}
