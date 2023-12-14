package com.ding.study.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ding.study.temp.ExcelDataVO;

import com.ding.study.util.AESUtil;
import com.ding.study.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.util.*;

/**
 * 两个有序链表排序：
 */
@Slf4j
public class TestMain {
    public static int isProtocolSupported(String domain, String protocol) {
        try {
            URL url = new URL(protocol + "://" + domain);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            // 如果返回的状态码为 101，则表示该域名支持指定的协议
            return responseCode ;
        } catch (Exception e) {
            e.printStackTrace();
            // 发生异常时，表示该域名不支持指定的协议
            return 0;
        }
    }

    public static void main(String args[]) throws  Exception{

        int result=isProtocolSupported("sobotceshi.yixin.com","wss");
        log.info("测试域名返回状态码 : {}",result);
        result=isProtocolSupported("sobotceshi.yixin.com","wss");
        log.info("生产域名返回状态码 : {}",result);

        if(1!=2){
            return;
        }

        String str="{\n" +
                "\t\"2023CX\": \"2023CX2xxxx\",\n" +
                "\t\"forecastlnfo\": 333,\n" +
                "\t\"istMaxSize\": 1000,\n" +
                "\t\"previousMonthHideDay\": 21,\n" +
                "\t\"listPageSize\": 1\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(str);


        String yearMonthConf = jsonObject.getString("2023CX");

        log.info("2023CX: {}", yearMonthConf);


        String userInputContent = "营销组:1234556789";

        ExcelDataVO [] arry=new ExcelDataVO[10];
        arry[0]=new ExcelDataVO();
        List<ExcelDataVO> list=new ArrayList<>();




        try {
            String chatKey = "营销组:";
            if (userInputContent.indexOf(chatKey) == 0) {
                log.info("TextListenHandler 命中 内容: {} ,key: {}", userInputContent, chatKey);
                String reqData = userInputContent.substring(chatKey.length());
                log.info("TextListenHandler reqData :{} ", reqData);
                //todo ddd

                log.info("TextListenHandler reqData 结束 :{} ", reqData);
            } else {
                log.info("TextListenHandler 未命中 :{} ", userInputContent);
            }
        } catch (Exception e) {
            log.error("chatGPT异常 ", e);
        }


/*

        LinkedList<Integer> linkedList1 = new LinkedList<>();
        LinkedList<Integer> linkedList2 = new LinkedList<>();
        linkedList1.add(1);
        linkedList1.add(5);
        linkedList1.add(7);
        linkedList1.add(8);

        linkedList2.add(2);
        linkedList2.add(4);
        linkedList2.add(6);
        System.out.println(JsonUtils.convertObjToJsonString(sortLink(linkedList1, linkedList2)));

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        LocalDateTime ldt = LocalDateTime.parse("2021-02-08T00:00:00.000+0800",df);
        System.out.println("LocalDateTime转成String类型的时间："+ldt);

*/

        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                System.out.println(getNub(i) + "排" + getNub(j) + "列");
            }
        }

    }


    private static String getNub(int i) {
        if (i == 1) {
            return "一";
        }
        if (i == 2) {
            return "二";
        }
        if (i == 3) {
            return "三";
        }
        if (i == 4) {
            return "四";
        }
        if (i == 5) {
            return "五";
        }
        return "";
    }

    /**
     * 合并排序链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public static LinkedList<Integer> sortLink(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        LinkedList<Integer> all = new LinkedList();
        if (list1 == null && list2 == null) {
            return all;
        }
        all.addAll(list1);
        all.addAll(list2);
        for (int i = 0; i < all.size(); i++) {
            for (int j = i + 1; j < all.size(); j++) {
                if (all.get(i) > all.get(j)) {
                    Integer temp = all.get(j);
                    all.set(j, all.get(i));
                    all.set(i, temp);
                }

            }

        }
        return all;

    }

}
