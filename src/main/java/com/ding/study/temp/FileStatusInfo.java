package com.ding.study.temp;

import java.util.Date;

/**
 * @author daniel 2019-6-14 0014.
 */
public class FileStatusInfo {
    private Long id;
    /**
     * 本次对应类型请求的ID
     */
    private String requestid;

    /**
     * 本次请求的任务枚举
     */
    private String requestEnum;
    /**
     *
     */
    private String state;
    /**
     *
     */
    private String fileData;

    private Date createDte;

    private Date updateDte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid == null ? null : requestid.trim();
    }

    public String getRequestEnum() {
        return requestEnum;
    }

    public void setRequestEnum(String requestEnum) {
        this.requestEnum = requestEnum == null ? null : requestEnum.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData == null ? null : fileData.trim();
    }

    public Date getCreateDte() {
        return createDte;
    }

    public void setCreateDte(Date createDte) {
        this.createDte = createDte;
    }

    public Date getUpdateDte() {
        return updateDte;
    }

    public void setUpdateDte(Date updateDte) {
        this.updateDte = updateDte;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FileStatusInfo{");
        sb.append("id=").append(id);
        sb.append(", requestid='").append(requestid).append('\'');
        sb.append(", requestEnum='").append(requestEnum).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", fileData='").append(fileData).append('\'');
        sb.append(", createDte=").append(createDte);
        sb.append(", updateDte=").append(updateDte);
        sb.append('}');
        return sb.toString();
    }
}
