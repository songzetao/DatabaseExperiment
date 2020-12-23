package db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * H2数据库测试以及数据准备
 * 
 * @author xiaodong
 *
 */
public class H2DatabasePreparation {
	/*
	 * 以嵌入式(本地)连接方式连接H2数据库
	 */
	private static final String JDBC_URL = "jdbc:h2:file:~/db/h2db"; // 使用绝对路径
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private static final String DRIVER_CLASS = "org.h2.Driver";

	public static void main(String[] args) throws Exception {
		Class.forName(DRIVER_CLASS);
		Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
		Statement statement = conn.createStatement();
		// 删表
		statement.execute("DROP TABLE IF EXISTS IMAGE");
		// 创建表
		statement.execute(
				"CREATE TABLE IMAGE(id INTEGER PRIMARY KEY auto_increment, title VARCHAR(255), description VARCHAR(255), path VARCHAR(255), timestamp TIMESTAMP)");
		// 插入数据样例
		statement.executeUpdate(
				"INSERT INTO IMAGE(title, description, path, timestamp) VALUES('Car', 'This is a car image', 'images/car.jpg', CURRENT_TIMESTAMP())");
		statement.executeUpdate(
				"INSERT INTO IMAGE(title, description, path, timestamp) VALUES('Beauty', 'This is a beauty image', 'images/beauty.jpg', CURRENT_TIMESTAMP())");
		statement.executeUpdate(
				"INSERT INTO IMAGE(title, description, path, timestamp) VALUES('Game', 'This is a game image', 'images/game.jpg', CURRENT_TIMESTAMP())");
		statement.executeUpdate(
				"INSERT INTO IMAGE(title, description, path, timestamp) VALUES('Girl', 'This is a girl image', 'images/girl.jpg', CURRENT_TIMESTAMP())");

		// 查询全表数据
		ResultSet resultSet = statement.executeQuery("select * from IMAGE");

		while (resultSet.next()) {
			System.out.println(resultSet.getInt("id") + ", " + resultSet.getString("title") + ", "
					+ resultSet.getString("description") + ", " + resultSet.getString("path") + ", "
					+ resultSet.getTimestamp("timestamp"));
		}

		statement.close();
		conn.close();
	}

}