package service;

import java.util.List;

import dao.UserDao;
import db.util.UserInfo;

public class UserService {

	UserDao userDao=new UserDao();
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param userno
	 * @param password
	 * @return
	 */
	public boolean login(String userno,String password) {
		return userDao.login(userno,password);
	}
	public List<UserInfo> findUserList() {
		// TODO Auto-generated method stub
		return userDao.findUserList();
	}
	
}
