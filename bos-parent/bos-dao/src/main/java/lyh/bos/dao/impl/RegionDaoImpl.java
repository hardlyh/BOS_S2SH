package lyh.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import lyh.bos.base.dao.impl.BaseDaoImpl;
import lyh.bos.dao.RegionDao;
import lyh.bos.domain.Region;
import lyh.bos.utils.PageBean;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao {

    public List findListByQ(String q) {
        String hql="from Region r where r.shortcode like ? or r.province like ? or r.city like ?"
                + "or r.citycode like ? or r.district like ?";
        List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
        return list;
    }

    public void deleteById(String str) {
        String hql="delete Region r where r.id=?";
        this.getHibernateTemplate().bulkUpdate(hql, str);
    }
   

}
