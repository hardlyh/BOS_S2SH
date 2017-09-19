package lyh.bos.action;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lyh.bos.action.base.BaseAction;
import lyh.bos.domain.Decidedzone;
import lyh.bos.service.DecidedzoneService;
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{

    @Autowired
    private DecidedzoneService decidedzoneService;
    
    private String [] ids;
    
    public void setIds(String[] ids) {
        this.ids = ids;
    }
    
    public String[] getIds() {
        return ids;
    }
    
    /**
     * 保存定区的方法
     * @return
     */
    public String save(){
        decidedzoneService.save(model,ids);
        return "list";
    }
    
    /**
     * 分页查询
     * @return
     */
    public String pageQuery(){
        DetachedCriteria criteria = pageBean.getCriteria();
        if(StringUtils.isNotBlank(model.getName())){
            criteria.add(Restrictions.like("name", "%" + model.getName() + "%"));
        }
        if(model.getStaff()!=null){
            criteria.createAlias("staff", "s");
            String station = model.getStaff().getStation();
            if (StringUtils.isNotBlank(station)) {
                criteria.add(Restrictions.like("s.station", "%" + station + "%"));
            }
        }
        decidedzoneService.pageQuery(pageBean);
        this.objectToString(pageBean, new String[]{"currentPage","detachedCriteria",
                "pageSize","subareas","decidedzones"});
        return null;
    }
    
    /**
     * 修改定区(不包括关联的分区)
     * @return
     */
    public String update(){
        decidedzoneService.update(model);
        return "list";
    }
    
    /**
     * 根据选择的id删除对应数据 
     * @return
     */
    private String deleteIds;
    public void setDeleteIds(String deleteIds) {
        this.deleteIds = deleteIds;
    }

    public String delete(){
        decidedzoneService.delte(deleteIds);
        return "list";
    }
}
