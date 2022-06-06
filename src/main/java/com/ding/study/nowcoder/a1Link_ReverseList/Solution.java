package com.ding.study.nowcoder.a1Link_ReverseList;

/**
 *
 * 新建一个result节点，把head 节点一个一个复制过去。
 * head越来越短.
 *
 * @author daniel 2021-1-4 0004.
 */
public class Solution {
    /**
     *
     * @param head
     * @return
     */
    public static ListNode ReverseList(ListNode head) {
        //初始化pre指针，用于记录当前结点的前一个结点地址
        ListNode pre = null;
        //初始化p指针，用于记录当前结点的下一个结点地址
        ListNode p = null;
        //head指向null时，循环终止。
        while(head != null){
            //先用p指针记录当前结点的下一个结点地址。
            p = head.next;
            //让被当前结点与链表断开并指向前一个结点pre。
            head.next = pre;
            //pre指针指向当前结点
            pre = head;
            //head指向p(保存着原链表中head的下一个结点地址)
            head = p;
        }
      //当循环结束时,pre所指的就是反转链表的头结点
        return pre;
    }

    /**
     * 测试方法
     * @param args
     */
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
            System.out.println("正常遍历:" + temp.val);
            temp = temp.next;
        }

        System.out.println("--------------:");
        ListNode temp2 = ReverseList(listNode10);
        while (temp2 != null) {
            System.out.println("反转后遍历:" + temp2.val);
            temp2 = temp2.next;
        }
    }

}
