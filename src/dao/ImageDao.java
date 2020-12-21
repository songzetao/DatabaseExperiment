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

import db.util.BasicDaoInterface;
import db.util.DatabaseUtil;
import model.Image;

public class ImageDao implements BasicDaoInterface<Image> {

	@Override
	public List<Image> findAll() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Image image = null;
		List<Image> images = new ArrayList<Image>();
		String sql = "SELECT * FROM IMAGE";

		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				image = new Image();
				image.setId(rs.getInt("id"));
				image.setTitle(rs.getString("title"));
				image.setDescription(rs.getString("description"));
				image.setPath(rs.getString("path"));
				image.setTimestamp(rs.getTimestamp("timestamp"));
				images.add(image);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("select all data failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return images;
	}

	@Override
	public int insert(Image image) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		int autoIncKey = -1;

		String sql = "INSERT INTO IMAGE(title, description, path, timestamp) VALUES(?, ?, ?, ?)";

		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, image.getTitle());
			ps.setString(2, image.getDescription());
			ps.setString(3, image.getPath());
			ps.setTimestamp(4, new Timestamp(new Date().getTime()));

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

	@Override
	public void update(Image image) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE IMAGE SET title=?, description=?, path=?, timestamp=? WHERE id=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, image.getTitle());
			ps.setString(2, image.getDescription());
			ps.setString(3, image.getPath());
			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
			ps.setInt(5, image.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("update data failed.");
		} finally {
			DatabaseUtil.close(null, ps, conn);
		}
	}

	@Override
	public void delete(Image image) throws SQLException {
		deleteById(image.getId());
	}

	@Override
	public void deleteById(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM IMAGE WHRER id=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("delete data failed.");
		} finally {
			DatabaseUtil.close(null, ps, conn);
		}
	}

	@Override
	public Image findById(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Image image = null;
		String sql = "SELECT * FROM IMAGE WHERE id=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				image = new Image();
				image.setId(rs.getInt("id"));
				image.setTitle(rs.getString("title"));
				image.setDescription(rs.getString("description"));
				image.setPath(rs.getString("path"));
				image.setTimestamp(rs.getTimestamp("timestamp"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("select by id failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return image;
	}
}
