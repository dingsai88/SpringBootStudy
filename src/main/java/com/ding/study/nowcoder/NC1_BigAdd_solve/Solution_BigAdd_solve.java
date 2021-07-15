package com.ding.study.nowcoder.NC1_BigAdd_solve;

/**
 *NC1 大数加法
 *描述
 * 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
 * （字符串长度不大于100000，保证字符串仅由'0'~'9'这10种字符组成）
 * 示例1
 * 输入： "1","99"

 * 返回值： *"100"
 *
 * 说明：
 * 1+99=100
 *
 * @author daniel 2021-7-15 0015.
 */
public class Solution_BigAdd_solve {

    /**
     *ascii
     * 'a' =65
     * '0' =48
     * '4' =52
     *
     * '4'-'0'  =52-48  = 4 ;
     *
     * @param s
     * @param t
     * @return
     */
    public String solve (String s, String t) {
        int sl=s.length()-1,tl=t.length()-1,temp=0;
        StringBuilder sb=new StringBuilder();
        while(sl>=0||tl>=0||temp>0){
            int i=sl<0?0:s.charAt(sl--)-'0';
            int j=tl<0?0:t.charAt(tl--)-'0';
            int m=i+j+temp;
            sb.append(m%10);
            temp=m/10;
        }
        return sb.reverse().toString();

    }

    /**
     * 解法2：java.math.bigInteger
     * @param s
     * @param t
     * @return
     */
    public String solve2 (String s, String t) {
        return new java.math.BigInteger(s).add(new java.math.BigInteger(t)).toString();
    }








    public static void main(String[] args) {

        //正确
        int k='4'-'0';

        //错误
        int z='4'-0;

        System.out.println(k);
        System.out.println(z);



    }
}
