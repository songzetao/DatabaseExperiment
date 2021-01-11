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
	public void clickDetail(int id)throws SQLException {
		Image i=imageDao.findById(id);
		i.setHitsNumber(i.getHitsNumber()+1);
		System.out.println(i.getHitsNumber());
		imageDao.update(i);
	}
	public List<Image> getImagesC()throws SQLException{
		return imageDao.getImageCByDec();
	}
}
