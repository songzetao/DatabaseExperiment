package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.util.DatabaseUtil;
import model.Comment;
import model.Image;
import model.User;
import util.ResponseBuilder;
import util.ResponseCode;

/**
 * 初始化Web应用
 * 
 * 初始化部分数据，包括IMAGE, USER数据表等
 * 
 * @author xiaodong
 *
 */
@WebServlet("/app-init")
public class AppInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AppInitServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 存放完成数据准备后的返回数据
		HashMap<String, Object> dataInit = new HashMap<String, Object>();

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		Connection connUser=null;
		Statement statUser=null;
		ResultSet rsUser=null;
		Statement statCom=null;
		ResultSet rsCom=null;
		

		try {
			conn = DatabaseUtil.getConnection();
			stat = conn.createStatement();
			// 删表
			stat.execute("DROP TABLE IF EXISTS IMAGE");
			// 创建表
			stat.execute(
					"CREATE TABLE IMAGE(id INTEGER PRIMARY KEY auto_increment, title VARCHAR(255), description VARCHAR(255), path VARCHAR(255), timestamp TIMESTAMP,hitsNumber INTEGER,comment VARCHAR(255))");
			// 插入数据样例
			stat.executeUpdate(
					"INSERT INTO IMAGE(title, description, path, timestamp,hitsNumber,comment) VALUES('Car', 'This is a car image', 'images/car.jpg', CURRENT_TIMESTAMP(),0,null)");
			stat.executeUpdate(
					"INSERT INTO IMAGE(title, description, path, timestamp,hitsNumber,comment) VALUES('Beauty', 'This is a beauty image', 'images/beauty.jpg', CURRENT_TIMESTAMP(),0,null)");
			stat.executeUpdate(
					"INSERT INTO IMAGE(title, description, path, timestamp,hitsNumber,comment) VALUES('Game', 'This is a game image', 'images/game.jpg', CURRENT_TIMESTAMP(),0,null)");
			stat.executeUpdate(
					"INSERT INTO IMAGE(title, description, path, timestamp,hitsNumber,comment) VALUES('Girl', 'This is a girl image', 'images/girl.jpg', CURRENT_TIMESTAMP(),0,null)");
			stat.executeUpdate(
					"INSERT INTO IMAGE(title, description, path, timestamp,hitsNumber,comment) VALUES('House', 'This is a house image', 'images/house.jpg', CURRENT_TIMESTAMP(),0,null)");
			stat.executeUpdate(
					"INSERT INTO IMAGE(title, description, path, timestamp,hitsNumber,comment) VALUES('Snow', 'This is a snow image', 'images/snow.jpg', CURRENT_TIMESTAMP(),0,null)");
			stat.executeUpdate(
					"INSERT INTO IMAGE(title, description, path, timestamp,hitsNumber,comment) VALUES('trees', 'This is a trees image', 'images/trees.jpg', CURRENT_TIMESTAMP(),0,null)");
			
			
			

			// 查询全表数据
			rs = stat.executeQuery("SELECT * FROM IMAGE");
			List<Image> images = new ArrayList<Image>();
			Image image = null;
			while (rs.next()) {
				image = new Image();
				image.setId(rs.getInt("id"));
				image.setTitle(rs.getString("title"));
				image.setDescription(rs.getString("description"));
				image.setPath(rs.getString("path"));
				image.setTimestamp(rs.getTimestamp("timestamp"));
				image.setHitsNumber(rs.getInt("hitsNumber"));
				images.add(image);
			}
			dataInit.put("images", images);

			/*************************************************************************
			 * Tag: 参考以上IMAGE表初始化代码，补充初始化创建USER表并添加admin/admin用户的代码
			 *************************************************************************/
			//stat.close();
			
			
			statUser=conn.createStatement();
			statUser.execute("DROP TABLE IF EXISTS USER");
			// 创建表
			statUser.execute(
					"CREATE TABLE USER(id INTEGER PRIMARY KEY auto_increment, username VARCHAR(255), password VARCHAR(255))");
			// 插入数据样例
			statUser.executeUpdate(
					"INSERT INTO USER(username, password) VALUES('songzetao', '123456')");
			
			rsUser = statUser.executeQuery("SELECT * FROM USER");
			List<User> users = new ArrayList<User>();
			User user = null;
			while (rsUser.next()) {
				user = new User();
				user.setUsername(rsUser.getString("username"));
				user.setPassword(rsUser.getString("password"));
				users.add(user);
			}
			dataInit.put("user", users); 

			//创建评论表
			statCom=conn.createStatement();
				// 删表
			statCom.execute("DROP TABLE IF EXISTS COMMENT");
				// 创建表
			statCom.execute(
						"CREATE TABLE COMMENT(id INTEGER PRIMARY KEY auto_increment, imageID INTEGER, username VARCHAR(255), timestamp TIMESTAMP,comment VARCHAR(255))");
				// 插入数据样例
			statCom.executeUpdate(
						"INSERT INTO COMMENT(imageID, username, timestamp, comment) VALUES(1, 'song', CURRENT_TIMESTAMP(),'这是一个测试评论')");
			statCom.executeUpdate(
						"INSERT INTO COMMENT(imageID, username, timestamp, comment) VALUES(1, 'ze', CURRENT_TIMESTAMP(),'这是一个测试评论')");
			statCom.executeUpdate(
						"INSERT INTO COMMENT(imageID, username, timestamp, comment) VALUES(3, 'song', CURRENT_TIMESTAMP(),'这是一个测试评论')");
			statCom.executeUpdate(
						"INSERT INTO COMMENT(imageID, username, timestamp, comment) VALUES(4, 'song', CURRENT_TIMESTAMP(),'这是一个测试评论')");
			statCom.executeUpdate(
						"INSERT INTO COMMENT(imageID, username, timestamp, comment) VALUES(5, 'song', CURRENT_TIMESTAMP(),'这是一个测试评论')");
			statCom.executeUpdate(
						"INSERT INTO COMMENT(imageID, username, timestamp, comment) VALUES(6, 'song', CURRENT_TIMESTAMP(),'这是一个测试评论')");
			statCom.executeUpdate(
						"INSERT INTO COMMENT(imageID, username, timestamp, comment) VALUES(7, 'song', CURRENT_TIMESTAMP(),'这是一个测试评论')");
				// 查询全表数据
			rs = stat.executeQuery("SELECT * FROM COMMENT");
			List<Comment> comments = new ArrayList<Comment>();
			Comment comment = null;
			while (rs.next()) {
				comment = new Comment();
				comment.setImageID(rs.getInt("imageID"));
				comment.setUsername(rs.getString("username"));
				comment.setTimestamp(rs.getTimestamp("timestamp"));
				comment.setComment(rs.getString("comment"));
				comments.add(comment);
			}
			dataInit.put("comments", comments);
			
			
			
			
			
			
			// 将初始化的数据返回
			if (dataInit.size() != 0) {
				response.getWriter().append(ResponseBuilder.createJson(dataInit));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().append(ResponseBuilder.createJson(ResponseCode.SERVICE_ERROR));
		} finally {
			DatabaseUtil.close(rs, stat, conn);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
