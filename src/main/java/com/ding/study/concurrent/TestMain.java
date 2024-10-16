package com.ding.study.concurrent;

import java.util.HashSet;

/**
 * @author daniel 2019-9-24 0024.
 */
public class TestMain {
    public static void main(String args[]) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <1000; j ++) {
                for (int k = 0; k < 10000; k++) {
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Time spent is " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j <1000; j ++) {
                for (int k = 0; k < 100; k++) {
                }
            }
        }
        end = System.currentTimeMillis();
        System.out.println("Time spent is " + (end - start) + "ms \n\n");



        for (String s:set){
            System.out.println("\n" +
                    "insert into t_integral_invest (ecif_id,integral,operate_type,integral_type,invest_id,product_type,product_name,invest_time,expire_time,integral_status,etl_time)  \n" +
                    " VALUES ("+s+" ,5000,1,16,'ntc:"+s+":1','礼遇积分','礼遇积分','2024-10-16 00:00:00','2025-10-17 00:00:00',1,'2024-10-17 01:11:11');");


        }



    }
    public static HashSet<String> set =new HashSet<>();

    static {
        set.add("1004487244");
        set.add("1004727943");
        set.add("1016619295");
        set.add("1026502433");
        set.add("1002183216");
        set.add("1008650095");
        set.add("1028683372");
        set.add("1002425256");
        set.add("1018711588");
        set.add("1018809378");
        set.add("1016635732");
        set.add("1021481517");
        set.add("1026614409");
        set.add("1017719743");
        set.add("2059969495");
        set.add("2060249973");
        set.add("1001650016");
        set.add("1001676207");
        set.add("1001690937");
        set.add("2060074339");
        set.add("2051995011");
        set.add("2053314929");
        set.add("2053049777");
        set.add("2051226633");
        set.add("2022232061");
        set.add("2056231377");
        set.add("2060296991");
        set.add("2055587207");
        set.add("2006780533");
        set.add("2055968573");
        set.add("2057016093");
        set.add("2029682401");
        set.add("2057450203");
        set.add("2054616621");
        set.add("2059689159");
        set.add("2053062065");
        set.add("2059688077");
        set.add("2054058165");



    }



}
