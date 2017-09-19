package lyh.bos.dao.impl;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import lyh.bos.base.dao.impl.BaseDaoImpl;
import lyh.bos.dao.CustomerDao;
import lyh.bos.domain.Customer;

@Repository
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

    @Override
    public void updateCustomer(String num, int[] customerIds) {

        String hql = "update t_customer set decidedzone_id=null where decidedzone_id=?";
        Session openSession = this.getHibernateTemplate().getSessionFactory().openSession();
        Query createQuery = openSession.createSQLQuery(hql);
        createQuery.setString(0, num);
        createQuery.executeUpdate();
        hql = "update t_customer set decidedzone_id=? where id=?";
        createQuery = openSession.createSQLQuery(hql);
        createQuery.setString(0, num);
        for (int i : customerIds) {
            createQuery.setInteger(1, i);
            createQuery.executeUpdate();
        }
    }

}
