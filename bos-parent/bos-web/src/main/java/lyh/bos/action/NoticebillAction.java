package lyh.bos.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lyh.bos.action.base.BaseAction;
import lyh.bos.domain.Noticebill;
import lyh.bos.service.NoticebillService;
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill>{
    
    @Autowired
    private NoticebillService noticebillService;
    
    /**
     * 保存业务对象 
     * @return
     */
    public String saveNoticebill(){
        noticebillService.save(model);
        return null;
    }

}
