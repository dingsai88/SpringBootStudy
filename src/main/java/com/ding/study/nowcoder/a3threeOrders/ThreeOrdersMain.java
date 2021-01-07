package com.ding.study.nowcoder.a3threeOrders;

import com.ding.study.util.JsonUtils;

import java.util.ArrayList;
import java.util.*;

public class ThreeOrdersMain {


    /**
     * @param root TreeNode类 the root of binary tree
     * @return int整型二维数组
     */
    List<Integer> front = new ArrayList<>();
    List<Integer> mid = new ArrayList<>();
    List<Integer> back = new ArrayList<>();


    //前序遍历
    public void PreorderTraversal(TreeNode root) {
        if (root == null)
            return;
        front.add(root.val);
        PreorderTraversal(root.left);
        PreorderTraversal(root.right);
    }

    //中序遍历
    public void InorderTraversal(TreeNode root) {
        if (root == null)
            return;
        InorderTraversal(root.left);
        mid.add(root.val);
        InorderTraversal(root.right);
    }

    //后序遍历
    public void PostorderTraversal(TreeNode root) {
        if (root == null)
            return;
        PostorderTraversal(root.left);
        PostorderTraversal(root.right);
        back.add(root.val);
    }


    public int[][] threeOrders(TreeNode root) {
        // write code here
        PreorderTraversal(root);
        InorderTraversal(root);
        PostorderTraversal(root);

        int[][] res = {
                front.stream().mapToInt(Integer::valueOf).toArray(),
                mid.stream().mapToInt(Integer::valueOf).toArray(),
                back.stream().mapToInt(Integer::valueOf).toArray()
        };

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        TreeNode l = new TreeNode();
        TreeNode r = new TreeNode();
        root.val = 1;
        l.val = 2;
        r.val = 3;
        root.left = l;
        root.right = r;
        ThreeOrdersMain threeOrders = new ThreeOrdersMain();
        int[][] data = threeOrders.threeOrders(root);
        System.out.println(JsonUtils.convertObjToJsonString(data));
    }

}
