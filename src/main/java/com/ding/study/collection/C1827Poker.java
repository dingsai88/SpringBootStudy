package com.ding.study.collection;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author daniel 2019-8-21 0021.
 */
public class C1827Poker {
    public static void main(String[] args) {
        ArrayList<String> color=new ArrayList<>();
        color.add("红桃");
        color.add("黑桃");
        color.add("方块");
        color.add("梅花");
        ArrayList<String> numb=new ArrayList<>();
        numb.add("A");
        numb.add("2");
        numb.add("3");
        numb.add("4");
        numb.add("5");
        numb.add("6");
        numb.add("7");
        numb.add("8");
        numb.add("9");
        numb.add("10");
        numb.add("J");
        numb.add("Q");
        numb.add("K");

        ArrayList<String> poker=new ArrayList();
        for(String s:color){
            for(String n:numb){
                poker.add(s+n);
            }
        }
        poker.add("小王");
        poker.add("大王");
        System.out.println("全部牌:"+poker);
        Collections.shuffle(poker);
        System.out.println("洗牌:"+poker);
        ArrayList<String> dong=new ArrayList();
        ArrayList<String> xi=new ArrayList();
        ArrayList<String> nan=new ArrayList();
        ArrayList<String> dipai=new ArrayList();
       for(int i=0;i<poker.size();i++){
           if(i<4){
               dipai.add(poker.get(i));
           }else if(i%3==0){
               dong.add(poker.get(i));
           }else if(i%2==0){
               xi.add(poker.get(i));
           }else if(i%1==0){
               nan.add(poker.get(i));
           }
       }
        Collections.sort(dipai);
        Collections.sort(dong);
        Collections.sort(xi);
        Collections.sort(nan);
        System.out.println("dipai:"+dipai);
        System.out.println("dong:"+dong);
        System.out.println("xi:"+xi);
        System.out.println("nan:"+nan);
    }
}
