package lyh.bos.action;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lyh.bos.action.base.BaseAction;
import lyh.bos.domain.Workbill;
import lyh.bos.service.WorkbillService;

@Scope("prototype")
@Controller
public class WorkbillAction extends BaseAction<Workbill> {

    @Autowired
    public WorkbillService workbillService;

    public String pageQuery() {
        DetachedCriteria criteria = pageBean.getCriteria();
        criteria.add(Restrictions.isNotNull("staff"));
        if (model != null) {
           
            if (model.getNoticebill() != null) {
                criteria.createAlias("noticebill", "n");
                criteria.add(Restrictions.like("n.telephone", "%" + model.getNoticebill().getTelephone() + "%"));
            }
            if (model.getStaff() != null) {
                criteria.createAlias("staff", "s");
                criteria.add(Restrictions.like("s.name", "%" + model.getStaff().getName() + "%"));
            }
        }
        workbillService.pageQuery(pageBean);
        this.objectToString(pageBean, new String[] { "workbills", "user", "decidedzones" });

        return null;
    }

    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String delete() {
        workbillService.delete(ids);
        return "list";
    }

}
