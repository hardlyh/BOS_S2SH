package lyh.bos.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import lyh.bos.dao.FunctionDao;
import lyh.bos.dao.IUserDao;
import lyh.bos.domain.Function;
import lyh.bos.domain.User;

// 自定义的Shiro授权方法
public class BOSRealm extends AuthorizingRealm {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private FunctionDao functionDao;

    // 授权方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Function> list = null;
        if (user.getUsername().equals("admin")) {
            list = functionDao.findAll();
        } else {
            list = functionDao.findFunctionByUserId(user.getId());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("staff-list");
        return info;
    }
    // 认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("认证...");
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) arg0;
        String username = passwordToken.getUsername();
        User user = userDao.findByUsername(username);
        if (user == null) {
            return null;
        }
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        return authenticationInfo;
    }

}
