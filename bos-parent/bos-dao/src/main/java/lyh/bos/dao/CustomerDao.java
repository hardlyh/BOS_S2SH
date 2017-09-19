package lyh.bos.dao;

import lyh.bos.base.dao.IBaseDao;
import lyh.bos.domain.Customer;

public interface CustomerDao extends IBaseDao<Customer>{

    void updateCustomer(String num, int[] customerIds);
    

}
