package lyh.bos.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lyh.bos.action.base.BaseAction;
import lyh.bos.domain.Function;
import lyh.bos.service.FunctionService;

/**
 * 权限管理
 */
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

    @Autowired
    private FunctionService functionService;

    /**
     * 返回Function数据(json)
     * 
     * @return
     */
    public String listajax() {
        List<Function> list = functionService.findAll();
        this.objectToString(list, new String[] { "roles", "parentFunction" });
        return null;
    }

    /**
     * 添加权限
     * 
     * @return
     */
    public String save() {
        functionService.save(model);
        return null;
    }

    /**
     * 分页查询
     * 
     * @return
     */
    public String pageQuqey() {
        String page = model.getPage();
        this.setPage(Integer.parseInt(page));
        functionService.pageQuery(pageBean);
        this.objectToString(pageBean, new String[] { "roles", "children", "parentFunction" });
        return null;
    }

    /**
     * 查询没有父id的对象
     * 
     * @return
     */
    public String findByZindex() {
        List<Function> list = functionService.findByZindex();
        this.objectToString(list, new String[] { "roles", "parentFunction" });
        return null;
    }

    /**
     * 根据登陆用户查询该用户的权限
     * 
     * @return
     */
    public String findMenu() {
        List<Function> functions = functionService.findMenu();
        this.objectToString(functions, new String[] { "roles", "parentFunction","children"});
        return null;
    }
}
