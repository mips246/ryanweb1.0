package common;

public class DBUtil {
    private DBUtil() {
	}
    
    public static String ReformatBlank(String str) {
		if (str == null) {
			return null;
		}
		if (str.getBytes()[0] == ' ') {
			return str.substring(1);
		}else {
			return str;
		}
	}
}
