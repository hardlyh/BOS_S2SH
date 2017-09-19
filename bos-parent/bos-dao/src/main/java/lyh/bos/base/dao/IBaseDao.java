package lyh.bos.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import lyh.bos.utils.PageBean;

/**
 * 持久层通用接口
 * @author zhaoqx
 *
 * @param <T>
 */
public interface IBaseDao<T> {
    public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public void saveOrUpdate(T entity);
	public List<T> findByCriteria(DetachedCriteria obj);
	public T findById(Serializable id);
	public List<T> findAll();
	public void executeUpdate(String queryVo,Object... obj);
	public void pageQuery(PageBean bean);
}
