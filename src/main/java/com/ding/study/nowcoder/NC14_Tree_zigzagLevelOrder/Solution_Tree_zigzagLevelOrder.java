package com.ding.study.nowcoder.NC14_Tree_zigzagLevelOrder;

import java.util.ArrayList;

/**
 * NC14 二叉树的之字形层序遍历
 * <p>
 * <p>
 * 描述
 * 给定一个二叉树，返回该二叉树的之字形层序遍历，（第一层从左向右，下一层从右向左，一直这样交替）
 * 例如：
 * 给定的二叉树是{3,9,20,#,#,15,7},
 *
 * @author daniel 2021-7-15 0015.
 */
public class Solution_Tree_zigzagLevelOrder {
    private static ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

    /**
     * @param root TreeNode类
     * @return int整型ArrayList<ArrayList <>>
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // write code here
        levelOver(root, 0);
        return result;
    }

    /**
     * 程序 之子遍历
     * @param tree
     * @param level
     */
    public void levelOver(TreeNode tree, int level) {
        if (tree == null) {
            return;
        }
        if (result.size() == level) {
            result.add(new ArrayList<>());
        }
        ArrayList<Integer> data = result.get(level);

        //头插
        if ((level + 1) % 2 == 0) {
            data.add(0, tree.val);
        } else {
            //尾插
            data.add(tree.val);
        }
        levelOver(tree.left, level + 1);
        levelOver(tree.right, level + 1);

    }


}
