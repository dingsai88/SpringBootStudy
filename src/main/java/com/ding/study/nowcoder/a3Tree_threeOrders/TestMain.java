package com.ding.study.nowcoder.a3Tree_threeOrders;

import com.ding.study.util.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        TreeNode l = new TreeNode();
        TreeNode r = new TreeNode();
        root.val = 1;
        l.val = 2;
        r.val = 3;
        root.left = l;
        root.right = r;

        //正式开始
        dlr(root);
        ldr(root);
        lrd(root);

        //数据汇总
        int[][] data = { getInt(dlrList),getInt(dlrList),getInt(dlrList)};

        System.out.println(JsonUtils.convertObjToJsonString(data));

        //汇总方式2
        int[][] data2 = { dlrList.stream().mapToInt(Integer::intValue).toArray(),
                ldrList.stream().mapToInt(Integer::intValue).toArray(),
                lrdList.stream().mapToInt(Integer::intValue).toArray()
        };
        System.out.println(JsonUtils.convertObjToJsonString(data2));

    }

    private static List<Integer> dlrList = new ArrayList<>();
    private static List<Integer> ldrList = new ArrayList<>();
    private static List<Integer> lrdList = new ArrayList<>();

    public static int[] getInt(List<Integer> list) {
        int[] dlr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            dlr[i] = list.get(i);
        }
        return dlr;
    }

    public static void lrd(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        dlr(treeNode.left);
        dlr(treeNode.right);
        lrdList.add(treeNode.val);
    }

    public static void ldr(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        dlr(treeNode.left);
        ldrList.add(treeNode.val);
        dlr(treeNode.right);
    }

    public static void dlr(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        dlrList.add(treeNode.val);
        dlr(treeNode.left);
        dlr(treeNode.right);
    }

}
