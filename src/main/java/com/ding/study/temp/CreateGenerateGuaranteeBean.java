package com.ding.study.temp;

import java.util.Map;

/**
 * @author daniel 2019-6-14 0014.
 */
public class CreateGenerateGuaranteeBean {

    /**
     * 文件路径
     */
    private   String fileURI;

    /**
     * 需要替换的模版
     */
    private Map<String, Object> paraMap;

    /**
     * 文件名
     */
    private String finalPdfFileName;

    /**
     *
     */
    private Long  treatyId;

    public String getFileURI() {
        return fileURI;
    }

    public void setFileURI(String fileURI) {
        this.fileURI = fileURI;
    }

    public Map<String, Object> getParaMap() {
        return paraMap;
    }

    public void setParaMap(Map<String, Object> paraMap) {
        this.paraMap = paraMap;
    }

    public String getFinalPdfFileName() {
        return finalPdfFileName;
    }

    public void setFinalPdfFileName(String finalPdfFileName) {
        this.finalPdfFileName = finalPdfFileName;
    }

    public Long getTreatyId() {
        return treatyId;
    }

    public void setTreatyId(Long treatyId) {
        this.treatyId = treatyId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateGenerateGuaranteeBean{");
        sb.append("fileURI='").append(fileURI).append('\'');
        sb.append(", paraMap=").append(paraMap);
        sb.append(", finalPdfFileName='").append(finalPdfFileName).append('\'');
        sb.append(", treatyId=").append(treatyId);
        sb.append('}');
        return sb.toString();
    }
}
