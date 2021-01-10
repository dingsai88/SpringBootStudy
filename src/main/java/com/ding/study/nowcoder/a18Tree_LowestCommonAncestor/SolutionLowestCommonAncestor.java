package com.ding.study.nowcoder.a18Tree_LowestCommonAncestor;

/**
 * 题目描述
 * 给定一棵二叉树以及这棵树上的两个节点 o1 和 o2，请找到 o1 和 o2 的最近公共祖先节点。
 *
 *
 */
public class SolutionLowestCommonAncestor {

    public int lowestCommonAncestor (TreeNode root, int o1, int o2) {
        if(root==null){
            return 0;
        }
        int temp=0;
        if(root.val==o1||root.val==o2){
            temp=1;
        }

        temp+= lowestCommonAncestor(root.left,o1,o2);
        temp+= lowestCommonAncestor(root.right,o1,o2);
        if(temp==2){
            return root.val;
        }
        return temp;
    }
}
