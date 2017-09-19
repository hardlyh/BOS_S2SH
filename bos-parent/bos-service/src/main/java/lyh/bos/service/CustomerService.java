package lyh.bos.service;

import java.util.List;

import lyh.bos.domain.Customer;

public interface CustomerService {

    List<Customer> findCustomerNull();
    
    List<Customer> findByDecideIdCustomer(String id);

    void updateCustomer(String num, int[] customerIds);

    Customer findByTel(String c_Tel);
    
    String findByAddressTodecided(String addr);
    

}
