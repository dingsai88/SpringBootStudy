package com.ding.bean;


import javax.validation.constraints.NotNull;

/**
 * @author daniel 2020-10-26 0026.
 */

public class SendReq {

    /**
     * 用户id
     */
    @NotNull
    private Long userId;

    /**
     * 单号，业务系统唯一
     */
    @NotNull
    private String busId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }
}
