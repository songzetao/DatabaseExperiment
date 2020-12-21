package db.util;

import java.sql.SQLException;
import java.util.List;

public interface BasicDaoInterface<T> {

	public List<T> findAll() throws SQLException;

	public int insert(T entity) throws SQLException;

	public void update(T entity) throws SQLException;

	public void delete(T entity) throws SQLException;
	
	public void deleteById(int id) throws SQLException;

	public T findById(int id) throws SQLException;
}
