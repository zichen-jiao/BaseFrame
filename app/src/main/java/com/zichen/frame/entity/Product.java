package com.zichen.frame.entity;

/**
 * author : lyw
 * time : 2017/9/11
 * desc :
 * version: 1.0
 */
public class Product {

    /**
     * 类别（0：全部 1：业绩比较基准类 2：浮动收益类）
     */
    private String type;
    /**
     * 标题
     */
    private String title;
    /**
     * 募集开始时间
     */
    private String startTime;
    /**
     * 募集结束时间
     */
    private String endTime;
    /**
     * 状态
     */
    private String status;
    /**
     * 风险评级
     */
    private String riskAppraisal;
    /**
     * 投资起点
     */
    private String investStart;
    /**
     * 业绩比较基准
     */
    private String compref;
    /**
     * 产品期限
     */
    private String termCount;
    /**
     * 单位净值
     */
    private String unitNet;
    /**
     * 产品id
     */
    private String id;

    /**
     * 是否推荐  1:推荐
     */
    private String stickyCode;

    /**
     * @return 类别（0：全部 1：业绩比较基准类 2：浮动收益类）
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return 标题
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return 募集开始时间
     */
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return 募集结束时间
     */
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return 状态
     */
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return 风险评级
     */
    public String getRiskAppraisal() {
        return riskAppraisal;
    }

    public void setRiskAppraisal(String riskAppraisal) {
        this.riskAppraisal = riskAppraisal;
    }

    /**
     * @return 投资起点
     */
    public String getInvestStart() {
        return investStart;
    }

    public void setInvestStart(String investStart) {
        this.investStart = investStart;
    }

    /**
     * @return 业绩比较基准
     */
    public String getCompref() {
        return compref;
    }

    public void setCompref(String compref) {
        this.compref = compref;
    }

    /**
     * @return 产品期限
     */
    public String getTermCount() {
        return termCount;
    }

    public void setTermCount(String termCount) {
        this.termCount = termCount;
    }

    /**
     * @return 单位净值
     */
    public String getUnitNet() {
        return unitNet;
    }

    public void setUnitNet(String unitNet) {
        this.unitNet = unitNet;
    }

    /**
     * @return 产品id
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStickyCode() {
        return stickyCode;
    }

    public void setStickyCode(String stickyCode) {
        this.stickyCode = stickyCode;
    }

    public boolean isRecommend() {
        return "1".equals(stickyCode);
    }

    @Override
    public String toString() {
        return "Product{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status='" + status + '\'' +
                ", riskAppraisal='" + riskAppraisal + '\'' +
                ", investStart='" + investStart + '\'' +
                ", compref='" + compref + '\'' +
                ", termCount='" + termCount + '\'' +
                ", unitNet='" + unitNet + '\'' +
                ", id='" + id + '\'' +
                ", stickyCode='" + stickyCode + '\'' +
                '}';
    }
}
