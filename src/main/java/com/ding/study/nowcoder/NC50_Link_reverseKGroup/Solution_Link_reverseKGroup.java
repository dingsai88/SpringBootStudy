package com.ding.study.nowcoder.NC50_Link_reverseKGroup;

/**
 * NC50 链表中的节点每k个一组翻转
 * <p>
 * 描述
 * 将给出的链表中的节点每\ k k 个一组翻转，返回翻转后的链表
 * 如果链表中的节点数不是\ k k 的倍数，将最后剩下的节点保持原样
 * 你不能更改节点中的值，只能更改节点本身。
 * 要求空间复杂度 \ O(1) O(1)
 * 例如：
 * 给定的链表是1\to2\to3\to4\to51→2→3→4→5
 * 对于 \ k = 2 k=2, 你应该返回 2\to 1\to 4\to 3\to 52→1→4→3→5
 * 对于 \ k = 3 k=3, 你应该返回 3\to2 \to1 \to 4\to 53→2→1→4→5
 * <p>
 * <p>
 * 示例1
 * 输入：
 * {1,2,3,4,5},2
 * 复制
 * 返回值：
 * {2,1,4,3,5}
 *
 * @author daniel 2021-7-8 0008.
 */
public class Solution_Link_reverseKGroup {

    /**
     * @param head ListNode类
     * @param k    int整型
     * @return ListNode类
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        int i = 1;
        ListNode result = head;
        while (head != null) {
            i++;
            if (i % k == 0 && head.next != null) {
                int t1 = head.val;
                head.val = head.next.val;
                head.next.val = t1;
            }
            head = head.next;
        }
        return result;
    }


    public ListNode reverseKGroup1(ListNode head, int k) {
        //先创建一个哑节点
        ListNode dummy = new ListNode(0);
        //让哑节点的指针指向链表的头
        dummy.next = head;
        //开始反转的前一个节点，比如反转的节点范围是[link1，link2],
        //那么pre就是link1的前一个节点
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null) {
            //每k个反转，end是每k个链表的最后一个
            for (int i = 0; i < k && end != null; i++)
                end = end.next;
            //如果end是空，说明不够k个，就不需要反转了，直接退出循环。
            if (end == null) {
                break;
            }
            //反转开始的节点
            ListNode start = pre.next;
            //next是下一次反转的头结点，先把他记录下来
            ListNode next = end.next;
            //因为end是这k个链表的最后一个结点，把它和原来链表断开，
            //这k个节点我们可以把他们看做一个小的链表，然后反转这个
            //小链表
            end.next = null;
            //因为pre是反转链表的前一个节点，我们把小链表[start,end]
            //反转之后，让pre的指针指向这个反转的小链表
            pre.next = reverse(start);
            //注意经过上一步反转之后，start反转到链表的尾部了，就是已经
            //反转之后的尾结点了，让他之前下一次反转的头结点即可（上面分析
            //过，next就是下一次反转的头结点）
            start.next = next;
            //前面反转完了，要进入下一波了，pre和end都有重新赋值
            pre = start;
            end = start;
        }
        return dummy.next;
    }

    //链表的反转
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
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
        ListNode temp = listNode9;
        while (temp != null) {
            System.out.println("1正常遍历:" + temp.val);
            temp = temp.next;
        }
        System.out.println("--------------:");
        ListNode temp2 = new Solution_Link_reverseKGroup().reverseKGroup(listNode9, 2);
        while (temp2 != null) {
            System.out.println("2反转后遍历:" + temp2.val);
            temp2 = temp2.next;
        }
    }

}
