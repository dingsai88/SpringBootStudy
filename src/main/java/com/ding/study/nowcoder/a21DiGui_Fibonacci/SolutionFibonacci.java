package com.ding.study.nowcoder.a21DiGui_Fibonacci;

import com.ding.study.util.JsonUtils;

/**
 * https://www.nowcoder.com/practice/c6c7742f5ba7442aada113136ddea0c3?tpId=190&&tqId=35364&rp=1&ru=/ta/job-code-high-rd&qru=/ta/job-code-high-rd/question-ranking
 *题目描述
 *大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0，第1项是1）。
 * n\leq 39n≤39
 *
 */
public class SolutionFibonacci {

    public int Fibonacci(int n) {
        if(n==0){
            return 0;
        }else  if(n==1){
            return 1;
        }else  if(n==2){
            return 1;
        }
        return Fibonacci(n-1)+Fibonacci(n-2);
    }
    public static void main(String[] args) {
        SolutionFibonacci obj=new SolutionFibonacci();
        obj.Fibonacci(5);
        System.out.println(JsonUtils.convertObjToJsonString(obj.Fibonacci(10)));

    }

}
