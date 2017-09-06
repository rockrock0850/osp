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

package com.fet.crm.osp.platform.core.vo.orderinfo.condition;


/**
 * [訂單資訊] 查詢條件 資料物件
 * 
 * @author LawrenceLai
 */
public class OrderInfoCVO {

    private String sourceCreateTimeBegin; // 來源端進件建檔時間起
    private String sourceCreateTimeEnd; // 來源端進件建檔時間迄
    private String expectProcessTimeBegin; // 預期處理時間起
    private String expectProcessTimeEnd; // 預期處理時間迄
    private String expectCompleteTime; // 預期完成時間
    private String orderTypeId; // 案件類型名稱 的內顯示值
    private String imgIdApid; // 影像代號APID
    private String sourceSysId; // 進件來源系統代號
    private String msisdn; // 門號/代表號
    private String custName; // 用戶名稱
    private String custId; // 客戶代號
    private String sourceProdTypeId; // 進件來源產品名稱 的內顯示值
    private String operateType; // 交易類型，AIMS特有欄位
    private String ivrCode; // 經銷代碼
    private String salesId; // 業務代碼
    private String processUserName; // 處理人員姓名
    private String orderStatus; // 案件狀態

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

    public String getExpectProcessTimeBegin() {
        return expectProcessTimeBegin;
    }

    public void setExpectProcessTimeBegin(String expectProcessTimeBegin) {
        this.expectProcessTimeBegin = expectProcessTimeBegin;
    }

    public String getExpectProcessTimeEnd() {
        return expectProcessTimeEnd;
    }

    public void setExpectProcessTimeEnd(String expectProcessTimeEnd) {
        this.expectProcessTimeEnd = expectProcessTimeEnd;
    }

    public String getExpectCompleteTime() {
        return expectCompleteTime;
    }

    public void setExpectCompleteTime(String expectCompleteTime) {
        this.expectCompleteTime = expectCompleteTime;
    }


    public String getImgIdApid() {
        return imgIdApid;
    }

    public void setImgIdApid(String imgIdApid) {
        this.imgIdApid = imgIdApid;
    }

    public String getSourceSysId() {
        return sourceSysId;
    }

    public void setSourceSysId(String sourceSysId) {
        this.sourceSysId = sourceSysId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getIvrCode() {
        return ivrCode;
    }

    public void setIvrCode(String ivrCode) {
        this.ivrCode = ivrCode;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getProcessUserName() {
        return processUserName;
    }

    public void setProcessUserName(String processUserName) {
        this.processUserName = processUserName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(String orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public String getSourceProdTypeId() {
        return sourceProdTypeId;
    }

    public void setSourceProdTypeId(String sourceProdTypeId) {
        this.sourceProdTypeId = sourceProdTypeId;
    }

}
