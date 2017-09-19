package lyh.bos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lyh.bos.domain.Function;
import lyh.bos.utils.PageBean;

public interface FunctionService {

    List<Function> findAll();

    void save(Function model);

    void pageQuery(PageBean pageBean);

    List<Function> findByZindex();

    List<Function> findMenu();
    
}
