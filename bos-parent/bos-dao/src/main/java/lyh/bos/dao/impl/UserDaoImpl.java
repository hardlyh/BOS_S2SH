package lyh.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import lyh.bos.base.dao.impl.BaseDaoImpl;
import lyh.bos.dao.IUserDao;
import lyh.bos.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
	/**
	 * 根据用户名和密码查询用户
	 */
	public User findUserByUsernameAndPassword(String username, String password) {
		String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username,password);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

    @Override
    public User findByUsername(String username) {
        String hql = "FROM User u WHERE u.username = ?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
