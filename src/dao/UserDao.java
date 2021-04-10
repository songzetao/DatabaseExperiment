package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.util.UserInfo;

public class UserDao extends BaseDao{
 /**
 * 用户登录
 * @author Song
 * @param userno 账号
 * @param password 密码
 * @return true:成功，false：失败
 */
	public UserDao() {
		// TODO Auto-generated constructor stub
	}

	public boolean login(String userno, String password) {
		// TODO Auto-generated method stub
		//JDBC(Java DataBase Connection)连接数据库
		//1、获得数据库连接
		String sql="SELECT * from user_info WHERE userno='"+userno+" 'and password ='"+password+"'";
		
		try {
			//1、获得数据库连接
			this.conn=this.getConn();
			//2、获得执行命令对象
			this.ps=conn.prepareStatement(sql);
			//3、执行命令
			this.rs=ps.executeQuery();
			if(rs.next()) {//指向每一条，只要有一条，就认为登录成功
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public List<UserInfo> findUserList() {
		// TODO Auto-generated method stub
		String sql="SELECT * from user_info";
		try {
			//1、获得数据库连接
			this.conn=this.getConn();
			//2、获得执行命令对象
			this.ps=conn.prepareStatement(sql);
			//3、执行命令
			this.rs=ps.executeQuery();
			List<UserInfo> list = new ArrayList<UserInfo>();
			
			while(rs.next()) {//指向每一条，只要有一条，就认为登录成功
				int userId=rs.getInt("userId");
				String userno=rs.getString("userno");
				String password=rs.getString("password");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				UserInfo userInfo=new UserInfo();
				userInfo.setUserId(userId);
				userInfo.setUserno(userno);
				userInfo.setPassword(password);
				userInfo.setEmail(email);
				userInfo.setPhone(phone);
				list.add(userInfo);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
