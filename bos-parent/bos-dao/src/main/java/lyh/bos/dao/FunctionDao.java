package lyh.bos.dao;

import java.util.List;

import lyh.bos.base.dao.IBaseDao;
import lyh.bos.domain.Function;

public interface FunctionDao extends IBaseDao<Function>{

    List<Function> findAllMenu();

    List<Function> findMenuById(Integer id);

    List<Function> findFunctionByUserId(Integer id);

}
