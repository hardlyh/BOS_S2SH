package lyh.bos.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import lyh.bos.domain.User;
import lyh.bos.utils.BOSUtils;

public class BOSLoginInterceptor extends MethodFilterInterceptor{

    @Override
    protected String doIntercept(ActionInvocation arg0) throws Exception {
        User user= BOSUtils.getLoginUser();
        if(user==null){
            return "login";
        }
        return arg0.invoke();
    }

}
