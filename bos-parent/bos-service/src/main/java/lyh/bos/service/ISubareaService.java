package lyh.bos.service;

import java.util.List;

import lyh.bos.domain.Subarea;
import lyh.bos.utils.PageBean;

public interface ISubareaService {

    void save(Subarea model);

    List<Subarea> finAll();

    void pageQuery(PageBean pageBean);

    List<Subarea> findNotAssocation();

    List<Subarea> findByDecededzoneId(String deciId);

    List<Object> findSubareasGroupByProvince();

    void delete(String ids);

    void update(Subarea model);

    
    
    

}
