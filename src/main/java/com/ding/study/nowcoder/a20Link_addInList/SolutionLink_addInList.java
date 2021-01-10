package com.ding.study.nowcoder.a20Link_addInList;

import com.ding.study.util.JsonUtils;

/**
 * https://www.nowcoder.com/practice/c56f6c70fb3f4849bc56e33ff2a50b6b?tpId=190&&tqId=35219&rp=1&ru=/ta/job-code-high-rd&qru=/ta/job-code-high-rd/question-ranking
 * 题目描述
 * 假设链表中每一个节点的值都在 0 - 9 之间，那么链表整体就可以代表一个整数。
 * 给定两个这种链表，请生成代表两个整数相加值的结果链表。
 * 例如：链表 1 为 9->3->7，链表 2 为 6->3，最后生成新的结果链表为 1->0->0->0。
 */
public class SolutionLink_addInList {


    public ListNode addInList(ListNode head1, ListNode head2) {
        head1 = reverse(head1);
        head2 = reverse(head2);
        ListNode result1 = new ListNode(-1);
        ListNode result2 = result1;
        int sum = 0;
        while (head1 != null || head2 != null) {
            //最后一个数相加，并且下移一位
            if (head1 != null) {
                sum += head1.val;
                head1 = head1.next;
            }
            if (head2 != null) {
                sum += head2.val;
                head2 = head2.next;
            }
            //相加之和的个位
            result1.next = new ListNode(sum % 10);
            result1 = result1.next;

            //如有有十位就先放入十位
            sum = sum / 10;
            if (sum > 0) {
                result1.next = new ListNode(sum);
            }
        }

        ListNode aa= reverse(result2.next);
        int sum2 = 0;
        return aa;
    }

    /**
     * 反转链表
     *
     * @param root
     * @return
     */
    public ListNode reverse(ListNode root) {
        ListNode pre = null;
        ListNode next = null;
        while (root != null) {
            next = root.next;
            root.next = pre;
            pre = root;
            root = next;
        }
        return pre;
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

        ListNode head4_2 = new ListNode(4);
        ListNode head5_2 = new ListNode(5);
        head4_2.next = head5_2;

        System.out.println(JsonUtils.convertObjToJsonString(head1));
        System.out.println(JsonUtils.convertObjToJsonString(head4));
        SolutionLink_addInList obj=  new SolutionLink_addInList();
      //  ListNode result = obj.addInList(head1, head4);
        System.out.println(JsonUtils.convertObjToJsonString(obj.addInList(head1, head4_2)));


    }
}
