package lyh.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lyh.bos.dao.RoleDao;
import lyh.bos.domain.Function;
import lyh.bos.domain.Role;
import lyh.bos.service.RoleService;
import lyh.bos.utils.PageBean;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    public void save(Role model, String functionIds) {
        if (StringUtils.isNotBlank(functionIds)) {
            String[] ids = functionIds.split(",");
            for (String string : ids) {
                Function fun = new Function();
                fun.setId(string);
                model.getFunctions().add(fun);
            }
        }
        roleDao.save(model);
    }

    /* 
     * 分页查询
     */
    public void pageQuery(PageBean pageBean) {
        roleDao.pageQuery(pageBean);
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }

}
