package lyh.bos.service;

import lyh.bos.domain.Decidedzone;
import lyh.bos.utils.PageBean;

public interface DecidedzoneService {

    void save(Decidedzone model, String[] id);

    void pageQuery(PageBean pageBean);

    void update(Decidedzone model);

    void delte(String deleteIds);

    
    

}
