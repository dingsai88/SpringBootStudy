package com.ding.study.collection;

import java.util.HashMap;

/**
 * @author daniel 2019-7-30 0030.
 */
public class TestMain {



    public static void main(String[] args) throws Exception {
        //虽然
        HashMap<String,String> hm=new HashMap<>();
        hm.put("1","1");
        hm.put("2","2");
        hm.put("3","3");
        System.out.println(hm.get("2"));
        HashMap<String,String> hm2=(  HashMap<String,String>)hm.clone();
        System.out.println(hm2.get("2"));
        hm2.put("2","22");
        System.out.println(hm2.get("2"));
        System.out.println(hm.get("2"));

    }

}
