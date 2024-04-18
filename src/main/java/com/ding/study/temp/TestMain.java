package com.ding.study.temp;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daniel 2018-8-17 0017.
 */
@Slf4j
public class TestMain {
    public static void main1(String[] args) {

        String[] applyList = {"27784623",
                "27796186",
                "27797240",
                "27797655",
                "27799622",
                "27802245",
                "27803917",
                "27804406",
                "27804978",
                "27804981",
                "27805116",
                "27805123",
                "27805396",
                "27805533",
                "27805961",
                "27805970",
                "27806127",
                "27806147",
                "27806191",
                "27806543",
                "27806594",
                "27806671",
                "27806753",
                "27806782",
                "27806801",
                "27807014",
                "27807087",
                "27807595",
                "27807596",
                "27807889",
                "27807954",
                "27808026",
                "27808346",
                "27808350",
                "27808369",
                "27808390",
                "27808412",
        };
        String[] date = {"2018-08-15 10:37:38",
                "2018-08-15 07:53:14",
                "2018-08-15 15:10:31",
                "2018-08-09 15:58:22",
                "2018-08-16 18:46:29",
                "2018-08-17 11:03:27",
                "2018-08-16 16:10:07",
                "2018-08-15 06:00:59",
                "2018-08-14 19:21:01",
                "2018-08-14 19:24:07",
                "2018-08-14 21:52:20",
                "2018-08-15 18:11:28",
                "2018-08-15 09:37:26",
                "2018-08-15 11:17:47",
                "2018-08-15 13:26:10",
                "2018-08-15 13:26:09",
                "2018-08-15 15:10:42",
                "2018-08-17 12:31:24",
                "2018-08-15 15:10:45",
                "2018-08-15 17:23:19",
                "2018-08-15 18:03:22",
                "2018-08-15 18:43:33",
                "2018-08-16 15:29:57",
                "2018-08-16 15:29:55",
                "2018-08-16 16:14:07",
                "2018-08-16 15:29:56",
                "2018-08-16 16:14:07",
                "2018-08-16 15:29:52",
                "2018-08-16 16:22:10",
                "2018-08-16 15:25:52",
                "2018-08-16 15:29:56",
                "2018-08-16 16:18:10",
                "2018-08-16 18:46:28",
                "2018-08-16 19:06:31",
                "2018-08-16 18:46:01",
                "2018-08-16 18:46:28",
                "2018-08-17 11:12:09"};


        for (int i = 0; i < applyList.length; i++) {
            System.out.println(" update tb_cbm_transport    set STATUS='80' , LOAN_DATE='" + date[i] + "' where YRD_APPLY_ID='" + applyList[i] + "' ;  ");


        }


        for (int i = 0; i < applyList.length; i++) {
            System.out.println(" select YRD_APPLY_ID,STATUS,LOAN_DATE from  tb_cbm_transport  where     YRD_APPLY_ID='" + applyList[i] + "' ;  ");


        }


        TestBean bean = new TestBean();
        bean.setName("aaaa");
        System.out.println(bean.getName());

        try {
            bean = testBean(bean);
        } catch (Exception e) {

        }
        System.out.println(bean.getName());


    }

    public static TestBean testBean(TestBean bean) {
        bean.setName("bbbb");
        if (1 == 1) {
            throw new NullPointerException();
        }
        return bean;

    }

    public static String getContractName(String path) {
        if (path == null || path.trim().equals(""))
            return "";
        else {
            String[] splits = path.split("/");
            String fileName = splits[splits.length - 1];
            return fileName.substring(0, fileName.lastIndexOf("."));
        }
    }

    public static void main(String[] args) {
            log.info(" main {}", test("aa"));

    }

    public static  String test(String i){
        try {
            log.info(" test {}",i);

            try {
                if(1!=2){
                    log.info(" test throw {}",i);
                    throw new Exception("sss");
                }
            } catch (Exception e) {
                log.error("==调用资产中心查询当月收益分配异常！！！", e);
            }


            return i+" success haharesult";
        }catch (Exception e){

        }finally {
            log.info(" finally {}",i);

        }
        return i+" error haharesult";
    }


}
