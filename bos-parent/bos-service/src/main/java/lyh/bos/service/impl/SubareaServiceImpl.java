package lyh.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lyh.bos.dao.SubareaDao;
import lyh.bos.domain.Subarea;
import lyh.bos.service.ISubareaService;
import lyh.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

    @Autowired
    private SubareaDao subareaDao;

    /*
     * 保存对象
     */
    public void save(Subarea model) {
        subareaDao.save(model);
    }

    /*
     * 查询所有信息
     */
    public List finAll() {

        return subareaDao.findAll();
    }

    /*
     * 分页查询
     */
    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    /*
     * 未分配定去的分区
     */
    public List<Subarea> findNotAssocation() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
        criteria.add(Restrictions.isNull("decidedzone"));
        return subareaDao.findByCriteria(criteria);
    }

    /* 
     * 通过定去id查询对应的分区
     */
    public List<Subarea> findByDecededzoneId(String deciId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
        criteria.add(Restrictions.eq("decidedzone.id", deciId));
        return subareaDao.findByCriteria(criteria);
    }

    /* 
     * 区域分布图
     */
    public List<Object> findSubareasGroupByProvince() {
        return subareaDao.findSubareasGroupByProvince();
    }

    /* 
     * 删除分区 
     */
    public void delete(String ids) {
        if(StringUtils.isNotBlank(ids)){
            String[] arr = ids.split(",");
            for (String str : arr) {
                subareaDao.matchDelete(str);
            }
        }
    }

    /* 
     * 更新
     */
    public void update(Subarea model) {
        subareaDao.update(model);
    }

}
