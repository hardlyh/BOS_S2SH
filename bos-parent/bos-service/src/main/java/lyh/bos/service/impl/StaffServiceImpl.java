package lyh.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
        if (StringUtils.isNotBlank(ids)) {
            String[] id = ids.split(",");
            for (String str : id) {
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

    /*
     * 获得未删除的取派员
     */
    public List<Staff> findNotDelete() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Staff.class);
        criteria.add(Restrictions.eq("deltag", "0"));
        return staffDao.findByCriteria(criteria);
    }

    /*
     * 还原取派员
     */
    public void restore(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            String[] arr = ids.split(",");
            for (String str : arr) {
                staffDao.restore(str);
            }
        }
    }

    /* 
     * 根据传入的条件来进行查询
     */
    public void search(PageBean pageBean, Staff model) {
        DetachedCriteria criteria = pageBean.getCriteria();
        if(StringUtils.isNoneBlank(model.getName())){
            criteria.add(Restrictions.eq("name", model.getName()));
        }
        if(StringUtils.isNoneBlank(model.getDeltag())){
            criteria.add(Restrictions.eq("deltag", model.getDeltag()));
        }
        if(StringUtils.isNoneBlank(model.getTelephone())){
            criteria.add(Restrictions.eq("telephone", model.getTelephone()));
        }
        if(StringUtils.isNoneBlank(model.getHaspda())){
            criteria.add(Restrictions.eq("haspda", model.getHaspda()));
        }
        if(StringUtils.isNoneBlank(model.getStation())){
            criteria.add(Restrictions.eq("station", model.getStation()));
        }
        staffDao.pageQuery(pageBean);
    }

}
