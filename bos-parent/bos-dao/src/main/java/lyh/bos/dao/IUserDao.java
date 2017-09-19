package lyh.bos.dao;

import lyh.bos.base.dao.IBaseDao;
import lyh.bos.domain.User;

public interface IUserDao extends IBaseDao<User> {

	public User findUserByUsernameAndPassword(String username, String password);

    public User findByUsername(String username);

}
