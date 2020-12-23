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
import model.Image;
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

		try {
			conn = DatabaseUtil.getConnection();
			stat = conn.createStatement();
			// 删表
			stat.execute("DROP TABLE IF EXISTS IMAGE");
			// 创建表
			stat.execute(
					"CREATE TABLE IMAGE(id INTEGER PRIMARY KEY auto_increment, title VARCHAR(255), description VARCHAR(255), path VARCHAR(255), timestamp TIMESTAMP)");
			// 插入数据样例
			stat.executeUpdate(
					"INSERT INTO IMAGE(title, description, path, timestamp) VALUES('Car', 'This is a car image', 'images/car.jpg', CURRENT_TIMESTAMP())");
			stat.executeUpdate(
					"INSERT INTO IMAGE(title, description, path, timestamp) VALUES('Beauty', 'This is a beauty image', 'images/beauty.jpg', CURRENT_TIMESTAMP())");
			stat.executeUpdate(
					"INSERT INTO IMAGE(title, description, path, timestamp) VALUES('Game', 'This is a game image', 'images/game.jpg', CURRENT_TIMESTAMP())");
			stat.executeUpdate(
					"INSERT INTO IMAGE(title, description, path, timestamp) VALUES('Girl', 'This is a girl image', 'images/girl.jpg', CURRENT_TIMESTAMP())");
			stat.executeUpdate(
					"INSERT INTO IMAGE(title, description, path, timestamp) VALUES('House', 'This is a house image', 'images/house.jpg', CURRENT_TIMESTAMP())");
			stat.executeUpdate(
					"INSERT INTO IMAGE(title, description, path, timestamp) VALUES('Snow', 'This is a snow image', 'images/snow.jpg', CURRENT_TIMESTAMP())");
			stat.executeUpdate(
					"INSERT INTO IMAGE(title, description, path, timestamp) VALUES('trees', 'This is a trees image', 'images/trees.jpg', CURRENT_TIMESTAMP())");
			

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
				images.add(image);
			}
			dataInit.put("images", images);

			/*************************************************************************
			 * Tag: 参考以上IMAGE表初始化代码，补充初始化创建USER表并添加admin/admin用户的代码
			 *************************************************************************/

			
			
			
			dataInit.put("user", null); 

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
