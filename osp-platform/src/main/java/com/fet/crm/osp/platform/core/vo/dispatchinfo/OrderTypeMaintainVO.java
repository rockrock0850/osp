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
 * [案件類別維護] 資料封裝物件
 * 
 * @author LawrenceLai
 */
public class OrderTypeMaintainVO extends AbstractOspBaseVO {

    private String orderTypeId;
    private String orderTypeName;
    private BigDecimal successSec;
    private BigDecimal failSec;
    private BigDecimal regularTime;
    private BigDecimal overtime;
    private BigDecimal criticalCounts;
    private BigDecimal overtimeCounts;
    private String email;
    private String kpiDayType;
    private String chkCreateDate;
    private String beforeCreateDate;
    private String isRegularTime;
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

    public BigDecimal getRegularTime() {
        return regularTime;
    }

    public void setRegularTime(BigDecimal regularTime) {
        this.regularTime = regularTime;
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

}