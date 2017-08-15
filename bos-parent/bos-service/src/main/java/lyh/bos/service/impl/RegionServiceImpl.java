package lyh.bos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lyh.bos.dao.RegionDao;
import lyh.bos.domain.Region;
import lyh.bos.service.IRegionService;

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

}
