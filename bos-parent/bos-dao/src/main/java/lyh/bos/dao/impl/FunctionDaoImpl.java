package lyh.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import lyh.bos.base.dao.impl.BaseDaoImpl;
import lyh.bos.domain.Function;
import lyh.bos.dao.FunctionDao;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements FunctionDao {

    public List<Function> findAllMenu() {
        String hql = "FROM Function f WHERE f.generatemenu = '1' ORDER BY f.zindex asc";
        return (List<Function>) this.getHibernateTemplate().find(hql);
    }

    public List<Function> findMenuById(Integer id) {
        String hql = "SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
                + " r LEFT OUTER JOIN r.users u WHERE u.id = ? AND f.generatemenu = '1' "
                + "ORDER BY f.zindex asc";
        return (List<Function>) this.getHibernateTemplate().find(hql,id);
    }

    public List<Function> findFunctionByUserId(Integer id) {
        String hql = "select DISTINCT f from Function f left outer join f.roles r left outer join "
                + "r.users u where u.id=?  ";
        return (List<Function>) this.getHibernateTemplate().find(hql, id);
    }

}
