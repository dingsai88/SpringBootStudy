package com.ding.study.nowcoder.a20Link_addInList;

import com.ding.study.util.JsonUtils;

public class SolutionLink_addInListError {

    public ListNode addInList (ListNode head1, ListNode head2) {
        // write code here
        String headStr1="";
        String headStr2="";
        while(head1!=null){
            headStr1+=head1.val;
            head1=head1.next;
        }
        while(head2!=null){
            headStr2+=head2.val;
            head2=head2.next;
        }

        Integer headI1=Integer.parseInt(headStr1);
        Integer headI2=Integer.parseInt(headStr2);
        Integer data=headI1+headI2;

        String s= data.toString();
        char [] arr=s.toCharArray();
        ListNode result=new ListNode(Integer.parseInt(String.valueOf(arr[0])));
        ListNode result2=result;
        for(int i=1;i<arr.length;i++){
            int a=Integer.parseInt(String.valueOf(arr[i]));
            result.next=new ListNode(a);

            result=result.next;
        }

        return result2;

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
        System.out.println(JsonUtils.convertObjToJsonString(head1));
        System.out.println(JsonUtils.convertObjToJsonString(head4));
        ListNode result = new SolutionLink_addInListError().addInList(head1, head4);
        System.out.println(JsonUtils.convertObjToJsonString(result));


    }
}
