package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import db.util.BasicDaoInterface;
import db.util.DatabaseUtil;
import model.Image;

public class ImageDao implements BasicDaoInterface<Image> {

	@Override
	public List<Image> findPaged(int pno, int count) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Image image = null;
		List<Image> images = new ArrayList<Image>();
		String sql = "SELECT * FROM IMAGE LIMIT ?,?";

		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (pno - 1) * count);
			ps.setInt(2, count);
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
			throw new SQLException("select data paged failed.");
		} finally {
			DatabaseUtil.close(rs, ps, conn);
		}
		return images;
	}

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
				image.setHitsNumber(rs.getInt("hitsNumber"));
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
		String sql = "UPDATE IMAGE SET title=?, description=?,hitsNumber=?, path=?, timestamp=? WHERE id=?";
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, image.getTitle());
			ps.setString(2, image.getDescription());
			ps.setString(4, image.getPath());
			
			ps.setTimestamp(5, new Timestamp(new Date().getTime()));
			ps.setInt(6, image.getId());
			//System.out.println(ps.getParameterMetaData());
		
			ps.setInt(3, image.getHitsNumber());
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
				image.setHitsNumber(rs.getInt("hitsNumber"));
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
	public List<Image> getImageCByDec() throws SQLException{
		List<Image> images=this.findAll();
		for(Image i:images) {
			System.out.println(i.getDescription());
			System.out.println(i.getHitsNumber());
		}
		images=this.sort(images);
		return images;
	}
	private List<Image> sort(List<Image> img)throws SQLException{
		for(int i=0;i<img.size()-1;i++) {
			for(int j=i+1;j<img.size();j++) {
				if(img.get(j-1).getHitsNumber()<img.get(j).getHitsNumber()) {
					Image temp=img.get(j-1);
					img.set(j-1, img.get(j));
					img.set(j, temp);
				}
			}
		}
		List<Image> images=new ArrayList<Image>();
		images.add(img.get(0));
		images.add(img.get(1));
		images.add(img.get(2));
		for(Image i:images) {
			System.out.println(i.getHitsNumber());
		}
		return images;
	}
}
