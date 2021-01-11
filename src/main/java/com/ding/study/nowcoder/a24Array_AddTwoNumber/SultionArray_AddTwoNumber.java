package com.ding.study.nowcoder.a24Array_AddTwoNumber;

import com.ding.study.util.JsonUtils;

public class SultionArray_AddTwoNumber {

    public String solve(String s, String t) {
        // write code here
        char[] a1 = s.toCharArray();
        char[] a2 = t.toCharArray();
        int maxLength = Math.max(s.length(), t.length()) + 1;
        char[] result = new char[maxLength];
        int sum = 0;
        for (int i = 1; i < maxLength; i++) {
            if (s.length() - i >= 0 && t.length() - i >= 0) {
                sum += (a1[s.length() - i] - '0') + (a2[t.length() - i] - '0');
            } else if (s.length() - i >= 0) {
                sum += (a1[s.length() - i]) - '0';
            } else if (t.length() - i >= 0) {
                sum += (a1[s.length() - i]) - '0';
            }
            result[maxLength - i] = (char) ((sum % 10) + '0');
            sum = sum / 10;
            if (sum > 0) {
                result[maxLength - i - 1] = (char) (sum + '0');
            }


        }


        return result[0] == 0 ? String.valueOf(result).substring(1) : String.valueOf(result);

    }

    public static void main(String[] args) {
        SultionArray_AddTwoNumber obj = new SultionArray_AddTwoNumber();
Math.sqrt(11);
        System.out.println(JsonUtils.convertObjToJsonString(obj.solve("9999999", null)));

    }
}
