package com.ding.study.leetcode;

import java.util.Arrays;

/**
 * 爱丽丝有一手（hand）由整数数组给定的牌。
 * <p>
 * 现在她想把牌重新排列成组，使得每个组的大小都是 W，且由 W 张连续的牌组成。
 * <p>
 * 如果她可以完成分组就返回 true，否则返回 false。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：hand = [1,2,3,6,2,3,4,7,8], W = 3
 * 输出：true
 * 解释：爱丽丝的手牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 * 示例 2：
 * <p>
 * 输入：hand = [1,2,3,4,5], W = 4
 * 输出：false
 * 解释：爱丽丝的手牌无法被重新排列成几个大小为 4 的组。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= hand.length <= 10000
 * 0 <= hand[i] <= 10^9
 * 1 <= W <= hand.length
 *
 * @author daniel 2018-12-19 0019.
 */
public class Study846 {

    /**
     *
     * @param hand
     * @param W
     * @return
     */
    public boolean isNStraightHand(int[] hand, int W) {
        int n = hand.length;
        //取模
        if (n % W != 0) {
            return false;
        }
        //排序
        Arrays.sort(hand);
        //护一个vis数组和表示符合规定的顺子数量cnt
        int[] vis = new int[n];
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                int cnt = 1;
                vis[i] = 1;
                int pre = hand[i];
                int j = i + 1;
                while (cnt < W) {
                    //遍历到最后跳出
                    if (j >= n) {
                        break;
                    }
                    //比大于一的数是否存在
                    if (vis[j] == 0 && hand[j] == pre + 1) {
                        cnt++;
                        vis[j] = 1;
                        pre = hand[j];
                    }
                    j++;
                }
                if (cnt != W) {
                    return false;
                }

            }
        }
        return true;
    }

    public static void main(String[] args) {
        Study846 temp = new Study846();
      //  int[] nums={0,1,3,3,4,2,0,1,1,2};

        int[]   nums={1,2,3,9,2,3,4,7,8};
        System.out.println("原始"+Arrays.toString(nums));
        System.out.println(temp.isNStraightHand(nums,3));
        System.out.println("最终"+Arrays.toString(nums));


     //   System.out.println("返回true"+temp.isNStraightHand(nums,2));
    }



}
