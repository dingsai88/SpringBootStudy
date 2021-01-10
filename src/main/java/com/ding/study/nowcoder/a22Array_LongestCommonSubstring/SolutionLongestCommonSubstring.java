package com.ding.study.nowcoder.a22Array_LongestCommonSubstring;

import com.ding.study.nowcoder.a21DiGui_Fibonacci.SolutionFibonacci;
import com.ding.study.util.JsonUtils;

/**https://www.nowcoder.com/practice/f33f5adc55f444baa0e0ca87ad8a6aac?tpId=190&&tqId=36002&rp=1&ru=/activity/oj&qru=/ta/job-code-high-rd/question-ranking
 * 题目描述
 * 给定两个字符串str1和str2,输出两个字符串的最长公共子串，如果最长公共子串为空，输出-1。
 */
public class SolutionLongestCommonSubstring {


    /**
     * longest common substring
     *
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    public String LCS(String str1, String str2) {
        // write code here
        if (str1.length() == 0 || str2.length() == 0) return "";
        int[][] arrays = new int[str1.length()][str2.length()];
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        int max = 0;
        int end = 0;
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if (char1[i] == char2[j]) {
                    if (i == 0 || j == 0) {
                        arrays[i][j] = 1;
                    } else {
                        arrays[i][j] = arrays[i - 1][j - 1] + 1;
                    }
                    if (arrays[i][j] > max) {
                        max = arrays[i][j];
                        end = i;
                    }
                }
            }
        }

        if (max == 0) {
            return "-1";
        }
        return str1.substring(end - max + 1, end + 1);
    }


    public static void main(String[] args) {
        SolutionLongestCommonSubstring obj = new SolutionLongestCommonSubstring();

        System.out.println(JsonUtils.convertObjToJsonString(obj.LCS("1234567", "129345")));

    }
}
