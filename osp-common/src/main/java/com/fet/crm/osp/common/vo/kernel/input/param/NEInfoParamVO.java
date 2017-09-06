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
package com.fet.crm.osp.common.vo.kernel.input.param;

/**
 * 查詢 NE Info 的 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 * 
 * @author RichardHuang
 */
public class NEInfoParamVO extends AbstractESBParamVO {
    
    private String paymentCategory; // 付款種類
    private String msisdn; 
    private String accountName;
    private String isVolteSub;
    private String subType;
    
    /**
     * @return the paymentCategory
     */
    public String getPaymentCategory() {
        return paymentCategory;
    }
    
    /**
     * @param paymentCategory the paymentCategory to set
     */
    public void setPaymentCategory(String paymentCategory) {
        this.paymentCategory = paymentCategory;
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
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }
    
    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
    /**
     * @return the isVolteSub
     */
    public String getIsVolteSub() {
        return isVolteSub;
    }
    
    /**
     * @param isVolteSub the isVolteSub to set
     */
    public void setIsVolteSub(String isVolteSub) {
        this.isVolteSub = isVolteSub;
    }
    
    /**
     * @return the subType
     */
    public String getSubType() {
        return subType;
    }
    
    /**
     * @param subType the subType to set
     */
    public void setSubType(String subType) {
        this.subType = subType;
    }
    
}
