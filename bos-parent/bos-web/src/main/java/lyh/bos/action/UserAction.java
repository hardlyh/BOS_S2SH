package lyh.bos.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lyh.bos.action.base.BaseAction;
import lyh.bos.domain.User;
import lyh.bos.service.IUserService;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
    // 属性驱动，接收页面输入的验证码
    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Autowired
    private IUserService userService;

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String login() {
        String validateCode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        if (StringUtils.isNotBlank(checkcode) && checkcode.equals(validateCode)) {

            User user = userService.login(model);
            if (user != null) {
                ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
                return HOME;
            } else {
                this.addActionError("输入账号密码错误");
                return LOGIN;
            }

        } else {
            this.addActionError("输入验证码错误");
            return LOGIN;
        }

    }

    public String exit() {
        ServletActionContext.getRequest().getSession().invalidate();
        return LOGIN;
    }
}
