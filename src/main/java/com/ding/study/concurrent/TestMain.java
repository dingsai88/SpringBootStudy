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
        System.out.println("Time spent is正式开始 " + (end - start) + "ms \n\n");



        for (String s:set){
            System.out.println("\n" +
                    "insert into t_integral_invest (ecif_id,integral,operate_type,integral_type,invest_id,product_type,product_name,invest_time,expire_time,integral_status,etl_time)  \n" +
                    " VALUES ("+s+" ,5000,1,16,'ntc:"+s+":1','礼遇积分','礼遇积分','2024-10-16 00:00:00','2025-10-17 00:00:00',0,'2024-10-17 01:11:11');");


        }
        System.out.println("\n\n\n\n");


        for (String s:setUser){
            if(!setAllUser.contains(s)){

                System.out.println("\n" +
                        "insert into t_integral_invest (ecif_id,integral,operate_type,integral_type,invest_id,product_type,product_name,invest_time,expire_time,integral_status,etl_time)  \n" +
                        " VALUES ("+s+" ,5000,1,16,'ntc:"+s+":1','礼遇积分','礼遇积分','2024-10-16 00:00:00','2025-10-17 00:00:00',0,'2024-10-18 01:11:11');");
            }



        }



    }
    public static HashSet<String> set =new HashSet<>();
    public static HashSet<String> setUser =new HashSet<>();

    public static HashSet<String> setAllUser =new HashSet<>();


    static {



        setUser.add("1005318964");
        setUser.add("1020580346");
        setUser.add("1028471020");
        setUser.add("1001721337");
        setUser.add("1001789858");
        setUser.add("1005011515");
        setUser.add("1001694281");
        setUser.add("1001724530");
        setUser.add("2011372557");
        setUser.add("2014487193");
        setUser.add("1001716060");
        setUser.add("1004841257");
        setUser.add("1012383575");
        setUser.add("1020614709");
        setUser.add("2000579242");
        setUser.add("2053489431");
        setUser.add("1014965048");
        setUser.add("1021357749");
        setUser.add("1022558977");
        setUser.add("1029063071");
        setUser.add("2026738027");
        setUser.add("2060214933");
        setUser.add("2060298543");
        setUser.add("2060411837");
        setUser.add("1001751720");
        setUser.add("1021669323");
        setUser.add("1028718859");
        setUser.add("1029765792");
        setUser.add("2020477817");
        setUser.add("1002082731");
        setUser.add("1016289953");
        setUser.add("1018763206");
        setUser.add("1021310725");
        setUser.add("1026168294");
        setUser.add("1001878185");
        setUser.add("1005995547");
        setUser.add("1007047070");
        setUser.add("1015717539");
        setUser.add("1016621044");
        setUser.add("1019372803");
        setUser.add("1028242156");
        setUser.add("1028863598");
        setUser.add("2030213437");
        setUser.add("2055533079");
        setUser.add("1008942223");
        setUser.add("1029314575");
        setUser.add("2058647541");
        setUser.add("2059339299");
        setUser.add("2059807783");
        setUser.add("2060270443");
        setUser.add("1001700180");
        setUser.add("1001722976");
        setUser.add("1015869284");
        setUser.add("1015870173");
        setUser.add("1024000376");
        setUser.add("1027599218");
        setUser.add("1029246951");
        setUser.add("1029717729");
        setUser.add("2059968095");
        setUser.add("1015739987");
        setUser.add("1024230057");
        setUser.add("1026107963");
        setUser.add("1028463047");
        setUser.add("1029097688");
        setUser.add("2025135237");
        setUser.add("2059507483");
        setUser.add("2060411109");
        setUser.add("1017759196");
        setUser.add("1029034142");
        setUser.add("1029264019");
        setUser.add("1029517145");
        setUser.add("2000327571");
        setUser.add("2041950033");
        setUser.add("2045768845");
        setUser.add("2060346393");


        setAllUser.add("1001650016");
        setAllUser.add("1001659118");
        setAllUser.add("1001662826");
        setAllUser.add("1001665368");
        setAllUser.add("1001676207");
        setAllUser.add("1001680520");
        setAllUser.add("1001690937");
        setAllUser.add("1001694281");
        setAllUser.add("1001700180");
        setAllUser.add("1001703404");
        setAllUser.add("1001709746");
        setAllUser.add("1001716060");
        setAllUser.add("1001721337");
        setAllUser.add("1001722976");
        setAllUser.add("1001724530");
        setAllUser.add("1001730782");
        setAllUser.add("1001751720");
        setAllUser.add("1001779698");
        setAllUser.add("1001789858");
        setAllUser.add("1001878185");
        setAllUser.add("1001878984");
        setAllUser.add("1002030387");
        setAllUser.add("1002046550");
        setAllUser.add("1002061599");
        setAllUser.add("1002061644");
        setAllUser.add("1002082731");
        setAllUser.add("1002183216");
        setAllUser.add("1002425256");
        setAllUser.add("1002486520");
        setAllUser.add("1002540754");
        setAllUser.add("1002565748");
        setAllUser.add("1004487244");
        setAllUser.add("1004727943");
        setAllUser.add("1004841257");
        setAllUser.add("1005011515");
        setAllUser.add("1005318989");
        setAllUser.add("1005451835");
        setAllUser.add("1005563688");
        setAllUser.add("1005628870");
        setAllUser.add("1005995547");
        setAllUser.add("1006363042");
        setAllUser.add("1007047070");
        setAllUser.add("1007908153");
        setAllUser.add("1008274136");
        setAllUser.add("1008429498");
        setAllUser.add("1008650095");
        setAllUser.add("1008942223");
        setAllUser.add("1009976588");
        setAllUser.add("1012383575");
        setAllUser.add("1014965048");
        setAllUser.add("1015717539");
        setAllUser.add("1015739987");
        setAllUser.add("1015870173");
        setAllUser.add("1015972845");
        setAllUser.add("1016289953");
        setAllUser.add("1016619295");
        setAllUser.add("1016621044");
        setAllUser.add("1016621282");
        setAllUser.add("1016635732");
        setAllUser.add("1017413386");
        setAllUser.add("1017488441");
        setAllUser.add("1017719743");
        setAllUser.add("1017759196");
        setAllUser.add("1018660014");
        setAllUser.add("1018711588");
        setAllUser.add("1018711958");
        setAllUser.add("1018809378");
        setAllUser.add("1018809904");
        setAllUser.add("1019523301");
        setAllUser.add("1019869147");
        setAllUser.add("1020052816");
        setAllUser.add("1020580346");
        setAllUser.add("1020614709");
        setAllUser.add("1021187607");
        setAllUser.add("1021310725");
        setAllUser.add("1021357749");
        setAllUser.add("1021481517");
        setAllUser.add("1021499470");
        setAllUser.add("1021669323");
        setAllUser.add("1021978555");
        setAllUser.add("1022175391");
        setAllUser.add("1022222650");
        setAllUser.add("1023764709");
        setAllUser.add("1023934206");
        setAllUser.add("1024000376");
        setAllUser.add("1024014319");
        setAllUser.add("1024230057");
        setAllUser.add("1024816287");
        setAllUser.add("1024853280");
        setAllUser.add("1025362755");
        setAllUser.add("1025928759");
        setAllUser.add("1026107963");
        setAllUser.add("1026262021");
        setAllUser.add("1026434156");
        setAllUser.add("1026502433");
        setAllUser.add("1026614409");
        setAllUser.add("1026663600");
        setAllUser.add("1026763632");
        setAllUser.add("1026910013");
        setAllUser.add("1027599218");
        setAllUser.add("1027773967");
        setAllUser.add("1028242156");
        setAllUser.add("1028351263");
        setAllUser.add("1028429927");
        setAllUser.add("1028460389");
        setAllUser.add("1028463047");
        setAllUser.add("1028463452");
        setAllUser.add("1028489083");
        setAllUser.add("1028584152");
        setAllUser.add("1028647681");
        setAllUser.add("1028683372");
        setAllUser.add("1028694912");
        setAllUser.add("1028740532");
        setAllUser.add("1028863598");
        setAllUser.add("1029035413");
        setAllUser.add("1029063071");
        setAllUser.add("1029074453");
        setAllUser.add("1029097688");
        setAllUser.add("1029236182");
        setAllUser.add("1029246827");
        setAllUser.add("1029246951");
        setAllUser.add("1029264019");
        setAllUser.add("1029314575");
        setAllUser.add("1029479173");
        setAllUser.add("1029496063");
        setAllUser.add("1029503611");
        setAllUser.add("1029717729");
        setAllUser.add("1029765792");
        setAllUser.add("2000050327");
        setAllUser.add("2000250397");
        setAllUser.add("2000278500");
        setAllUser.add("2000327571");
        setAllUser.add("2000334718");
        setAllUser.add("2000579242");
        setAllUser.add("2000982263");
        setAllUser.add("2006780533");
        setAllUser.add("2006828375");
        setAllUser.add("2007886551");
        setAllUser.add("2012701501");
        setAllUser.add("2014487193");
        setAllUser.add("2015004309");
        setAllUser.add("2017666737");
        setAllUser.add("2022232061");
        setAllUser.add("2025135237");
        setAllUser.add("2025855509");
        setAllUser.add("2026215799");
        setAllUser.add("2026738027");
        setAllUser.add("2029682401");
        setAllUser.add("2039358493");
        setAllUser.add("2039761981");
        setAllUser.add("2042379735");
        setAllUser.add("2051226633");
        setAllUser.add("2051995011");
        setAllUser.add("2052869537");
        setAllUser.add("2053049777");
        setAllUser.add("2053062065");
        setAllUser.add("2053314929");
        setAllUser.add("2053489431");
        setAllUser.add("2054058165");
        setAllUser.add("2054616621");
        setAllUser.add("2055587207");
        setAllUser.add("2055968573");
        setAllUser.add("2056231377");
        setAllUser.add("2057016093");
        setAllUser.add("2057450203");
        setAllUser.add("2058197383");
        setAllUser.add("2058522843");
        setAllUser.add("2058622349");
        setAllUser.add("2059507483");
        setAllUser.add("2059680841");
        setAllUser.add("2059687759");
        setAllUser.add("2059688077");
        setAllUser.add("2059689159");
        setAllUser.add("2059969495");
        setAllUser.add("2060074339");
        setAllUser.add("2060176697");
        setAllUser.add("2060249973");
        setAllUser.add("2060296991");
        setAllUser.add("2060411109");
        setAllUser.add("2060411837");
        setAllUser.add("2061169631");
        setAllUser.add("2061337429");
        setAllUser.add("2061433713");
        setAllUser.add("2061445879");
        setAllUser.add("2061491551");
        setAllUser.add("2061530767");
        setAllUser.add("2061571527");
        setAllUser.add("2061571771");
        setAllUser.add("2061578939");
        setAllUser.add("2061640983");
        setAllUser.add("2061676601");
        setAllUser.add("2061682429");
        setAllUser.add("2061687235");
        setAllUser.add("2061834847");
        setAllUser.add("2061847159");
        setAllUser.add("2061849569");
        setAllUser.add("2061852915");
        setAllUser.add("2061862029");
        setAllUser.add("2061881057");
        setAllUser.add("2061885815");



    }



}
