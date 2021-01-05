package com.ding.study.temp;

import com.ding.study.util.JsonUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ding.study.temp.ExcelReader.getStringZhengShu;

/**
 * @author daniel 2020-10-16 0016.
 */
public class ExcelTestMain {


    public static void main(String[] args) {

        String junzhengFileName = "E:\\\\zb.xls";
        // 读取Excel文件内容
        List<ExcelDataVO> junzhengResult = ExcelReader.readExcel(junzhengFileName);
        // System.out.println("最终返回君正:" + JsonUtils.convertObjToJsonString(junzhengResult)+"\n\n 测试");

        int countAll = 0;
        int countSuccess = 0;
        for (ExcelDataVO jun : junzhengResult) {
            countAll++;
            //System.out.println(""+JsonUtils.convertObjToJsonString(jun));
            System.out.println(" update xx set status=8 ,hold_days='" + getStringZhengShu(jun.getData7().replaceAll(" ", "")) + "' ,total_profit='" + jun.getData6() + "'  where  investment_org_id='" + replace(jun.getData2()) + "' ; ");
        }

        System.out.println("最终君正共计银行:" + countAll + "; 找到对应:" + countSuccess);

    }

    public static String replace(String str) {
        String str1 = str.replaceAll(" ", "");
        str1 = str1.replaceAll("\t", "");
        return str1;
    }

    public static void main1(String[] args) {
        // 设定Excel文件所在路径
        // String excelFileName = "E:\\JunZhengLimit.xlsx";
        String excelFileName = "E:\\银行编码.xls";
        // 读取Excel文件内容
        List<ExcelDataVO> readResult = ExcelReader.readExcel(excelFileName);
        System.out.println("最终返回:" + JsonUtils.convertObjToJsonString(readResult));

        //结算的keyICBC; value:{"data1":"工商银行","data2":"ICBC","data3":"0102"}
        Map<String, ExcelDataVO> jieSuanBankCodeMap = new HashMap<>();
        for (ExcelDataVO d : readResult) {
            //key ICBC;
            jieSuanBankCodeMap.put(d.getData2(), d);
        }

        String junzhengFileName = "E:\\\\JunZhengLimit.xlsx";
        // 读取Excel文件内容
        List<ExcelDataVO> junzhengResult = ExcelReader.readExcel(junzhengFileName);
        System.out.println("最终返回君正:" + JsonUtils.convertObjToJsonString(junzhengResult));

        int countAll = 0;
        int countSuccess = 0;
        for (ExcelDataVO jun : junzhengResult) {
            countAll++;

            if (jieSuanBankCodeMap.get(jun.getData2()) == null) {
                continue;
            }
            countSuccess++;
            ExcelDataVO jieSuanBank = jieSuanBankCodeMap.get(jun.getData2());
            // System.out.println(jieSuanBank.getData1()+"最终:" + JsonUtils.convertObjToJsonString(jun) + "   " + jieSuanBank);
            //{"data1":"1","data2":"ICBC","data3":"中国工商银行","data4":"直连普通","data5":"借记卡","data6":"3万","data7":"10万","data8":"50万"}
            // ExcelDataVO{data1='工商银行', data2='ICBC', data3='0102', data4='null', data5='null', data6='null', data7='null', data8='null', data9='null', data10='null'}
            System.out.println("INSERT INTO `bank_limit` ( `bank_code`, `bank_name`, `bank_name_abbr`, `limit_bank_code`, `limit_bank_name`, `single_limit`, `date_limit`, `month_limit`, `single_limit_dk`, `single_limit_kj`, `enable_ok`, `create_time`, `update_time`, `source`) VALUES " +
                    "('1001', '禹州新民生村镇银行', NULL, '" + jieSuanBank.getData3() + "', '" + jieSuanBank.getData1() + "', " + getLimit(jun.getData6()) + ", " + getLimit(jun.getData7()) + ", " + getLimit(jun.getData8()) + ", NULL, NULL, 0, '2020-10-01 10:10:10', '2020-10-01 10:10:10', 2);");
        }

        System.out.println("最终君正共计银行:" + countAll + "; 找到对应:" + countSuccess);

    }


    public static BigDecimal getLimit(String data) {
        try {
            data = data.replaceAll("万", "0000");
            data = data.replaceAll("W", "0000");
            data = data.replaceAll("W", "0000");
            if (data.equals("无限额")) {
                return null;
            }
            return new BigDecimal(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }
}
