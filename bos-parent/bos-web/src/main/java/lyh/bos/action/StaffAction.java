package lyh.bos.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lyh.bos.action.base.BaseAction;
import lyh.bos.domain.Staff;
import lyh.bos.service.IStaffService;
import lyh.bos.utils.PageBean;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 取派员action
 */

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
    @Autowired
    private IStaffService staffService;

    private int rows;
    private int page;
    private String ids;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    /**
     * 添加取派员
     * 
     * @return
     */
    public String add() {
        staffService.save(model);
        return null;
    }

    public String pageQuery() throws IOException {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        DetachedCriteria criteria = DetachedCriteria.forClass(Staff.class);
        pageBean.setCriteria(criteria);
        staffService.pageQuery(pageBean);
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"currentPage,pageSize,criteria"});
        String json = JSONObject.fromObject(pageBean,config).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().println(json);

        return null;
    }
    
    /**
     * 删除取派员
     * @return
     */
    public String delete(){
        staffService.delete(ids);
        return "list";
    }
    
    /**
     * 修改取派员信息
     * @return
     */
    public String update(){
        staffService.update(model);
        return "list";
    }
}
