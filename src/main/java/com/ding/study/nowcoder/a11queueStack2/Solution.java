package com.ding.study.nowcoder.a11queueStack2;

import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6?tpId=190&&tqId=35202&rp=1&ru=/ta/job-code-high-rd&qru=/ta/job-code-high-rd/question-ranking
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 *
 * 栈是先进后出；
 * 队列是先进先出
 *
 */
public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.size() <= 0) {
            while (stack1.size() > 0) {
                stack2.push(stack1.pop());
            }

        }
        return stack2.pop();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution=new Solution();
        solution.push(1);
        solution.push(2);
        solution.push(3);
        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());

    }
}
