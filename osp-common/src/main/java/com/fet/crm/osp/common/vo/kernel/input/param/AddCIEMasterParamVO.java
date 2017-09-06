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
 * 新增 CIE Master 資料 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 *  
 * @author RichardHuang
 */
public class AddCIEMasterParamVO extends AbstractESBParamVO {
    
    private String empNo;
    private String contractComponentId;
    private String partyId;
    private String accountId;
    private String subscriberId;
    private String msisdn;
    private String serviceProvider;
    private String generationCode;
    private String paymentCategory;
    
    /**
     * @return the empNo
     */
    public String getEmpNo() {
        return empNo;
    }
    
    /**
     * @param empNo the empNo to set
     */
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
    
    /**
     * @return the contractComponentId
     */
    public String getContractComponentId() {
        return contractComponentId;
    }
    
    /**
     * @param contractComponentId the contractComponentId to set
     */
    public void setContractComponentId(String contractComponentId) {
        this.contractComponentId = contractComponentId;
    }
    
    /**
     * @return the partyId
     */
    public String getPartyId() {
        return partyId;
    }
    
    /**
     * @param partyId the partyId to set
     */
    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }
    
    /**
     * @return the accountId
     */
    public String getAccountId() {
        return accountId;
    }
    
    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    
    /**
     * @return the subscriberId
     */
    public String getSubscriberId() {
        return subscriberId;
    }
    
    /**
     * @param subscriberId the subscriberId to set
     */
    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
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
     * @return the serviceProvider
     */
    public String getServiceProvider() {
        return serviceProvider;
    }
    
    /**
     * @param serviceProvider the serviceProvider to set
     */
    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
    
    /**
     * @return the generationCode
     */
    public String getGenerationCode() {
        return generationCode;
    }
    
    /**
     * @param generationCode the generationCode to set
     */
    public void setGenerationCode(String generationCode) {
        this.generationCode = generationCode;
    }
    
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

}
