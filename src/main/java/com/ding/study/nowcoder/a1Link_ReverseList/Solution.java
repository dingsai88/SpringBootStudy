package com.ding.study.nowcoder.a1Link_ReverseList;

/**
 *
 * 新建一个result节点，把head 节点一个一个复制过去。
 * head越来越短.
 *
 * @author daniel 2021-1-4 0004.
 *         int val;
 *         ListNode next = null;
 *         <p>
 *         ListNode(int val) {
 *         this.val = val;
 *         }
 */
public class Solution {
    public static ListNode ReverseList(ListNode head) {
        ListNode result=null;
        ListNode next=null;
        while(head!=null){
            next=head.next;
            head.next=result;
            result=head;
            head=next;
        }

        return   result;
    }


    public static void main(String[] args) {
        ListNode listNode10 = new ListNode(10);
        ListNode listNode9 = new ListNode(9);
        ListNode listNode8 = new ListNode(8);
        ListNode listNode7 = new ListNode(7);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode5 = new ListNode(5);
        listNode10.next = listNode9;
        listNode9.next = listNode8;
        listNode8.next = listNode7;
        listNode7.next = listNode6;
        listNode6.next = listNode5;

        ListNode temp = listNode10;
        while (temp != null) {
            System.out.println("1正常遍历:" + temp.val);
            temp = temp.next;
        }


        System.out.println("--------------:");
        ListNode temp2 = ReverseList(listNode10);
        while (temp2 != null) {
            System.out.println("2反转后遍历:" + temp2.val);
            temp2 = temp2.next;
        }



    }

}
