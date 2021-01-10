package com.ding.study.nowcoder.a5Link_hasCycle;

import com.ding.study.util.JsonUtils;

public class Solution {

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
         System.out.println(JsonUtils.convertObjToJsonString(new Solution().hasCycle(head0)));

        head5.next=head0;

        System.out.println(JsonUtils.convertObjToJsonString(new Solution().hasCycle(head0)));
    }

}
