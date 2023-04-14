package com.ding.study.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author daniel
 * @date 2023/4/14 11:14
 **/
public class Study20230414 {
    public static void main(String[] args) {
        Study20230414 temp = new Study20230414();
        String str = "{}()";
        System.out.println(temp.isValid(str));

          str = "{}([)";
        System.out.println(temp.isValid(str));
    }


    private char[] left = {'{', '(', '['};
    private char[] right = {'}', ')', ']'};

    private int charContains(char[] arr, char c) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public boolean isValid(String s) {
        char[] arr = s.toCharArray();
        List<Character> list = new ArrayList<>();
        for (char c : arr) {
            if (charContains(left, c) != -1) {
                list.add(c);
            } else if (charContains(right, c) != -1) {
                if (list.size() == 0) {
                    return false;
                }
                char leftTemp = list.get(list.size() - 1);
                if (charContains(right, c) == charContains(left, leftTemp)) {
                    list.remove(list.size() - 1);
                } else {
                    return false;
                }
            }
        }
        if (list.size() == 0) {
            return true;
        }
        return false;
    }

}
