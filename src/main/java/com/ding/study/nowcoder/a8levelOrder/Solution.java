package com.ding.study.nowcoder.a8levelOrder;

import java.util.*;

/**
 * 题目描述
 * 给定一个二叉树，返回该二叉树层序遍历的结果，（从左到右，一层一层地遍历）
 * 例如：
 * 给定的二叉树是{3,9,20,#,#,15,7},
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        TreeNode l = new TreeNode();
        TreeNode r = new TreeNode();
        root.val = 1;
        l.val = 2;
        r.val = 3;
        root.left = l;
        root.right = r;

        System.out.println(new Solution().levelOrder(root));

    }

    /**
     * @param root TreeNode类
     * @return int整型ArrayList<ArrayList <>>
     */
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // write code here
        if (root == null) {
            return result;
        }
        count2(root, 0);
        return result;
    }

    public void count2(TreeNode root,Integer i){
        if(result.size()==i){
            result.add(new ArrayList<>());
        }
        ArrayList data=result.get(i);
        data.add(root.val);
        if(root.left!=null){
            count2(root.left,i+1);
        }

        if(root.right!=null){
            count2(root.right,i+1);
        }

    }










    public void count(TreeNode root, int level) {
        if (level == result.size()) {
            result.add(new ArrayList<Integer>());
        }

        ArrayList<Integer> list = result.get(level);
        list.add(root.val);
        if (root.left != null) {
            count(root.left, level + 1);
        }
        if (root.right != null) {
            count(root.right, level + 1);
        }


    }

}
