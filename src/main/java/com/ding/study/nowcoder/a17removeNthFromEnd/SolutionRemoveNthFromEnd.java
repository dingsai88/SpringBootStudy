package com.ding.study.nowcoder.a17removeNthFromEnd;

import com.ding.study.util.JsonUtils;

/**
 * https://www.nowcoder.com/practice/f95dcdafbde44b22a6d741baf71653f6?tpId=190&&tqId=35195&rp=1&ru=/ta/job-code-high-rd&qru=/ta/job-code-high-rd/question-ranking
 *
 *删除链表的倒数第n个节点
 * 首先，我们要清楚链表的长度length，这样的话，要删除倒数第n个节点的话，就是删除正数的第（length - n）个节点（从 0 开始遍历链表）（当删除倒数第length的节点时例外，因为我们遍历链表的时候从 0 开始，所以这个时候我们需要单独将条件提出）。
 * 其次，当我们遍历到了要删除的节点的前一位的时候，我们只需要让其的 next 指向其 next.next，这样的话就可以删除该节点。
 * 最后，当我们遍历链表的时候，需要有一个指针指向该链表的首部，这样就不会丢失链表遍历位置之前的元素。
 *
 */
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
