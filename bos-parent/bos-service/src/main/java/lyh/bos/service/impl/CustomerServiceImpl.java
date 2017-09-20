package lyh.bos.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lyh.bos.dao.CustomerDao;
import lyh.bos.domain.Customer;
import lyh.bos.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> findCustomerNull() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        criteria.add(Restrictions.isNull("decidedzone"));
        return customerDao.findByCriteria(criteria);
    }

    public List<Customer> findByDecideIdCustomer(String id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        criteria.add(Restrictions.eq("decidedzone.id", id));
        return customerDao.findByCriteria(criteria);
    }

    public void updateCustomer(String num, int[] customerIds) {

        customerDao.updateCustomer(num, customerIds);
    }

    /*
     * 根据手机号查询客户
     */
    public Customer findByTel(String c_Tel) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        criteria.add(Restrictions.eq("telephone", c_Tel));
        List<Customer> list = customerDao.findByCriteria(criteria);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    /*
     * 根据地址查询该地址对应的定区id
     */
    public String findByAddressTodecided(String addr) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        criteria.add(Restrictions.eq("address", addr));
        List<Customer> list = customerDao.findByCriteria(criteria);
        if(list.size()>0 && list.get(0).getDecidedzone()!=null){
            return list.get(0).getDecidedzone().getId();
        }else{
            return null;
        }
       
    }

}
