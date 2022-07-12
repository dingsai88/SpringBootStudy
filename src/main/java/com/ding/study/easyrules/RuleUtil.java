package com.ding.study.easyrules;

import com.google.common.base.Strings;

/**
 * @author daniel
 * @date 2022/7/12 16:02
 **/
public class RuleUtil {

    public static final String KYC_S = "9";

    /**
     * 设置用户KYC等级
     * @param userId
     * @return
     */
    public static String getUserKyc(String userId) {
        if (Strings.isNullOrEmpty(userId)) {
            return "A";
        }
        if (userId.indexOf(KYC_S) != -1) {
            return "S";
        }
        return "B";
    }




}
