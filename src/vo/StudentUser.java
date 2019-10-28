package vo;

import org.json.JSONException;
import org.json.JSONObject;

public class StudentUser {
	private String userid;
	private String name;
	private String password;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isValid(String pwd){
		if(pwd.equals(password)){
			return true;
		}
		else return false;
	}
	public JSONObject toJsonObject() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("u_number", userid);
		obj.put("u_name", name);
		return obj;
	}
}
