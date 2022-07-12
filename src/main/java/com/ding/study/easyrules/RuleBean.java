package com.ding.study.easyrules;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 * @date 2022/7/12 15:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleBean {
    /**
     * 活动ID
     */
    private String activityId;
    /**
     *
     */
    private String userId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 任务类型：分享1、实名2：
     */
    private String taskType;
    /**
     * 用户kyc等级
     */
    private String kycLevel;

    /**
     * 用户城市、或用户理财师城市:[北京,上海]
     */
    private String cityName;

    /**
     * 奖品类型:[1积分、2唐圈、3积分商城物品]
     */
    private String rewardType;


    /**
     * 设置KYC地址
     */
    public static final String KYC_S = "9";


    /**
     * 设置KYC方法
     */
    public void setMethondKyc(String userId) {

        if (userId.indexOf(KYC_S) != -1) {
            kycLevel = "A";
        }else {
            kycLevel = "B";
        }

    }

    /**
     * 设置用户地区
     * @param userId
     */
    public void setMethondCityName(String userId) {

        if (userId.indexOf(KYC_S) != -1) {
            cityName = "上海";
        }else {
            cityName ="北京";
        }
    }


    /**
     * 设置用户地区
     * @param userId
     */
    public void setMethondTaskType(String userId) {
        if (userId.indexOf(KYC_S) != -1) {
            taskType = "2";
        }else {

            taskType = "1";
        }
    }

    /**
     * 发奖
     * @param rewardTypeTemp
     */
    public void setMethondRewardType(String rewardTypeTemp) {
        rewardType=rewardTypeTemp+" 号奖品";
    }
}
