package lyh.bos.service;

import java.util.List;

import lyh.bos.domain.Region;

public interface IRegionService {
    
    public void saveBatch(List<Region> list);

}
