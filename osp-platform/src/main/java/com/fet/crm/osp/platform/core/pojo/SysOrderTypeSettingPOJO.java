package com.fet.crm.osp.platform.core.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SysOrderTypeSetting POJO物件
 * 
 * @author AndrewLee
 *
 */
public class SysOrderTypeSettingPOJO {

    private String orderTypeId;
    private String orderTypeName;
    private BigDecimal successSec;
    private BigDecimal failSec;
    private BigDecimal regularTime;
    private BigDecimal overtime;
    private BigDecimal criticalCounts;
    private BigDecimal overtimeCounts;
    private String email;
    private String btnCreateSingle;
    private String btnCreateBatch;
    private Date createDate;
    private String createUser;
    private Date updateDate;
    private String updateUser;
    private String btnNotice;
    private String kpiDayType;
    private String chkCreateDate;
    private String isRegularTime;
    private String beforeCreateDate;
    private String startCountTime;

    public String getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(String orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public BigDecimal getSuccessSec() {
        return successSec;
    }

    public void setSuccessSec(BigDecimal successSec) {
        this.successSec = successSec;
    }

    public BigDecimal getFailSec() {
        return failSec;
    }

    public void setFailSec(BigDecimal failSec) {
        this.failSec = failSec;
    }

    public BigDecimal getOvertime() {
        return overtime;
    }

    public void setOvertime(BigDecimal overtime) {
        this.overtime = overtime;
    }

    public BigDecimal getCriticalCounts() {
        return criticalCounts;
    }

    public void setCriticalCounts(BigDecimal criticalCounts) {
        this.criticalCounts = criticalCounts;
    }

    public BigDecimal getOvertimeCounts() {
        return overtimeCounts;
    }

    public void setOvertimeCounts(BigDecimal overtimeCounts) {
        this.overtimeCounts = overtimeCounts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBtnCreateSingle() {
        return btnCreateSingle;
    }

    public void setBtnCreateSingle(String btnCreateSingle) {
        this.btnCreateSingle = btnCreateSingle;
    }

    public String getBtnCreateBatch() {
        return btnCreateBatch;
    }

    public void setBtnCreateBatch(String btnCreateBatch) {
        this.btnCreateBatch = btnCreateBatch;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getBtnNotice() {
        return btnNotice;
    }

    public void setBtnNotice(String btnNotice) {
        this.btnNotice = btnNotice;
    }

    public String getKpiDayType() {
        return kpiDayType;
    }

    public void setKpiDayType(String kpiDayType) {
        this.kpiDayType = kpiDayType;
    }

    public String getChkCreateDate() {
        return chkCreateDate;
    }

    public void setChkCreateDate(String chkCreateDate) {
        this.chkCreateDate = chkCreateDate;
    }

    public String getIsRegularTime() {
        return isRegularTime;
    }

    public void setIsRegularTime(String isRegularTime) {
        this.isRegularTime = isRegularTime;
    }

    public String getBeforeCreateDate() {
        return beforeCreateDate;
    }

    public void setBeforeCreateDate(String beforeCreateDate) {
        this.beforeCreateDate = beforeCreateDate;
    }

    public String getStartCountTime() {
        return startCountTime;
    }

    public void setStartCountTime(String startCountTime) {
        this.startCountTime = startCountTime;
    }

    public BigDecimal getRegularTime() {
        return regularTime;
    }

    public void setRegularTime(BigDecimal regularTime) {
        this.regularTime = regularTime;
    }

}
