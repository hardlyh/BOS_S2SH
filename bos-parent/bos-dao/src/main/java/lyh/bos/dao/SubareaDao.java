package lyh.bos.dao;

import java.util.List;

import lyh.bos.base.dao.IBaseDao;
import lyh.bos.domain.Subarea;

public interface SubareaDao extends IBaseDao<Subarea>{

    List<Object> findSubareasGroupByProvince();

    void matchDelete(String str);

}
