package lyh.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lyh.bos.dao.IUserDao;
import lyh.bos.domain.Role;
import lyh.bos.domain.User;
import lyh.bos.service.IUserService;
import lyh.bos.utils.MD5Utils;
import lyh.bos.utils.PageBean;

/**
 * 
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    /*
     * 登录
     */
    public User login(User user) {
        String password = MD5Utils.md5(user.getPassword());
        return userDao.findUserByUsernameAndPassword(user.getUsername(), password);

    }

    /*
     * 修改密码
     */
    public void editPassword(Integer id, String password) {
        password = MD5Utils.md5(password);
        userDao.executeUpdate("update User set password=? where id=?", password, id);
    }

    /*
     * 添加一个用户
     */
    public void save(User model, String[] roleIds) {
        String password = model.getPassword();
        password = MD5Utils.md5(password);
        model.setPassword(password);
        if (roleIds != null && roleIds.length > 0) {
            for (String id : roleIds) {
                Role role=new Role();
                role.setId(id);
                model.getRoles().add(role);
            }
        }
        userDao.save(model);

    }

    public void pageQuery(PageBean pageBean) {
        userDao.pageQuery(pageBean);
        
    }

}
