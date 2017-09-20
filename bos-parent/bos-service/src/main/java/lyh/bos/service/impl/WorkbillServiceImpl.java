package lyh.bos.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lyh.bos.dao.NoticebillDao;
import lyh.bos.dao.WorkbillDao;
import lyh.bos.domain.Workbill;
import lyh.bos.service.WorkbillService;
import lyh.bos.utils.PageBean;

@Service
@Transactional
public class WorkbillServiceImpl implements WorkbillService {

    @Autowired
    private WorkbillDao workbillDao;
    
    @Autowired
    private NoticebillDao noticebillDao;

    public void pageQuery(PageBean pageBean) {
        workbillDao.pageQuery(pageBean);
    }

    @Override
    public void delete(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            String[] arr = ids.split(",");
            for (String str : arr) {
                Workbill obj = workbillDao.findById(str);
                noticebillDao.deleteByid(obj.getNoticebill().getId());
                workbillDao.deleteByid(str);
            }
        }
        
    }

}
