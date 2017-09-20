package lyh.bos.service;

import lyh.bos.utils.PageBean;

public interface WorkbillService {

    void pageQuery(PageBean pageBean);

    void delete(String ids);

}
