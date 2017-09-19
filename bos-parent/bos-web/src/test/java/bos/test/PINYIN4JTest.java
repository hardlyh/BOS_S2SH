package bos.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import lyh.bos.utils.PinYin4jUtils;

public class PINYIN4JTest {
    /**
     * pinyin4j 测试
     */
    @Test
    public void test1(){
        String province="河北省";
        String city="石家庄市";
        String district="桥西区";
        
        province=province.substring(0, province.length()-1);
        city=city.substring(0, city.length()-1);
        district=district.substring(0, district.length()-1);
        
        String info=province+city+district;
        
        String[] headByString=PinYin4jUtils.getHeadByString(info);
        String shortCode=StringUtils.join(headByString);
        System.out.println(shortCode);
        
        String cityCode=PinYin4jUtils.hanziToPinyin(city,"");
        System.out.println(cityCode);
    }

}
