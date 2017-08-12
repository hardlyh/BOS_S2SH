package lyh.bos.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lyh.bos.dao.StaffDao;
import lyh.bos.domain.Staff;
import lyh.bos.service.IStaffService;
import lyh.bos.utils.PageBean;
import net.sf.json.JSONObject;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
    @Autowired
    private StaffDao staffDao;

    @Override
    public void save(Staff model) {
        staffDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        staffDao.pageQuery(pageBean);
        
    }

    public void delete(String ids) {
        if(StringUtils.isNotBlank(ids)){
            String[] id = ids.split(",");
            for(String str:id){
                staffDao.executeUpdate("update Staff set deltag='1' where id=?", str);
            }
        }
    }

    public void update(Staff model) {
       Staff staff = staffDao.findById(model.getId());
       staff.setName(model.getName());
       staff.setTelephone(model.getTelephone());
       staff.setStation(model.getStation());
       staff.setHaspda(model.getHaspda());
       staff.setStandard(model.getStandard());
       staffDao.update(staff);
    }

}
