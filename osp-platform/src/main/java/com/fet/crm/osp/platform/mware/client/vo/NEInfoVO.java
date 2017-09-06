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
package com.fet.crm.osp.platform.mware.client.vo;

/**
 * 查詢簽核層級與人員相關資訊的 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 *  
 * @author Adam Yeh
 */
public class NEInfoVO {
    
    private String paymentCategory; // 付款種類
    private String msisdn; 
    private String accountName;
    private String isVolteSub;
    private String subType;
	public String getPaymentCategory() {
		return paymentCategory;
	}
	public void setPaymentCategory(String paymentCategory) {
		this.paymentCategory = paymentCategory;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getIsVolteSub() {
		return isVolteSub;
	}
	public void setIsVolteSub(String isVolteSub) {
		this.isVolteSub = isVolteSub;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
    
    
}
