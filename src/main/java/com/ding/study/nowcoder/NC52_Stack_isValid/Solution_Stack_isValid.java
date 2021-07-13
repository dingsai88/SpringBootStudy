package com.ding.study.nowcoder.NC52_Stack_isValid;

import com.ding.study.nowcoder.a16Stack_isValidKuoHao.SolutionIsValidKuoHao;

import java.util.*;

/**
 * NC52 括号序列
 *
 *描述
 * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
 * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
 *
 *
 *示例1
 * 输入：  "["  、   ([)]
 * 返回值： false
 *
 *
 *输入：  "[]" 、()[]{}
 *回值： true
 *
 *
 * @author daniel 2021-7-13 0013.
 */
public class Solution_Stack_isValid {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c.equals('(') || c.equals('{') || c.equals('[')) {
                stack.push(c);
            } else {
                if (stack.size() == 0) {
                    return false;
                }
                if (c == ')' && !(stack.pop() == '(')) {
                    return false;
                } else if (c == '}' && !(stack.pop() == '{')) {
                    return false;
                } else if (c == ']' && !(stack.pop() == '[')) {
                    return false;
                }

            }
        }
        if (stack.size() == 0) {
            return true;
        }

        return false;

    }


    public static void main(String[] args) {

        System.out.println(new SolutionIsValidKuoHao().isValid("[](){}"));
        //这样不合法，不是你理解的那样，只需要配对就行  :返回false
        System.out.println(new SolutionIsValidKuoHao().isValid("[(]){}"));
    }


}
