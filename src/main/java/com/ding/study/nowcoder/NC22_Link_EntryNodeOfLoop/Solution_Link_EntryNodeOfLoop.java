package com.ding.study.nowcoder.NC22_Link_EntryNodeOfLoop;


import com.ding.study.util.JsonUtils;

/**
 * NC3 链表中环的入口结点
 *
 *
 * 描述
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，返回null。
 *
 * 输入描述：
 * 输入分为2段，第一段是入环前的链表部分，第二段是链表环的部分，后台将这2个会组装成一个有环或者无环单链表
 * 返回值描述：
 * 返回链表的环的入口结点即可。而我们后台程序会打印这个节点
 *
 *
 *
 *输入：
 * {1,2},{3,4,5}
 * 复制
 * 返回值：
 * 3
 * 复制
 * 说明：
 * 返回环形链表入口节点，我们后台会打印该环形链表入口节点，即3
 *
 *
 *
 *
 * @author daniel 2021-7-9 0009.
 */
public class Solution_Link_EntryNodeOfLoop {

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode fast=pHead;
        ListNode slow=pHead;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){

                ListNode slow2=pHead;
                while (slow2!=slow){
                    slow=slow.next;
                    slow2=slow2.next;
                }
                return slow2;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        //    ListNode listNode10 = new ListNode(10);
        ListNode listNode9 = new ListNode(9);
        ListNode listNode8 = new ListNode(8);
        ListNode listNode7 = new ListNode(7);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode5 = new ListNode(5);
        //   listNode10.next = listNode9;
        listNode9.next = listNode8;
        listNode8.next = listNode7;
        listNode7.next = listNode6;
        listNode6.next = listNode5;
        listNode5.next=listNode7;
       // System.out.println("--------------:"+ JsonUtils.convertObjToJsonString(listNode9));
        System.out.println("--------987657  ------:");
        ListNode temp2 = new Solution_Link_EntryNodeOfLoop().EntryNodeOfLoop(listNode9);
        System.out.println("--------------:222");
       // System.out.println("--------------:"+ JsonUtils.convertObjToJsonString(temp2));
    }




}
