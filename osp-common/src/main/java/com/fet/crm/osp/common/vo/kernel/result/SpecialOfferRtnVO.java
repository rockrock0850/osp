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
package com.fet.crm.osp.common.vo.kernel.result;

/**
 * 查詢學生溫暖長青申請方案資訊回傳資訊封裝物件
 * 
 * @author RichardHuang
 */
public class SpecialOfferRtnVO {
    
    private String offerId; // 折扣方案編號
    private String offerName; // 折扣方案名稱
    private String msisdn; // 門號
    private String startDate; // 折扣起始日期
    private String endDate; // 折扣終止日期
    
    /**
     * @return the offerId
     */
    public String getOfferId() {
        return offerId;
    }
    
    /**
     * @param offerId the offerId to set
     */
    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }
    
    /**
     * @return the offerName
     */
    public String getOfferName() {
        return offerName;
    }
    
    /**
     * @param offerName the offerName to set
     */
    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }
    
    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }
    
    /**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
    
    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }
    
    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }
    
    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
}
