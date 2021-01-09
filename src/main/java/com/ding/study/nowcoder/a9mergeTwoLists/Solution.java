package com.ding.study.nowcoder.a9mergeTwoLists;

import com.ding.study.util.JsonUtils;

public class Solution {


    public static void main(String[] args) {
        ListNode head0 = new ListNode(0);
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head0.next = head1;
        head1.next = head2;

        head3.next = head4;
        head4.next = head5;
        System.out.println(JsonUtils.convertObjToJsonString(new Solution().mergeTwoLists(head0, head3)));


    }

    /**
     * 两个链表融合
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newNode = new ListNode(-1);
        ListNode result = newNode;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                //放入小的，并且下移
                if (l1.val < l2.val) {
                    newNode.next = l1;
                    l1 = l1.next;
                } else {
                    newNode.next = l2;
                    l2 = l2.next;
                }
                newNode = newNode.next;
            } else if (l1 == null) {
                newNode.next = l2;
                l2 = l2.next;
                newNode = newNode.next;
            } else if (l2 == null) {
                newNode.next = l1;
                l1 = l1.next;
                newNode = newNode.next;
            }

        }
        return result.next;
    }
}
