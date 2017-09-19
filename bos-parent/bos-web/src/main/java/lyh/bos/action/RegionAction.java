package lyh.bos.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lyh.bos.action.base.BaseAction;
import lyh.bos.domain.Region;
import lyh.bos.service.IRegionService;
import lyh.bos.utils.PinYin4jUtils;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

    /**
     * 接受文件域
     */
    private File regionFile;
    
    private String q;
    

    public void setQ(String q) {
        this.q = q;
    }

    @Autowired
    private IRegionService regionService;

    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }

    /**
     * 解析xml文件并且存储到数据库
     */
    public String uploadXml() throws FileNotFoundException, IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(regionFile));
        ArrayList<Region> regions = new ArrayList<Region>();
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        for (Row row : hssfSheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();
            if (StringUtils.isBlank(province) || StringUtils.isBlank(city) || StringUtils.isBlank(district))
                continue;
            Region region = new Region(id, province, city, district, postcode, null, null, null);
            province = province.substring(0, province.length() - 1);
            city = city.substring(0, city.length() - 1);
            district = district.substring(0, district.length() - 1);
            String info = province + city + district;
            String[] headByString = PinYin4jUtils.getHeadByString(info);
            String shortCode = StringUtils.join(headByString);
            String cityCode = PinYin4jUtils.hanziToPinyin(city, "");
            region.setShortcode(shortCode);
            region.setCitycode(cityCode);
            regions.add(region);
        }
        regionService.saveBatch(regions);
        return null;
    }
    
    /**
     * 查询对象
     * @return
     */
    public String pageQuery(){
        regionService.pageQuery(pageBean);
        this.objectToString(pageBean,new String[]{"subareas"});
        return null;
    }
    
    /**
     * 根据条件模糊查询区域
     * @return
     */
    public String listajax(){
        List<Region> list=null; 
        if(StringUtils.isNotBlank(q)){
            list=regionService.findListByQ(q);
        }else{
            list=regionService.findAll();
        }
        this.objectToString(list,new String[]{"subareas"});
        return null;
        
      
    }
    
    /**
     * 保存对象
     * @return
     */
    public String save(){
        regionService.save(model);
        return "list";
    }
    
    private String ids;
    
    public void setIds(String ids) {
        this.ids = ids;
    }

    /**
     * 根据id删除区域
     * @return
     */
    public String delete(){
        regionService.delete(ids);
        return "list";
    }

    
    /**
     * 更改区域
     * @return
     */
    public String update(){
        regionService.update(model);
        return "list";
    }
}
