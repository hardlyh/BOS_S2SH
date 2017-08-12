package lyh.bos.service;

import lyh.bos.domain.User;

public interface IUserService {

    User login(User model);

    void editPassword(Long id, String password);


}
