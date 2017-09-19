package lyh.bos.dao;

import java.util.List;

import lyh.bos.base.dao.IBaseDao;
import lyh.bos.domain.Region;

public interface RegionDao extends IBaseDao<Region>{

    List findListByQ(String q);

    void deleteById(String str);

}
