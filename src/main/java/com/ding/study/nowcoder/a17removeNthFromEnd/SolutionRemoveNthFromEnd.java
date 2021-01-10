package com.ding.study.nowcoder.a17removeNthFromEnd;

import com.ding.study.util.JsonUtils;

public class SolutionRemoveNthFromEnd {


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode count = head;
        ListNode del = head;
        int i = 0;
        /**
         * 统计个数
         */
        while (count != null) {
            count = count.next;
            i++;
        }
        //删除第一个数
        if(i==n){
            del=del.next;
            return del;
        }
        int m = i - n - 1;
        //轮训到要删除数据的前一个数据
        for (int j = 0; j < m; j++) {
            del = del.next;
        }
        //del.next 就是要删除的数据
        del.next = del.next.next;
        return head;
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

        //  System.out.println(JsonUtils.convertObjToJsonString(result));
        //   head5.next=head3;
        System.out.println(JsonUtils.convertObjToJsonString(head0));
        ListNode result = new SolutionRemoveNthFromEnd().removeNthFromEnd(head0, 1);
        System.out.println(JsonUtils.convertObjToJsonString(result));


    }


}
