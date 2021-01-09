package com.ding.study.nowcoder.a14detectCycle;

import com.ding.study.util.JsonUtils;

/**
 * https://blog.nowcoder.net/n/c42a259697014745b1688f9c6795d206?f=comment
 */
public class Solution {

    public ListNode detectCycle(ListNode head) {
        ListNode fast=head;
        ListNode slow=head;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            //环内相遇，不一定是环的开头
            if(fast==slow){
                ListNode slow2=head;
                //原速度是2倍，现在相同倍速，能再走一圈就到初始位置
                while(slow!=slow2){
                    slow=slow.next;
                    slow2=slow2.next;
                }
                return slow;
            }

        }

        return null;

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

      //  System.out.println(JsonUtils.convertObjToJsonString(new Solution().detectCycle(head0)));
        head5.next=head3;

        System.out.println(new Solution().detectCycle(head1).val);


    }
}
