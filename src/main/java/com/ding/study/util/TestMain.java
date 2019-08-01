package com.ding.study.util;

import org.codehaus.groovy.util.StringUtil;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author daniel 2019-6-27 0027.
 */
public class TestMain {


    public static void main1(String[] args) throws Exception {
        String str="66_";
        SimpleDateFormat simdf = new SimpleDateFormat("yyyyMMddHHmmss");

        System.out.println("现在时间："+simdf.format(Calendar.getInstance().getTime()));
        String str2="66_1906271056_123456789012345678";

        System.out.println(UUID.randomUUID());
        System.out.println(getRequestId("123456789012345678").length());
        System.out.println(getRequestId("123456789012345678"));




    }

    /**
     *获取请求支付中心的请求ID
     * @param id GUARANTEE_LOAN_RECORD的自增ID
     * @return 66_yyMMddHHmm_表id
     */
    public static String getRequestId(String id){
        if(StringUtils.isEmpty(id)){
            throw new RuntimeException("表自增ID为空");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        return "66_"+sdf.format(Calendar.getInstance().getTime())+"_"+id;
    }

    public static void main(String[] args) throws Exception {
        String str="66_";
        SimpleDateFormat simdf = new SimpleDateFormat("yyyyMMddHHmmss");

        System.out.println("现在时间："+simdf.format(Calendar.getInstance().getTime()));
        String str2="66_1906271056_123456789012345678";
        System.out.println(str2.length());
        System.out.println(UUID.randomUUID());
        System.out.println(getRequestId("123456789012345678").length());
        System.out.println(getRequestId("123456789012345678"));

        for(int i=0;i<32;i++){

            System.out.println("               sec.options["+i+"] = new Option(\""+(i+1)+"日\",\""+(i+1)+"\");");

        }
    }
}
