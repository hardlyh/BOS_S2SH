package lyh.bos.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lyh.bos.action.base.BaseAction;
import lyh.bos.domain.Role;
import lyh.bos.service.RoleService;

/**
 * 角色管理
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

    private String functionIds;

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    @Autowired
    private RoleService roleService;

    /**
     * 保存角色
     * 
     * @return
     */
    public String save() {
        roleService.save(model, functionIds);
        return "list";
    }

    /**
     * 分页查询
     * 
     * @return
     */
    public String pageQuery() {
        roleService.pageQuery(pageBean);
        this.objectToString(pageBean, new String[] { "functions", "users" });
        return null;
    }

    public String listajax() {
        List<Role> list = roleService.findAll();
        this.objectToString(list, new String[] { "functions", "users" });
        return null;
    }

}
