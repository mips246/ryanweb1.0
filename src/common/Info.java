package common;

import org.json.JSONException;
import org.json.JSONObject;

public class Info {
	public Info() {
	}
	
	public static final String ERROR = "-1";
	public static final String SUCCESS = "1";
	public static final String DefaultPassword = "000001";
	
	public static JSONObject getERRJSON() {
        JSONObject object = new JSONObject();
        try {
			object.put("errorCode", ERROR);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object;
	}
}
