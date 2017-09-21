package lyh.bos.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lyh.bos.dao.FunctionDao;
import lyh.bos.domain.Function;
import lyh.bos.domain.User;
import lyh.bos.service.FunctionService;
import lyh.bos.utils.BOSUtils;
import lyh.bos.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private FunctionDao functionDao;

    /*
     * 查找全部的权限对象
     */
    public List<Function> findAll() {
        return functionDao.findAll();
    }

    /*
     * 保存权限对象
     */
    public void save(Function model) {
        if (model.getParentFunction() != null && model.getParentFunction().getId().equals("")) {
            model.setParentFunction(null);
        }
        functionDao.save(model);
    }

    /*
     * 分页查询
     */
    public void pageQuery(PageBean pageBean) {
        functionDao.pageQuery(pageBean);
    }

    /*
     * 查询没有父id的对象
     */
    public List<Function> findByZindex() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Function.class);
        criteria.add(Restrictions.isNull("parentFunction"));
        criteria.addOrder(Order.asc("zindex"));
        return functionDao.findByCriteria(criteria);
    }

    /*
     * 根据用户查询对应的权限
     */
    public List<Function> findMenu() {
        List<Function> list = null;
        User user = BOSUtils.getLoginUser();
        if (user.getUsername().equals("admin")) {
            list = functionDao.findAllMenu();
        } else {
            list = functionDao.findMenuById(user.getId());
        }
        return list;
    }

    public void deleteMatch(String ids) {
        if(StringUtils.isNoneBlank(ids)){
            String[] idArr = ids.split(",");
            for (String str : idArr) {
                Function function = functionDao.findById(str);
                function.setParentFunction(null);
                function.setRoles(null);
                function.setChildren(null);
                functionDao.delete(function);
            }
        }
    }

}
