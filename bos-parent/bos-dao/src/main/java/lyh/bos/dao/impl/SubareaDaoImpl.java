package lyh.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import lyh.bos.base.dao.impl.BaseDaoImpl;
import lyh.bos.dao.SubareaDao;
import lyh.bos.domain.Subarea;
@Repository
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements SubareaDao{

    public List<Object> findSubareasGroupByProvince() {
// SELECT r.province,count(*) FROM bc_subarea s LEFT OUTER JOIN bc_region r ON s.region_id=r.id GROUP BY r.province
        String hql="select r.province,count(*) FROM Subarea s LEFT OUTER JOIN s.region r GROUP BY r.province";
        return (List<Object>) this.getHibernateTemplate().find(hql);
    }

    public void matchDelete(String str) {
        String hql="delete Subarea s where s.id=?";
        this.getHibernateTemplate().bulkUpdate(hql, str);
    }
    

}
