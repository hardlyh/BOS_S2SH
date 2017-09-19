package lyh.bos.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lyh.bos.action.base.BaseAction;
import lyh.bos.domain.Customer;
import lyh.bos.service.CustomerService;

@Controller
@Scope("prototype")
public class CustomerAction extends BaseAction<Customer> {
    @Autowired
    private CustomerService customerService;

    private String num;
    
    private int[] customerIds;
    

    public int[] getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(int[] customerIds) {
        this.customerIds = customerIds;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    /**
     * 查找没有定区关联的客户
     * @return
     */
    public String findCustomerNotNull() {
        List<Customer> list = customerService.findCustomerNull();
        this.objectToString(list, new String[] { "decidedzone" });
        return null;
    }

    /**
     * 根据指定定区查找关联客户
     * @return
     */
    public String findByDecideIdCustomer() {
        List<Customer> list = customerService.findByDecideIdCustomer(this.num);
        this.objectToString(list, new String[] { "decidedzone" });
        return null;
    }
    
    /**
     * 更新定区关联关系
     * @return
     */
    public String updateCustomer(){
        customerService.updateCustomer(num,customerIds);
        return "list";
    }
    
    private String c_Tel;
    
    public void setC_Tel(String c_Tel) {
        this.c_Tel = c_Tel;
    }

    /**
     * 根据手机号查询客户
     * @return
     */
    public String findByTel(){
        Customer customer=customerService.findByTel(c_Tel);
        this.objectToString(customer, new String[]{"decidedzone"});
        return null;
    }

}
