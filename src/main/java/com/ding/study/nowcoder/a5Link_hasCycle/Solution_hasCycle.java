package com.ding.study.nowcoder.a5Link_hasCycle;

import com.ding.study.util.JsonUtils;

/**
 * NC4 判断链表中是否有环
 *
 *判断给定的链表中是否有环。如果有环则返回true，否则返回false。
 * 你能给出空间复杂度的解法么？
 * 输入分为2部分，第一部分为链表，第二部分代表是否有环，然后回组成head头结点传入到函数里面。-1代表无环，其他的数字代表有环，这些参数解释仅仅是为了方便读者自测调试
 *
 *
 *
 */
public class Solution_hasCycle {

    public boolean hasCycle(ListNode head) {
        ListNode faster=head;
        ListNode lower=head;
        while(faster!=null&&faster.next!=null){
            faster=faster.next.next;
            lower=lower.next;
            if(lower==faster){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode head0=new ListNode(0);
        ListNode head1=new ListNode(1);
        ListNode head2=new ListNode(2);
        ListNode head3=new ListNode(3);
        ListNode head4=new ListNode(4);
        ListNode head5=new ListNode(5);
        head0.next=head1;
        head1.next=head2;
        head2.next=head3;
        head3.next=head4;
        head4.next=head5;
         System.out.println(JsonUtils.convertObjToJsonString(new Solution_hasCycle().hasCycle(head0)));

        head5.next=head0;

        System.out.println(JsonUtils.convertObjToJsonString(new Solution_hasCycle().hasCycle(head0)));
    }

}
