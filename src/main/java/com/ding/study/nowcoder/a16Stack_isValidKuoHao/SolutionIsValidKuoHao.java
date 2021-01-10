package com.ding.study.nowcoder.a16Stack_isValidKuoHao;

import java.util.Stack;

/**
 * 题目描述
 * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
 * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
 */
public class SolutionIsValidKuoHao {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();

        for (char c : s.toCharArray()) {
            if (c == '[' || c == '(' || c == '{') {
                stack.push(c);
            } else {
                if (stack.empty()) {
                    return false;
                }
                if (c == ']' && stack.pop() != '[') {
                    return false;
                } else if (c == '}' && stack.pop() != '{') {
                    return false;
                } else if (c == ')' && stack.pop() != '(') {
                    return false;
                }
            }
        }
        if (stack.empty()) {
            return true;
        }
        return false;


    }


    public static void main(String[] args) {


        System.out.println(new SolutionIsValidKuoHao().isValid("[](){}"));
        System.out.println(new SolutionIsValidKuoHao().isValid("[(]){}"));
    }


}
