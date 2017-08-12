package lyh.bos.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import lyh.bos.base.dao.IBaseDao;
import lyh.bos.utils.PageBean;

/**
 * 持久层通用实现
 * 
 * @author zhaoqx
 *
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
    // 代表的是某个实体的类型
    private Class<T> entityClass;

    @Resource // 根据类型注入spring工厂中的会话工厂对象sessionFactory
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    // 在父类（BaseDaoImpl）的构造方法中动态获得entityClass
    public BaseDaoImpl() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 获得父类上声明的泛型数组
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        entityClass = (Class<T>) actualTypeArguments[0];
    }

    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass, id);
    }

    public List<T> findAll() {
        String hql = "FROM " + entityClass.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }

    /*
     * 更新
     */
    public void executeUpdate(String queryVo, Object... obj) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.createQuery(queryVo);
        int i = 0;
        for (Object ob : obj) {
            query.setParameter(i++, ob);
        }
        query.executeUpdate();
    }

    /*
     * 分页通用查询
     */
    public void pageQuery(PageBean bean) {
        int currentPage = bean.getCurrentPage();
        int pageSize = bean.getPageSize();
        DetachedCriteria criteria = bean.getCriteria();

        // 查询所有条数
        criteria.setProjection(Projections.rowCount());
        List<Long> countList = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
        Long count = countList.get(0);
        bean.setTotal(count.intValue());
        // 查询rows
        criteria.setProjection(null);
        int firstResult = (currentPage-1)*pageSize;
        int maxResults = pageSize;
        bean.setRows(this.getHibernateTemplate().findByCriteria(criteria, firstResult, maxResults));
    }
}
