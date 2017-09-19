package lyh.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lyh.bos.dao.RegionDao;
import lyh.bos.domain.Region;
import lyh.bos.service.IRegionService;
import lyh.bos.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
    
    @Autowired
    private RegionDao regiondao;

    /* 
     * 批量保存region对象
     */
    public void saveBatch(List<Region> list) {
        for (Region region : list) {
            regiondao.saveOrUpdate(region);
        }
    }
    /* 
     * 分页查询
     */
    public void pageQuery(PageBean pageBean) {
        regiondao.pageQuery(pageBean);
    }

    /* 
     * 通过条件查询区域对象
     */
    public List findListByQ(String q) {
        return regiondao.findListByQ(q);
    }
    
    public List<Region> findAll() {
        return regiondao.findAll();
    }
    
    /* 
     * 保存对象
     */
    public void save(Region model) {
        regiondao.save(model);
        
    }
    /* 
     * 批量删除对象
     */
    public void delete(String ids) {
        if(StringUtils.isNotBlank(ids)){
            String[] arr = ids.split(",");
            for (String str : arr) {
                regiondao.deleteById(str);
            }
        }
    }
    /* 
     * 更新区域
     */
    public void update(Region model) {
        regiondao.update(model);
    }

}
