package lyh.bos.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
            if(StringUtils.isBlank(province) || StringUtils.isBlank(city)
                    || StringUtils.isBlank(district) )
                continue;
            province = province.substring(0, province.length() - 1);
            city = city.substring(0, city.length() - 1);
            district = district.substring(0, district.length() - 1);
            String info = province + city + district;
            String[] headByString = PinYin4jUtils.getHeadByString(info);
            String shortCode = StringUtils.join(headByString);
            String cityCode = PinYin4jUtils.hanziToPinyin(city, "");
            Region region = new Region(id, province, city, district, postcode, shortCode, cityCode, null);
            regions.add(region);
        }
        regionService.saveBatch(regions);
        return null;
    }

}
