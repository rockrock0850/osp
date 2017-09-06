/**
 * Copyright (c) 2017 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */

package com.fet.crm.osp.platform.core.vo.dispatchinfo;

import java.math.BigDecimal;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 * [案件類別資訊] 資料封裝物件
 * 
 * @author LawrenceLai,AndrewLee
 */
public class OrderTypeInfoVO extends AbstractOspBaseVO {

    private String orderTypeId;
    private String orderTypeName;
    private String overtimeCounts;
    private String criticalCounts;
    private String email;
    private BigDecimal successSec;
    private BigDecimal failSec;
    private BigDecimal regularTime;
    private BigDecimal overtime;
    private String kpiDayType;
    private String chkCreateDate;
    private String beforeCreateDate;
    private String isRegularTime;
    private String startCountTime;
    private String kpiDayTypeText;
    private String btnCreateSingle;
    private String btnCreateBatch;
    private String btnNotice;

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

    public BigDecimal getRegularTime() {
        return regularTime;
    }

    public void setRegularTime(BigDecimal regularTime) {
        this.regularTime = regularTime;
    }

    public BigDecimal getOvertime() {
        return overtime;
    }

    public void setOvertime(BigDecimal overtime) {
        this.overtime = overtime;
    }

    public String getCriticalCounts() {
        return criticalCounts;
    }

    public void setCriticalCounts(String criticalCounts) {
        this.criticalCounts = criticalCounts;
    }

    public String getOvertimeCounts() {
        return overtimeCounts;
    }

    public void setOvertimeCounts(String overtimeCounts) {
        this.overtimeCounts = overtimeCounts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getBeforeCreateDate() {
        return beforeCreateDate;
    }

    public void setBeforeCreateDate(String beforeCreateDate) {
        this.beforeCreateDate = beforeCreateDate;
    }

    public String getIsRegularTime() {
        return isRegularTime;
    }

    public void setIsRegularTime(String isRegularTime) {
        this.isRegularTime = isRegularTime;
    }

    public String getStartCountTime() {
        return startCountTime;
    }

    public void setStartCountTime(String startCountTime) {
        this.startCountTime = startCountTime;
    }

    public String getKpiDayTypeText() {
        return kpiDayTypeText;
    }

    public void setKpiDayTypeText(String kpiDayTypeText) {
        this.kpiDayTypeText = kpiDayTypeText;
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

	public String getBtnNotice() {
		return btnNotice;
	}

	public void setBtnNotice(String btnNotice) {
		this.btnNotice = btnNotice;
	}

    
}