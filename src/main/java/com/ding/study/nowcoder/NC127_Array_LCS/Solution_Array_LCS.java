package com.ding.study.nowcoder.NC127_Array_LCS;

/**
 * NC127 最长公共子串
 *
 *描述
 * 给定两个字符串str1和str2,输出两个字符串的最长公共子串
 * 题目保证str1和str2的最长公共子串存在且唯一。
 * 示例1
 *
 输入："1AB2345CD","12345EF"

 * 返回值： "2345"
 *
 *
 * 思路:数组遍历，初始值是1，往后累加
 *
 * @author daniel 2021-7-15 0015.
 */
public class Solution_Array_LCS {
    /**
     * longest common substring
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    public String LCS (String str1, String str2) {
        int [][] array=new int[str1.length()][str2.length()];
        char [] c1=str1.toCharArray();
        char []c2= str2.toCharArray();
        int max=0;
        int end=0;
        for(int i=0;i<str1.length();i++){
            for(int j=0;j<str2.length();j++){
                if(c1[i]==c2[j]){
                    //这里要注意，一个成立就走
                    if(i==0||j==0){
                        array[i][j]=1;
                    }else {
                        array[i][j]=array[i-1][j-1]+1;
                    }
                    if (array[i][j] > max) {
                        max = array[i][j];
                        end = i;
                    }
                }
            }
        }
        if (max == 0) {
            return "-1";
        }
          return str1.substring(end-max+1,end+1);
    }



}
