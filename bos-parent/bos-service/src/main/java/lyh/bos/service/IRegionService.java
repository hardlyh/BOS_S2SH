package lyh.bos.service;

import java.util.List;

import lyh.bos.domain.Region;
import lyh.bos.utils.PageBean;

public interface IRegionService {
    
    public void saveBatch(List<Region> list);

    public void pageQuery(PageBean pageBean);

    public List findListByQ(String q);

    public List<Region> findAll();

    public void save(Region model);

    public void delete(String ids);

    public void update(Region model);

}
