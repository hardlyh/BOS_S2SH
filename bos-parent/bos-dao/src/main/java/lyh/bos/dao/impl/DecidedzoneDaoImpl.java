package lyh.bos.dao.impl;

import org.springframework.stereotype.Repository;

import lyh.bos.base.dao.impl.BaseDaoImpl;
import lyh.bos.dao.DecidedzoneDao;
import lyh.bos.domain.Decidedzone;
@Repository
public class DecidedzoneDaoImpl extends BaseDaoImpl<Decidedzone> implements DecidedzoneDao{

    @Override
    public void deleteById(String str) {
        String hql="delete Decidedzone d where d.id=?";
        this.getHibernateTemplate().bulkUpdate(hql, str);
    }

}
