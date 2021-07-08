package com.ding.study.test;

import com.ding.study.util.JsonUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

/**
 * 两个有序链表排序：
 */
public class TestMain {

    public static void main(String args[]) {
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

        for(int i=1;i<6;i++){
            for(int j=1;j<6;j++){
                System.out.println(getNub(i)+"排"+getNub(j)+"列");
            }
        }

    }


    private static String getNub(int i){
        if(i==1){
            return "一";
        }
        if(i==2){
            return "二";
        }
        if(i==3){
            return "三";
        }
        if(i==4){
            return "四";
        }
        if(i==5){
            return "五";
        }
        return "";
    }
    /**
     * 合并排序链表
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
