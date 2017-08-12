package lyh.bos.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 封装分页信息
 */
public class PageBean {

    private int currentPage; // 当前页码
    private DetachedCriteria criteria; // 查询条件
    private int pageSize; // 每页显示分页记录数
    private int total;// 总记录数
    private List rows; // 数据集合

    public DetachedCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(DetachedCriteria criteria) {
        this.criteria = criteria;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

}
