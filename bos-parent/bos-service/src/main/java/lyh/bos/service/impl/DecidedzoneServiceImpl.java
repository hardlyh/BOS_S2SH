package lyh.bos.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lyh.bos.dao.DecidedzoneDao;
import lyh.bos.dao.SubareaDao;
import lyh.bos.domain.Decidedzone;
import lyh.bos.domain.Subarea;
import lyh.bos.service.DecidedzoneService;
import lyh.bos.utils.PageBean;
/**
 * 
 */
@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService{

    @Autowired
    private DecidedzoneDao decidedzoneDao;
    
    @Autowired
    private SubareaDao subareaDao;

    /* 
     * 保存对象
     */
    public void save(Decidedzone model, String[] id) {
        decidedzoneDao.save(model);
        for (String str : id) {
            Subarea subarea = subareaDao.findById(str);
            subarea.setDecidedzone(model);
        }
    }

    /* 
     * 分页查询
     */
    public void pageQuery(PageBean pageBean) {
        decidedzoneDao.pageQuery(pageBean);
    }

    /* 
     * 分区修改
     */
    public void update(Decidedzone model) {
        if(StringUtils.isNotBlank(model.getName())&&model.getStaff()!=null){
            Decidedzone oj = decidedzoneDao.findById(model.getId());
            oj.setName(model.getName());
            oj.setStaff(model.getStaff());
        }
    }

    /* 
     * 删除数据
     */
    public void delte(String deleteIds) {
        if(StringUtils.isNotBlank(deleteIds)){
            String[] ids = deleteIds.split(",");
            for (String str : ids) {
                decidedzoneDao.deleteById(str);
            }
        }
    }
}
