package bos.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

public class POITest {

    /**
     * 测试使用POI解析xml
     */
    @Test
    public void test1() throws FileNotFoundException, IOException {
        String path = "F:\\BaiduYunDownload\\BOS-day05\\BOS-day05\\资料\\区域导入测试数据.xls";
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(new File(path)));
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        for(Row row:hssfSheet){
            for(Cell cell:row){
                String value=cell.getStringCellValue();
                System.out.print(value+" "+"1"+"  ");
            }
            System.out.println();
        }

    }

}
