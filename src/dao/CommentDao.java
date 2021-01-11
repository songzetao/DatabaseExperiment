package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jdt.internal.compiler.batch.Main;

import db.util.BasicDaoInterface;
import db.util.DatabaseUtil;
import model.Comment;
import model.Image;

public class CommentDao{

	public CommentDao() {
		// TODO 自动生成的构造函数存根
	}
	public List<Comment> findById(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Comment> comments=new ArrayList<Comment>();
		Comment comment = null;
		String sql = "SELECT * FROM COMMENT WHERE imageID=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				comment = new Comment();
				comment.setImageID(id);
				comment.setUsername(rs.getString("username"));
				comment.setTimestamp(rs.getTimestamp("timestamp"));
				comment.setComment(rs.getString("comment"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("select by id failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		for(Comment c:comments) {
			System.out.println(c.getUsername()+"   "+ c.getImageID()+"   "+c.getComment());
		}
		return comments;
	}
	
	public int insert(Comment comment) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int autoIncKey = -1;

		String sql = "INSERT INTO COMMENT(imageID, username, timestamp,comment) VALUES(?, ?, ?, ?)";

		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, comment.getImageID());
			ps.setString(2, comment.getUsername());
			ps.setString(4, comment.getComment());
			ps.setTimestamp(3, new Timestamp(new Date().getTime()));
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				autoIncKey = rs.getInt(1);
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("insert data failed.");
		} finally {
			DatabaseUtil.close(null, ps, conn);
		}
		return autoIncKey;
	}
	
	public static void main(String[] args) throws SQLException {
		CommentDao cd=new CommentDao();
		cd.findById(1);
	}
}
