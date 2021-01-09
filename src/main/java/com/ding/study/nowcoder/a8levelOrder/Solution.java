package com.ding.study.nowcoder.a8levelOrder;

import com.ding.study.nowcoder.a7findKth.SolutionErrorPao;

import java.util.*;

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
        count(root, 0);

        return result;
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
