package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库基类
 * @author Song
 *
 */
public class BaseDao {

	private String url="jdbc:mysql://localhost:3306/blogdb";
	private String driver="com.mysql.jdbc.Driver";
	private String name="root";//账号
	private String pass="";//密码
	Connection conn;//数据库连接对象
	PreparedStatement ps;//执行命令（SQL）对象
	ResultSet rs;//储存返回结果
	
	public Connection getConn() throws SQLException {
		//加载驱动
		try {
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url,name,pass);
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public BaseDao() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws SQLException {
		BaseDao bd=new BaseDao();
		bd.getConn();
	}
	
	
}
