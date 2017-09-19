package lyh.bos.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lyh.bos.dao.DecidedzoneDao;
import lyh.bos.dao.NoticebillDao;
import lyh.bos.dao.WorkbillDao;
import lyh.bos.domain.Decidedzone;
import lyh.bos.domain.Noticebill;
import lyh.bos.domain.Staff;
import lyh.bos.domain.User;
import lyh.bos.domain.Workbill;
import lyh.bos.service.CustomerService;
import lyh.bos.service.NoticebillService;
import lyh.bos.utils.BOSUtils;

@Service
@Transactional
public class NoticebillServiceImpl implements NoticebillService{
    /* 
     * 保存业务受理对象
     */
    @Autowired
    private NoticebillDao noticebillDao;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private DecidedzoneDao decidedzoneDao;
    
    @Autowired
    private WorkbillDao workbillDao;
    
    /* 
     * 保存业务受理对象,并且尝试自动分单
     */
    public void save(Noticebill model) {
        User user = BOSUtils.getLoginUser();   // 设置录入业务的人员
        model.setUser(user);
        
        String addr=model.getPickaddress();  // 通过输入的地址尝试判断是不是分配定区
        String id = customerService.findByAddressTodecided(addr);
        if(id!=null){  // 如果有关联定区, 则自动分单
            Decidedzone decided = decidedzoneDao.findById(id);
            Staff staff = decided.getStaff();   // 设置分配员
            model.setStaff(staff);
            model.setOrdertype(Noticebill.ORDERTYPE_AUTO);  // 设置为自动分单
            // 产生一个工单
            Workbill workbill=new Workbill();
            workbill.setAttachbilltimes(0);  // 追单次数
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));  // 创建时间
            workbill.setNoticebill(model);   // 工单页面关联通知单
            workbill.setPickstate(Workbill.PICKSTATE_NO);   // 取件状态
            workbill.setRemark(model.getRemark());  // 备注
            workbill.setStaff(staff);  // 管理取派员
            workbill.setType(Workbill.TYPE_1);  // 工单状态(新单)
            workbillDao.save(workbill);  // 保存工单
            
        }else{
            model.setOrdertype(Noticebill.ORDERTYPE_MAN);
            
        }
        noticebillDao.save(model);
    }
    

}
