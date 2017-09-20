package lyh.bos.service;

import lyh.bos.domain.Noticebill;
import lyh.bos.utils.PageBean;

public interface NoticebillService {
    void save(Noticebill model);
    void pageQuery(PageBean pageBean);
    void dispatch(Noticebill model);
}
