package lyh.bos.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

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
    
    public String test(){
        roleService.test();
        return null;
    }

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
    
    
    private String roleId;
    
    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 根据角色的id查询对应的权限
     * @return
     */
    public String listAjaxById(){
        Role role = roleService.findById(roleId);
        this.objectToString(role, new String[] { "users","roles","children","parentFunction" });
        return null;
    }
    
    /**
     * 将权限对象的信息返回到页面
     * @return
     */
    public String update(){
        Role role = roleService.findById(model.getId());
        ActionContext.getContext().put("id", role.getId());
        ActionContext.getContext().put("code", role.getCode());
        ActionContext.getContext().put("name", role.getName());
        ActionContext.getContext().put("description", role.getDescription());
        return "update2";
    }
    
    /**
     * 更新权限对象的信息
     * @return
     */
    public String update2(){
        roleService.update2(model,functionIds);
        return "list";
    }

}
