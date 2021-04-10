package dao;

import java.security.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.util.UserInfo;
import model.Player;

public class PlayerDao extends BaseDao{

	public PlayerDao() {
		// TODO Auto-generated constructor stub
	}
	
	public Player getPlayerInfo() {
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
		
		return null;
	}

	public List<Player> findPlayerList() {
		// TODO Auto-generated method stub
		String sql="SELECT * from player";
		try {
			//1、获得数据库连接
			this.conn=this.getConn();
			//2、获得执行命令对象
			this.ps=conn.prepareStatement(sql);
			//3、执行命令
			this.rs=ps.executeQuery();
			List<Player> list = new ArrayList<Player>();
			
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
				
				int pid=rs.getInt("Pid");
				String pname=rs.getString("Pname");
				Double pheight=rs.getDouble("Pheight");
				Double pweight=rs.getDouble("Pweight");
				int page=rs.getInt("Page");
				String pteam=rs.getString("Pteam");
				int pscore=rs.getInt("Psore");
				java.sql.Timestamp pdate=rs.getTimestamp("Pdate");
				String pselect=rs.getString("Pselect");
				String prank=rs.getString("Prank");
				
				Player player=new Player();
				player.setPid(pid);
				player.setPname(pname);
				player.setPheight(pheight);
				player.setPweight(pweight);
				player.setPage(page);
				player.setPteam(pteam);
				player.setPscore(pscore);
				player.setPdate(pdate);
				
				
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
