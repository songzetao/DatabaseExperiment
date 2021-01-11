package service;

import java.sql.SQLException;
import java.util.List;

import dao.CommentDao;
import model.Comment;

public class DetailService {
	private CommentDao commentDao;
	public DetailService() {
		// TODO 自动生成的构造函数存根
		commentDao=new CommentDao();
	}
	public void addComment(Comment comment)throws SQLException {
		commentDao.insert(comment);
	}
	public List<Comment> getCommentsById(int imageId)throws SQLException{
		return commentDao.findById(imageId);
	}
}
