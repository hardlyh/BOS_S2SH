package lyh.bos.service.impl;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;

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

    /* 
     * 根据id来查询
     */
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void test() {
        Role role = roleDao.findById("40283f815e3899ab015e389dba2d0002");
        role.setFunctions(null);
        roleDao.save(role);
    }

    /* 
     * 更新角色的权限信息
     */
    public void update2(Role model,String functionIds) {
        Role role = roleDao.findById(model.getId());
        role.setFunctions(null);
        HashSet set = new HashSet(0);
        role.setFunctions(set);
        if (StringUtils.isNotBlank(functionIds)) {
            String[] ids = functionIds.split(",");
            for (String string : ids) {
                Function fun = new Function();
                fun.setId(string);
                role.getFunctions().add(fun);
            }
        }
        role.setCode(model.getCode());
        role.setName(model.getName());
        role.setDescription(model.getDescription());
    }

}
