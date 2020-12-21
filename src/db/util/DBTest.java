package db.util;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import dao.ImageDao;
import model.Image;

public class DBTest {

	public static void main(String[] args) throws SQLException {
		ImageDao imageDao = new ImageDao();
		
		Image im1 = new Image();
		im1.setTitle("房子");
		im1.setDescription("这是一所梦想的房子。");
		im1.setPath("images/house.jpg");
		im1.setTimestamp(new Date());
		
		System.out.println(imageDao.insert(im1));
		
		List<Image> images = imageDao.findAll();
		images.stream().forEach((Image image) -> System.out.println(image.toString()));
		
		im1.setId(8);
		im1.setDescription("Hello");
		imageDao.update(im1);
		
		System.out.println(imageDao.findById(1));
		System.out.println(imageDao.findById(9));
	}

}
