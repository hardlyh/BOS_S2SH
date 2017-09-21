package lyh.bos.service;

import java.util.List;

import lyh.bos.domain.Role;
import lyh.bos.utils.PageBean;

public interface RoleService {

    void save(Role model, String functionIds);

    void pageQuery(PageBean pageBean);

    List<Role> findAll();

    Role findById(String id);

    void test();

    void update2(Role model,String functionIds);

}
