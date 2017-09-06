package com.fet.crm.osp.platform.core.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class BuzVPosCompareResultPOJO {

    private String compareId;
    private String VPosAuthCode;
    private BigDecimal VPosMoney;
    private BigDecimal VPosNumberPeriods;
    private String VPosRemark;
    private String aimsOrderId;
    private String aimsShippingId;
    private String aimsMsisdn;
    private BigDecimal aimsActualPayment;
    private String compareResult;
    private Date createDate;
    private String createUser;
    private Date updateDate;
    private String updateUser;
    private String aimsAuthCode;

    public String getCompareId() {
        return compareId;
    }

    public void setCompareId(String compareId) {
        this.compareId = compareId;
    }

    public String getVPosAuthCode() {
        return VPosAuthCode;
    }

    public void setVPosAuthCode(String vPosAuthCode) {
        VPosAuthCode = vPosAuthCode;
    }

    public BigDecimal getVPosMoney() {
        return this.VPosMoney;
    }

    public void setVPosMoney(BigDecimal VPosMoney) {
        this.VPosMoney = VPosMoney;
    }

    public BigDecimal getVPosNumberPeriods() {
        return this.VPosNumberPeriods;
    }

    public void setVPosNumberPeriods(BigDecimal VPosNumberPeriods) {
        this.VPosNumberPeriods = VPosNumberPeriods;
    }

    public String getVPosRemark() {
        return this.VPosRemark;
    }

    public void setVPosRemark(String VPosRemark) {
        this.VPosRemark = VPosRemark;
    }

    public String getAimsOrderId() {
        return this.aimsOrderId;
    }

    public void setAimsOrderId(String aimsOrderId) {
        this.aimsOrderId = aimsOrderId;
    }

    public String getAimsShippingId() {
        return this.aimsShippingId;
    }

    public void setAimsShippingId(String aimsShippingId) {
        this.aimsShippingId = aimsShippingId;
    }

    public String getAimsMsisdn() {
        return this.aimsMsisdn;
    }

    public void setAimsMsisdn(String aimsMsisdn) {
        this.aimsMsisdn = aimsMsisdn;
    }

    public BigDecimal getAimsActualPayment() {
        return this.aimsActualPayment;
    }

    public void setAimsActualPayment(BigDecimal aimsActualPayment) {
        this.aimsActualPayment = aimsActualPayment;
    }

    public String getCompareResult() {
        return this.compareResult;
    }

    public void setCompareResult(String compareResult) {
        this.compareResult = compareResult;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getAimsAuthCode() {
        return this.aimsAuthCode;
    }

    public void setAimsAuthCode(String aimsAuthCode) {
        this.aimsAuthCode = aimsAuthCode;
    }

}
