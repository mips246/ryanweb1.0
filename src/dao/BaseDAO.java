package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import common.DBUtil;

public class BaseDAO {
    public BaseDAO(){}
    
    private static Connection conn = null;
    protected static  PreparedStatement pstmt = null;
    
    public static Connection getConnect() {
    	return conn;
    }
    
    public static PreparedStatement getPStatement(String sql) {
    	try {
			return conn.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("conn出错");
			e.printStackTrace();
		}
		return null;
	}
    
    public static void openConnection() {
    	Properties prop = new Properties();
		System.out.println("打开数据库连接");
		String driver = null;
		String url = null;
		String username = null;
		String password = null;
		try{
			prop.load(DBUtil.class.getClassLoader().getResourceAsStream("DBConfig.properties"));
			
		    driver = prop.getProperty("driver");
		    url = prop.getProperty("url");
		    username = prop.getProperty("username");
		    password = prop.getProperty("password");
		    //System.out.println(driver+" "+url);
		    Class.forName(driver);
		    System.out.println("Connect Database Success.");
		    conn = DriverManager.getConnection(url,username,password);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("database connection failed");
		}
		return ;
	}
    
    public static void closeConnect() {
    	System.out.println("关闭数据库连接");
    	try {
    		if (conn != null) {
    			conn.close();
    			conn = null;
			}
    		if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
		} catch (SQLException e) {
			System.out.println("Close Connect Failed.");
			e.printStackTrace();
		}
	}
    
//    protected static void setCacheMap(String index, String o) {
//    	Date time = new Date();
//		CacheMgr.getInstance().addCache(index, o, CacheMgr.getGeneralModel(time.getTime()));
//	}
//    
//    public static void removeCache(String number) {
//		CacheMgr.getInstance().removeCache(number);
//	}
//    
//    public static String getCacheFlag(String number) {
//    	String o = CacheMgr.getInstance().getValue(number);
//		return o;
//	}
    
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
