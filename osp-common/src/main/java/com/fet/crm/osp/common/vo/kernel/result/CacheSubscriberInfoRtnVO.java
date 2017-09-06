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

import java.math.BigInteger;
import java.util.Date;

/**
 * @author RichardHuang
 */
public class CacheSubscriberInfoRtnVO {
    
    private String subscriberId;
    private BigInteger contractComponentId;
    private String msisdn;
    private BigInteger partyId;
    private String accountId;
    private BigInteger contractId;
    private String accountContractComponentId;
    private BigInteger accountPartyId;
    private String customerId;
    private BigInteger adminFldNmTpCd;
    private String serviceProvider;
    private String generationCode;
    private String paymentCategory;
    private String subscriberType;
    private String subscriberStatus;
    private String ivrLang;
    private Date lastMsisdnUpdateDt;
    private Date initActiveDt;
    private Date updateDt;
    private Date lastSubStDt;
    private String lastSubStActivity;
    private String subStReason;
    private String subStReasonDesr;
    
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
     * @return the contractComponentId
     */
    public BigInteger getContractComponentId() {
        return contractComponentId;
    }
    
    /**
     * @param contractComponentId the contractComponentId to set
     */
    public void setContractComponentId(BigInteger contractComponentId) {
        this.contractComponentId = contractComponentId;
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
     * @return the partyId
     */
    public BigInteger getPartyId() {
        return partyId;
    }
    
    /**
     * @param partyId the partyId to set
     */
    public void setPartyId(BigInteger partyId) {
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
     * @return the contractId
     */
    public BigInteger getContractId() {
        return contractId;
    }
    
    /**
     * @param contractId the contractId to set
     */
    public void setContractId(BigInteger contractId) {
        this.contractId = contractId;
    }
    
    /**
     * @return the accountContractComponentId
     */
    public String getAccountContractComponentId() {
        return accountContractComponentId;
    }
    
    /**
     * @param accountContractComponentId the accountContractComponentId to set
     */
    public void setAccountContractComponentId(String accountContractComponentId) {
        this.accountContractComponentId = accountContractComponentId;
    }
    
    /**
     * @return the accountPartyId
     */
    public BigInteger getAccountPartyId() {
        return accountPartyId;
    }
    
    /**
     * @param accountPartyId the accountPartyId to set
     */
    public void setAccountPartyId(BigInteger accountPartyId) {
        this.accountPartyId = accountPartyId;
    }
    
    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }
    
    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    /**
     * @return the adminFldNmTpCd
     */
    public BigInteger getAdminFldNmTpCd() {
        return adminFldNmTpCd;
    }
    
    /**
     * @param adminFldNmTpCd the adminFldNmTpCd to set
     */
    public void setAdminFldNmTpCd(BigInteger adminFldNmTpCd) {
        this.adminFldNmTpCd = adminFldNmTpCd;
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
    
    /**
     * @return the subscriberType
     */
    public String getSubscriberType() {
        return subscriberType;
    }
    
    /**
     * @param subscriberType the subscriberType to set
     */
    public void setSubscriberType(String subscriberType) {
        this.subscriberType = subscriberType;
    }
    
    /**
     * @return the subscriberStatus
     */
    public String getSubscriberStatus() {
        return subscriberStatus;
    }
    
    /**
     * @param subscriberStatus the subscriberStatus to set
     */
    public void setSubscriberStatus(String subscriberStatus) {
        this.subscriberStatus = subscriberStatus;
    }
    
    /**
     * @return the ivrLang
     */
    public String getIvrLang() {
        return ivrLang;
    }
    
    /**
     * @param ivrLang the ivrLang to set
     */
    public void setIvrLang(String ivrLang) {
        this.ivrLang = ivrLang;
    }
    
    /**
     * @return the lastMsisdnUpdateDt
     */
    public Date getLastMsisdnUpdateDt() {
        return lastMsisdnUpdateDt;
    }
    
    /**
     * @param lastMsisdnUpdateDt the lastMsisdnUpdateDt to set
     */
    public void setLastMsisdnUpdateDt(Date lastMsisdnUpdateDt) {
        this.lastMsisdnUpdateDt = lastMsisdnUpdateDt;
    }
    
    /**
     * @return the initActiveDt
     */
    public Date getInitActiveDt() {
        return initActiveDt;
    }
    
    /**
     * @param initActiveDt the initActiveDt to set
     */
    public void setInitActiveDt(Date initActiveDt) {
        this.initActiveDt = initActiveDt;
    }
    
    /**
     * @return the updateDt
     */
    public Date getUpdateDt() {
        return updateDt;
    }
    
    /**
     * @param updateDt the updateDt to set
     */
    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }
    
    /**
     * @return the lastSubStDt
     */
    public Date getLastSubStDt() {
        return lastSubStDt;
    }
    
    /**
     * @param lastSubStDt the lastSubStDt to set
     */
    public void setLastSubStDt(Date lastSubStDt) {
        this.lastSubStDt = lastSubStDt;
    }
    
    /**
     * @return the lastSubStActivity
     */
    public String getLastSubStActivity() {
        return lastSubStActivity;
    }
    
    /**
     * @param lastSubStActivity the lastSubStActivity to set
     */
    public void setLastSubStActivity(String lastSubStActivity) {
        this.lastSubStActivity = lastSubStActivity;
    }
    
    /**
     * @return the subStReason
     */
    public String getSubStReason() {
        return subStReason;
    }
    
    /**
     * @param subStReason the subStReason to set
     */
    public void setSubStReason(String subStReason) {
        this.subStReason = subStReason;
    }
    
    /**
     * @return the subStReasonDesr
     */
    public String getSubStReasonDesr() {
        return subStReasonDesr;
    }
    
    /**
     * @param subStReasonDesr the subStReasonDesr to set
     */
    public void setSubStReasonDesr(String subStReasonDesr) {
        this.subStReasonDesr = subStReasonDesr;
    }

}
