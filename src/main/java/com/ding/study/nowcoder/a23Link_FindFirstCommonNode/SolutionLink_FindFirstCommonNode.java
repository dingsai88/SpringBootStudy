package com.ding.study.nowcoder.a23Link_FindFirstCommonNode;

import com.ding.study.util.JsonUtils;

/**
 * https://www.nowcoder.com/practice/6ab1d9a29e88450685099d45c9e31e46?tpId=190&&tqId=35197&rp=1&ru=/activity/oj&qru=/ta/job-code-high-rd/question-ranking
 * 题目描述
 * 输入两个链表，找出它们的第一个公共结点。（注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
 * a.length+b.length=b.length+a.length;
 *
 */
public class SolutionLink_FindFirstCommonNode {


    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }

        ListNode p1 = pHead1;
        ListNode p2 = pHead2;

        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
            if (p1 != p2) {
                if (p1 == null) {
                    p1 = pHead2;
                }
                if (p2 == null) {
                    p2 = pHead1;
                }
            }

        }

        return p1;
    }


    public static void main(String[] args) {
        ListNode head0 = new ListNode(0);
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head0.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;

        ListNode head4_2 = new ListNode(4);
        ListNode head5_2 = new ListNode(5);
        head4_2.next = head5_2;

        System.out.println(JsonUtils.convertObjToJsonString(head1));
        System.out.println(JsonUtils.convertObjToJsonString(head4));
        SolutionLink_FindFirstCommonNode obj = new SolutionLink_FindFirstCommonNode();
        //  ListNode result = obj.addInList(head1, head4);
        System.out.println(JsonUtils.convertObjToJsonString(obj.FindFirstCommonNode(head4, head1)));


    }

}
