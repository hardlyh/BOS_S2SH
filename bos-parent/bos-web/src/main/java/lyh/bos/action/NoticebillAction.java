package lyh.bos.action;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lyh.bos.action.base.BaseAction;
import lyh.bos.domain.Noticebill;
import lyh.bos.service.NoticebillService;

@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {

    @Autowired
    private NoticebillService noticebillService;

    /**
     * 保存业务对象
     * 
     * @return
     */
    public String saveNoticebill() {
        noticebillService.save(model);
        return "list";
    }
    
    /*
     * public String pageQuery() throws IOException {
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
     * 
     * 
     * */
    
    public String findnoassociations(){
        DetachedCriteria criteria = pageBean.getCriteria();
        criteria.add(Restrictions.isNull("staff"));
        noticebillService.pageQuery(pageBean);
        this.objectToString(pageBean, new String[] {"workbills","user","decidedzones"});
        return null;
    }
    
    public String dispatch(){
        noticebillService.dispatch(model);
        return "disList";
    }

}
