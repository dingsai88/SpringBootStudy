package com.ding.study.datastructure;

/**
 * @author daniel 2019-7-4 0004.
 */
public class GreedyGiveMoney {
    public static void main(String[] args) {
        greedyGiveMoney(12);
    }
    public static void greedyGiveMoney(int money) {
        System.out.println("需要找零: " + money);
        int[] moneyLevel = {1, 5, 10, 20, 50, 100};
        for (int i = moneyLevel.length - 1; i >= 0; i--) {
            int num = money/ moneyLevel[i];
            int mod = money % moneyLevel[i];
            money = mod;
            if (num > 0) {
                System.out.println("需要" + num + "张" + moneyLevel[i] + "块的");
            }
        }
    }
}
