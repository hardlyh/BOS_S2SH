package lyh.bos.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lyh.bos.action.base.BaseAction;
import lyh.bos.domain.Region;
import lyh.bos.domain.Subarea;
import lyh.bos.service.ISubareaService;
import lyh.bos.utils.FileUtils;
import net.sf.json.JSONArray;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

    @Autowired
    private ISubareaService subareaService;

    /**
     * 保存分区对象
     * 
     * @return
     */
    public String save() {
        subareaService.save(model);
        return "list";
    }

    /**
     * 查询所有分区信息
     * 
     * @return
     */
    public String findAll() {
        List<Subarea> list = subareaService.finAll();
        String str = JSONArray.fromObject(list).toString();
        System.out.println(str);
        return null;
    }

    /**
     * 分页条件查询
     * 
     * @return
     */
    public String pageQuery() {
        DetachedCriteria dc = pageBean.getCriteria();
        String addresskey = model.getAddresskey();
        if (StringUtils.isNotBlank(addresskey)) {
            dc.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
        }
        Region region = model.getRegion();
        if (region != null) {
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            dc.createAlias("region", "r");
            if (StringUtils.isNotBlank(province)) {
                dc.add(Restrictions.like("r.province", "%" + province + "%"));
            }
            if (StringUtils.isNotBlank(city)) {
                dc.add(Restrictions.like("r.city", "%" + city + "%"));
            }
            if (StringUtils.isNotBlank(district)) {
                dc.add(Restrictions.like("r.district", "%" + district + "%"));
            }
        }
        subareaService.pageQuery(pageBean);
        this.objectToString(pageBean,
                new String[] { "currentPage", "detachedCriteria", "pageSize", "decidedzone", "subareas" });
        return null;
    }

    /**
     * 分区数据导出
     * 
     * @return
     * @throws IOException
     */
    public String exportXls() throws IOException {
        List<Subarea> list = subareaService.finAll();
        // 内存创建一个excel
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        // 创建一个标签页
        HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");
        // 创建标题
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("分区编号");
        row.createCell(1).setCellValue("开始编号");
        row.createCell(2).setCellValue("结束编号");
        row.createCell(3).setCellValue("位置信息");
        row.createCell(4).setCellValue("省市区");
        // 写入数据
        for (Subarea subarea : list) {
            HSSFRow rowDate = sheet.createRow(sheet.getLastRowNum() + 1);
            rowDate.createCell(0).setCellValue(subarea.getId());
            rowDate.createCell(1).setCellValue(subarea.getStartnum());
            rowDate.createCell(2).setCellValue(subarea.getEndnum());
            rowDate.createCell(3).setCellValue(subarea.getPosition());
            rowDate.createCell(4).setCellValue(subarea.getRegion().getName());
        }
        String filename = "分区数据.xls";
        // 获取文件后缀对应的编码
        ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
        String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
        // 设置类型
        ServletActionContext.getResponse().setContentType(mimeType);
        String agent = ServletActionContext.getRequest().getHeader("User-Agent");
        filename = FileUtils.encodeDownloadFilename(filename, agent);
        ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + filename);
        hssfWorkbook.write(outputStream);

        return null;
    }

    /**
     * 查询所有未关联的分区
     * 
     * @return
     */
    public String listAjax() {
        List<Subarea> list = subareaService.findNotAssocation();
        this.objectToString(list, new String[] { "decidedzone", "region" });
        return null;
    }

    private String deciId;

    public void setDeciId(String deciId) {
        this.deciId = deciId;
    }

    /**
     * 通过定去id查询所关联的分区
     * 
     * @return
     */
    public String findByDecededzoneId() {
        List<Subarea> list = subareaService.findByDecededzoneId(deciId);
        this.objectToString(list, new String[] { "decidedzone", "subareas" });
        return null;
    }

    /**
     * 区域分布图
     * 
     * @return
     */
    public String findSubareasGroupByProvince() {
        List<Object> list = subareaService.findSubareasGroupByProvince();
        this.objectToString(list, new String[] {});
        return null;
    }

    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    /**
     * 删除用户
     * 
     * @return
     */
    public String delete() {
        subareaService.delete(ids);
        return "list";
    }

    /**
     * 更新
     * 
     * @return
     */
    public String update() {
        subareaService.update(model);
        return "list";
    }

}
