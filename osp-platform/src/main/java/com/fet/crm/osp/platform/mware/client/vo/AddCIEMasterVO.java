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
 * 新增 CIE Master 資料的 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 *  
 * @author Adam Yeh
 */
public class AddCIEMasterVO {
    
    private String empNo;
    private String contractComponentId;
    private String partyId;
    private String accountId;
    private String subscriberId;
    private String msisdn;
    private String serviceProvider;
    private String generationCode;
    private String paymentCategory;
    
    public AddCIEMasterVO() {
    	;
    }
    
    public AddCIEMasterVO(String empNo, CacheSubscriberInfoVO subscriberInfoVO) {
    	if (subscriberInfoVO == null) {
    		return;
    	}
    	
    	this.empNo = empNo;
	    this.contractComponentId = String.valueOf(subscriberInfoVO.getContractComponentId());
	    this.partyId = String.valueOf(subscriberInfoVO.getPartyId());            
	    this.accountId = subscriberInfoVO.getAccountId();          
	    this.subscriberId = subscriberInfoVO.getSubscriberId();       
	    this.msisdn = subscriberInfoVO.getMsisdn();             
	    this.serviceProvider = subscriberInfoVO.getServiceProvider();    
	    this.generationCode = subscriberInfoVO.getGenerationCode();     
	    this.paymentCategory = subscriberInfoVO.getPaymentCategory();    
    }
    
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getContractComponentId() {
		return contractComponentId;
	}
	public void setContractComponentId(String contractComponentId) {
		this.contractComponentId = contractComponentId;
	}
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	public String getGenerationCode() {
		return generationCode;
	}
	public void setGenerationCode(String generationCode) {
		this.generationCode = generationCode;
	}
	public String getPaymentCategory() {
		return paymentCategory;
	}
	public void setPaymentCategory(String paymentCategory) {
		this.paymentCategory = paymentCategory;
	}
    
}
