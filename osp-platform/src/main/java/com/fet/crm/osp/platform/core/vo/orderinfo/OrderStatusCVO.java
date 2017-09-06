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

package com.fet.crm.osp.platform.core.vo.orderinfo;

/**
 * [案件狀態] 查詢條件封裝物件
 *
 * @author AndrewLee
 */
public class OrderStatusCVO {

    private String custId;
    private String custName;
    private String ivrCode;
    private String msisdn;
    private String operateType;
    private String orderFinishDateBegin;
    private String orderFinishDateEnd;
    private String orderMId;
    private String orderStatus;
    private String orderTypeId;
    private String processUserName;
    private String salesId;
    private String sourceCreateTimeBegin;
    private String sourceCreateTimeEnd;
    private String sourceOrderId;
    private String sourceProdTypeId;
    private String sourceSysId;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getIvrCode() {
        return ivrCode;
    }

    public void setIvrCode(String ivrCode) {
        this.ivrCode = ivrCode;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOrderFinishDateBegin() {
        return orderFinishDateBegin;
    }

    public void setOrderFinishDateBegin(String orderFinishDateBegin) {
        this.orderFinishDateBegin = orderFinishDateBegin;
    }

    public String getOrderFinishDateEnd() {
        return orderFinishDateEnd;
    }

    public void setOrderFinishDateEnd(String orderFinishDateEnd) {
        this.orderFinishDateEnd = orderFinishDateEnd;
    }

    public String getOrderMId() {
        return orderMId;
    }

    public void setOrderMId(String orderMId) {
        this.orderMId = orderMId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(String orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public String getProcessUserName() {
        return processUserName;
    }

    public void setProcessUserName(String processUserName) {
        this.processUserName = processUserName;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getSourceCreateTimeBegin() {
        return sourceCreateTimeBegin;
    }

    public void setSourceCreateTimeBegin(String sourceCreateTimeBegin) {
        this.sourceCreateTimeBegin = sourceCreateTimeBegin;
    }

    public String getSourceCreateTimeEnd() {
        return sourceCreateTimeEnd;
    }

    public void setSourceCreateTimeEnd(String sourceCreateTimeEnd) {
        this.sourceCreateTimeEnd = sourceCreateTimeEnd;
    }

    public String getSourceOrderId() {
        return sourceOrderId;
    }

    public void setSourceOrderId(String sourceOrderId) {
        this.sourceOrderId = sourceOrderId;
    }

    public String getSourceProdTypeId() {
        return sourceProdTypeId;
    }

    public void setSourceProdTypeId(String sourceProdTypeId) {
        this.sourceProdTypeId = sourceProdTypeId;
    }

    public String getSourceSysId() {
        return sourceSysId;
    }

    public void setSourceSysId(String sourceSysId) {
        this.sourceSysId = sourceSysId;
    }

}
