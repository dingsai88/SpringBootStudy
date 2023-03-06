package com.ding.study.net;

import com.ding.study.util.AESUtil;
import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URLDecoder;
import java.util.*;

import static com.ding.study.net.HttpClient4Util.CONTENT_TYPE_JSON;

/**
 * kibana es  请求
 *
 * @author daniel 2021-10-15 0015.
 */
@Getter
@Setter
@ToString
class TestBean {
    String key;
    String value;
}

@Slf4j
public class Test {
    public static final String UID_ENC_KEY = "xxxxxx";

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        List<TestBean> list = new ArrayList<>();

        FileReader fr = new FileReader("D:\\DingSai\\data\\chunming.txt");
        BufferedReader bf = new BufferedReader(fr);
        String str;
        // 按行读取字符串
        while ((str = bf.readLine()) != null) {
            if (Strings.isNullOrEmpty(str)) {
                continue;
            }
            try {
              //  log.info("密文:{}   ", str);
                String userStr = AESUtil.aesDecrypt(URLDecoder.decode(str, "UTF-8"), UID_ENC_KEY);
                if(!Strings.isNullOrEmpty(userStr)){

                    System.out.println("'"+userStr+"',");
                }
            } catch (Exception e) {

            }
     /*       String resultStr = getESdata(str);
            resultStr = clearESdata(resultStr);

            TestBean bean=new TestBean();
            bean.setKey(str);
            bean.setValue(resultStr);
            list.add(bean);*/
            //log.info("返回: {}", bean);
        }
        bf.close();
        fr.close();

       /* String resultStr = getESdata("emp_one_dept_name");
        resultStr = clearESdata(resultStr);*/
        System.out.println("\n\n");


        for (TestBean b : list) {
            System.out.println(b.getKey() + " , " + b.getValue());
        }

    }

    /**
     * 访问ES
     *
     * @param tag
     * @return
     */
    public static String getESdata(String tag) {
        String url = "http://tagplatform-kibana.paas.corp/api/console/proxy?path=/_sql?format=txt&method=POST";
        String json = " {\"query\": \"select count(*)  from yxcf_tag_data_20230113 where " + tag + " is not null\"}";
        log.info("开始: {}", json);
        String resultStr = HttpClient4Util.doPostKibana(url, json, CONTENT_TYPE_JSON);
        log.info("返回: {}", resultStr);

        return resultStr;
    }

    /**
     * 清洗ES返回的数据
     *
     * @param resultStr
     * @return
     */
    public static String clearESdata(String resultStr) {
        String data = resultStr.substring(resultStr.indexOf("\n", 30) + 1, resultStr.length() - 1);
        data = data.replaceAll(" ", "");
        return data;
    }


}
