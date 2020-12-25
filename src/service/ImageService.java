package service;

import java.sql.SQLException;
import java.util.List;

import dao.ImageDao;
import model.Image;

public class ImageService {
	private ImageDao imageDao;
	
	public ImageService() {
		imageDao = new ImageDao();
	}

	public List<Image> getImagesPaged(int pno, int count) throws SQLException {
		return imageDao.findPaged(pno, count);
	}

	public Image getImageById(int id) throws SQLException {
		return imageDao.findById(id);
	}
}
