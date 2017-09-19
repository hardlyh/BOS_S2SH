package lyh.bos.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import lyh.bos.utils.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 表现层通用实现
 * 
 * @author zhaoqx
 *
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    public static final String HOME = "home";

    protected PageBean pageBean = new PageBean();
    // 模型对象
    protected T model;

    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }

    public void setPage(int page) {
        pageBean.setCurrentPage(page);
    }

    public T getModel() {
        return model;
    }
    
    
    
    public void setModel(T model) {
        this.model = model;
    }

    /**
     * 将对象转化为Json数组并且输出
     */
    public void objectToString(Object o,String [] str){
        
        JsonConfig config = new JsonConfig();
        config.setExcludes(str);
        String json = JSONObject.fromObject(o,config).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().println(json);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void objectToString(List o,String [] str){
        JsonConfig config = new JsonConfig();
        config.setExcludes(str);
        String json =JSONArray.fromObject(o,config).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().println(json);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // 在构造方法中动态获取实体类型，通过反射创建model对象
    public BaseAction() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 获得BaseAction上声明的泛型数组
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> entityClass = (Class<T>) actualTypeArguments[0];
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        pageBean.setCriteria(criteria);
        // 通过反射创建对象
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
