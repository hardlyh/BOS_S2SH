package lyh.bos.service;

import lyh.bos.domain.User;
import lyh.bos.utils.PageBean;

public interface IUserService {

    User login(User model);


    void editPassword(Integer id, String password);


    void save(User model, String[] roleIds);


    void pageQuery(PageBean pageBean);


    void delete(String ids);




}
