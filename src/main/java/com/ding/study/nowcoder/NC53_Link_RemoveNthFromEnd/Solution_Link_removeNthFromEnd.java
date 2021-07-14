package com.ding.study.nowcoder.NC53_Link_RemoveNthFromEnd;


/**
 *NC53 删除链表的倒数第n个节点
 *
 * 描述
 * 给定一个链表，删除链表的倒数第 nn 个节点并返回链表的头指针
 * 例如，
 * 给出的链表为: 1\to 2\to 3\to 4\to 51→2→3→4→5, n= 2n=2.
 * 删除了链表的倒数第 nn 个节点之后,链表变为1\to 2\to 3\to 51→2→3→5.
 *
 * 备注：
 * 题目保证 nn 一定是有效的
 * 请给出请给出时间复杂度为\ O(n) O(n) 的算法
 *
 *
 * 输入： {1,2},2

 * 返回值： {2}
 *
 * @author daniel 2021-7-14 0014.
 */
public class Solution_Link_removeNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode count = head;
        ListNode del = head;
        int i = 0;
        while (count != null) {
            i++;
            count = count.next;
        }
        if (n == i) {
            return del.next;
        }
        int d = i - n - 1;
        for (int j = 0; j < d; j++) {
            del = del.next;
        }
        del.next = del.next.next;
        return head;
    }
}
