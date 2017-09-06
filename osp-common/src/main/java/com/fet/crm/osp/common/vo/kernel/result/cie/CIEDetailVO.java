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
package com.fet.crm.osp.common.vo.kernel.result.cie;

import java.util.Date;

/**
 * @author RichardHuang
 */
public class CIEDetailVO {
    
    private String cieDetailId;
    private String cieId;
    private String accountContactId;
    private String subscriberContactId;
    private String accountContractCompId;
    private String subscriberContractCompId;
    private String billingAccountId;
    private String billingSubscriberId;
    private String msisdn;
    private String serviceProvider;
    private String mobileGenerationCode;
    private String paymentCategory;
    private Date createdDate;
    private Date cieDetailLastUpdateDate;
    private String cieDetailLastUpdateUser;
    private String cieDetailLastUpdateTxId;
    
    /**
     * @return the cieDetailId
     */
    public String getCieDetailId() {
        return cieDetailId;
    }
    
    /**
     * @param cieDetailId the cieDetailId to set
     */
    public void setCieDetailId(String cieDetailId) {
        this.cieDetailId = cieDetailId;
    }
    
    /**
     * @return the cieId
     */
    public String getCieId() {
        return cieId;
    }
    
    /**
     * @param cieId the cieId to set
     */
    public void setCieId(String cieId) {
        this.cieId = cieId;
    }
    
    /**
     * @return the accountContactId
     */
    public String getAccountContactId() {
        return accountContactId;
    }
    
    /**
     * @param accountContactId the accountContactId to set
     */
    public void setAccountContactId(String accountContactId) {
        this.accountContactId = accountContactId;
    }
    
    /**
     * @return the subscriberContactId
     */
    public String getSubscriberContactId() {
        return subscriberContactId;
    }
    
    /**
     * @param subscriberContactId the subscriberContactId to set
     */
    public void setSubscriberContactId(String subscriberContactId) {
        this.subscriberContactId = subscriberContactId;
    }
    
    /**
     * @return the accountContractCompId
     */
    public String getAccountContractCompId() {
        return accountContractCompId;
    }
    
    /**
     * @param accountContractCompId the accountContractCompId to set
     */
    public void setAccountContractCompId(String accountContractCompId) {
        this.accountContractCompId = accountContractCompId;
    }
    
    /**
     * @return the subscriberContractCompId
     */
    public String getSubscriberContractCompId() {
        return subscriberContractCompId;
    }
    
    /**
     * @param subscriberContractCompId the subscriberContractCompId to set
     */
    public void setSubscriberContractCompId(String subscriberContractCompId) {
        this.subscriberContractCompId = subscriberContractCompId;
    }
    
    /**
     * @return the billingAccountId
     */
    public String getBillingAccountId() {
        return billingAccountId;
    }
    
    /**
     * @param billingAccountId the billingAccountId to set
     */
    public void setBillingAccountId(String billingAccountId) {
        this.billingAccountId = billingAccountId;
    }
    
    /**
     * @return the billingSubscriberId
     */
    public String getBillingSubscriberId() {
        return billingSubscriberId;
    }
    
    /**
     * @param billingSubscriberId the billingSubscriberId to set
     */
    public void setBillingSubscriberId(String billingSubscriberId) {
        this.billingSubscriberId = billingSubscriberId;
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
     * @return the mobileGenerationCode
     */
    public String getMobileGenerationCode() {
        return mobileGenerationCode;
    }
    
    /**
     * @param mobileGenerationCode the mobileGenerationCode to set
     */
    public void setMobileGenerationCode(String mobileGenerationCode) {
        this.mobileGenerationCode = mobileGenerationCode;
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
    
    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }
    
    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    /**
     * @return the cieDetailLastUpdateDate
     */
    public Date getCieDetailLastUpdateDate() {
        return cieDetailLastUpdateDate;
    }
    
    /**
     * @param cieDetailLastUpdateDate the cieDetailLastUpdateDate to set
     */
    public void setCieDetailLastUpdateDate(Date cieDetailLastUpdateDate) {
        this.cieDetailLastUpdateDate = cieDetailLastUpdateDate;
    }
    
    /**
     * @return the cieDetailLastUpdateUser
     */
    public String getCieDetailLastUpdateUser() {
        return cieDetailLastUpdateUser;
    }
    
    /**
     * @param cieDetailLastUpdateUser the cieDetailLastUpdateUser to set
     */
    public void setCieDetailLastUpdateUser(String cieDetailLastUpdateUser) {
        this.cieDetailLastUpdateUser = cieDetailLastUpdateUser;
    }
    
    /**
     * @return the cieDetailLastUpdateTxId
     */
    public String getCieDetailLastUpdateTxId() {
        return cieDetailLastUpdateTxId;
    }
    
    /**
     * @param cieDetailLastUpdateTxId the cieDetailLastUpdateTxId to set
     */
    public void setCieDetailLastUpdateTxId(String cieDetailLastUpdateTxId) {
        this.cieDetailLastUpdateTxId = cieDetailLastUpdateTxId;
    }

    @Override
    public String toString() {
        return "AddCIEMasterRtnVO [cieDetailId=" + cieDetailId + ", cieId=" + cieId + ", accountContactId="
                + accountContactId + ", subscriberContactId=" + subscriberContactId + ", accountContractCompId="
                + accountContractCompId + ", subscriberContractCompId=" + subscriberContractCompId
                + ", billingAccountId=" + billingAccountId + ", billingSubscriberId=" + billingSubscriberId
                + ", msisdn=" + msisdn + ", serviceProvider=" + serviceProvider + ", mobileGenerationCode="
                + mobileGenerationCode + ", paymentCategory=" + paymentCategory + ", createdDate=" + createdDate
                + ", cieDetailLastUpdateDate=" + cieDetailLastUpdateDate + ", cieDetailLastUpdateUser="
                + cieDetailLastUpdateUser + ", cieDetailLastUpdateTxId=" + cieDetailLastUpdateTxId + "]";
    }
    
}
