package db.util;

import java.sql.SQLException;
import java.util.List;

/**
 * 数据查询接口
 * 
 * @author xiaodong
 *
 * @param <T>
 */
public interface BasicDaoInterface<T> {

	/**
	 * 基于主键ID查询数据
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public T findById(int id) throws SQLException;

	/**
	 * 分页查询数据
	 * 
	 * @param pno   第几页
	 * @param count 每页多少条
	 * @return
	 * @throws SQLException
	 */
	public List<T> findPaged(int pno, int count) throws SQLException;

	/**
	 * 查询所有数据
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<T> findAll() throws SQLException;

	/**
	 * 插入数据
	 * 
	 * @param entity
	 * @return
	 * @throws SQLException
	 */
	public int insert(T entity) throws SQLException;

	/**
	 * 修改数据
	 * 
	 * @param entity
	 * @throws SQLException
	 */
	public void update(T entity) throws SQLException;

	/**
	 * 删除数据
	 * 
	 * @param entity
	 * @throws SQLException
	 */
	public void delete(T entity) throws SQLException;

	/**
	 * 基于主键ID删除数据
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteById(int id) throws SQLException;
}
