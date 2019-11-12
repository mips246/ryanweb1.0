package vo;

public class Admin {
	private String userid;
	private String name;
	private String password;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isValid(String pwd) {
		if(pwd.equals(password)) {
			return true;
		}
		return false;
	}
	
	
}
