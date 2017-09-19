package lyh.bos.dao.impl;

import org.springframework.stereotype.Repository;

import lyh.bos.base.dao.impl.BaseDaoImpl;
import lyh.bos.dao.StaffDao;
import lyh.bos.domain.Staff;
@Repository
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao {

    @Override
    public void restore(String str) {
        String hql="update Staff s set s.deltag='0' where s.id=?";
        this.getHibernateTemplate().bulkUpdate(hql, str);
    }

}
