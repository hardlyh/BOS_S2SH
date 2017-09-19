package lyh.bos.service;

import java.util.List;

import lyh.bos.domain.Staff;
import lyh.bos.utils.PageBean;

public interface IStaffService {

    void save(Staff model);

    void pageQuery(PageBean pageBean);

    void delete(String ids);

    void update(Staff model);

    List<Staff> findNotDelete();

    void restore(String ids);

    void search(PageBean pageBean, Staff model);

}
