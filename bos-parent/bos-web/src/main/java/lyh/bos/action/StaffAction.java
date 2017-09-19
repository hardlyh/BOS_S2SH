package lyh.bos.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lyh.bos.action.base.BaseAction;
import lyh.bos.domain.Staff;
import lyh.bos.service.IStaffService;

/**
 * 取派员action
 */

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
    @Autowired
    private IStaffService staffService;
    private String ids;

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
        model.setDeltag("0");
        staffService.save(model);
        return "list";
    }

    public String pageQuery() throws IOException {
        if (model != null) {
            DetachedCriteria criteria = pageBean.getCriteria();
            if (StringUtils.isNotBlank(model.getName())) {
                criteria.add(Restrictions.like("name","%"+model.getName()+"%"));
            }
            if (StringUtils.isNotBlank(model.getDeltag())) {
                criteria.add(Restrictions.like("deltag", "%"+model.getDeltag()+"%"));
            }
            if (StringUtils.isNotBlank(model.getTelephone())) {
                criteria.add(Restrictions.like("telephone","%"+model.getTelephone()+"%"));
            }
            if (StringUtils.isNotBlank(model.getStation())) {
                criteria.add(Restrictions.like("station", "%"+model.getStation()+"%"));
            }
        }
        staffService.pageQuery(pageBean);
        this.objectToString(pageBean, new String[] { "decidedzones" });
        return null;
    }

    /**
     * 删除取派员
     * 
     * @return
     */
    public String delete() {
        staffService.delete(ids);
        return "list";
    }

    /**
     * 修改取派员信息
     * 
     * @return
     */
    public String update() {
        staffService.update(model);
        return "list";
    }

    /**
     * 通过ajax返回取派员
     * 
     * @return
     */
    public String listAjax() {
        List<Staff> list = staffService.findNotDelete();
        this.objectToString(list, new String[] { "decidedzones" });
        return null;
    }

    /**
     * 还原取派员
     * 
     * @return
     */
    public String restore() {
        staffService.restore(ids);
        return "list";
    }

    /**
     * 根据条件查找员工
     * 
     * @return
     */
    public String search() {
        staffService.search(pageBean, model);
        this.objectToString(pageBean, new String[] { "decidedzones" });
        return null;
    }

}
