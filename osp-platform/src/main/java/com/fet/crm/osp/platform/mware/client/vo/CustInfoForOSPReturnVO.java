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

import java.util.List;

/**
 * 核資查詢後所回傳的封裝物件. <br>
 * 
 * @author AllenChen
 */
public class CustInfoForOSPReturnVO {
    
    // 核資相關資訊
    private String mobileGeneration; // 門號類別(2G/3G/4G/5G) - 世代別: 2/3/4/5
    private String paymentCategory; // 門號類別(月租/預付) - 付款類別: PS/PP
    private String contractStatusValue; // 門號(客戶)狀態
	private String sim; // SIM卡號
	private String birthDate; // 生日
	private String activeDate; // 門號啟用日
	private String accountId; // S1帳號(Account ID)
	
	// 付款人資訊
	private String identificationType; // 證照類別 
	private String payerName; // 用戶姓名 givenNameOne + lastName
	private String identificationNumber; // 證照號碼
	private String companyName; // 公司名稱
	private String citizenshipType; // 國籍
	private String billAddress; // 帳單地址 (City + Region + AddressLineOne)
	private String houseHoldAddress; // 戶籍地址 (City + Region + AddressLineOne)
	private List<String> contactTelList; // 聯絡電話
    private List<String> billEmailList; // 帳單電子信箱
    private List<String> otherEmailList; // 其他電子信箱
	private String promotionSMSIndicator; // 遠傳相關優惠簡訊
	
	// 使用人資訊
	private String userName; // 使用人姓名 givenNameOne + lastName
	private String secondIdentificationType; // 第二證照類別
	private String secondIdentificationNumber; // 第二證照號碼
	private List<String> personalEmailList; // 個人電子信箱
	
    /**
     * @return the mobileGeneration
     */
    public String getMobileGeneration() {
        return mobileGeneration;
    }

    /**
     * @param mobileGeneration the mobileGeneration to set
     */
    public void setMobileGeneration(String mobileGeneration) {
        this.mobileGeneration = mobileGeneration;
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
     * @return the contractStatusValue
     */
    public String getContractStatusValue() {
        return contractStatusValue;
    }
    
    /**
     * @param contractStatusValue the contractStatusValue to set
     */
    public void setContractStatusValue(String contractStatusValue) {
        this.contractStatusValue = contractStatusValue;
    }
    
    /**
     * @return the sim
     */
    public String getSim() {
        return sim;
    }

    /**
     * @param sim the sim to set
     */
    public void setSim(String sim) {
        this.sim = sim;
    }

    /**
     * @return the birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
    /**
     * @return the activeDate
     */
    public String getActiveDate() {
        return activeDate;
    }
    
    /**
     * @param activeDate the activeDate to set
     */
    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
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
     * @return the identificationType
     */
    public String getIdentificationType() {
        return identificationType;
    }
    
    /**
     * @param identificationType the identificationType to set
     */
    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    /**
     * @return the identificationNumber
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }
    
    /**
     * @param identificationNumber the identificationNumber to set
     */
    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }
    
    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     * @return the citizenshipType
     */
    public String getCitizenshipType() {
        return citizenshipType;
    }
    
    /**
     * @param citizenshipType the citizenshipType to set
     */
    public void setCitizenshipType(String citizenshipType) {
        this.citizenshipType = citizenshipType;
    }

    /**
     * @return the billAddress
     */
    public String getBillAddress() {
        return billAddress;
    }
    
    /**
     * @param billAddress the billAddress to set
     */
    public void setBillAddress(String billAddress) {
        this.billAddress = billAddress;
    }
    
    /**
     * @return the houseHoldAddress
     */
    public String getHouseHoldAddress() {
        return houseHoldAddress;
    }
    
    /**
     * @param houseHoldAddress the houseHoldAddress to set
     */
    public void setHouseHoldAddress(String houseHoldAddress) {
        this.houseHoldAddress = houseHoldAddress;
    }
    
    /**
     * @return the secondIdentificationType
     */
    public String getSecondIdentificationType() {
        return secondIdentificationType;
    }

    /**
     * @param secondIdentificationType the secondIdentificationType to set
     */
    public void setSecondIdentificationType(String secondIdentificationType) {
        this.secondIdentificationType = secondIdentificationType;
    }

    /**
     * @return the secondIdentificationNumber
     */
    public String getSecondIdentificationNumber() {
        return secondIdentificationNumber;
    }
    
    /**
     * @param secondIdentificationNumber the secondIdentificationNumber to set
     */
    public void setSecondIdentificationNumber(String secondIdentificationNumber) {
        this.secondIdentificationNumber = secondIdentificationNumber;
    }

    /**
     * @return the contactTelList
     */
    public List<String> getContactTelList() {
        return contactTelList;
    }
    
    /**
     * @param contactTelList the contactTelList to set
     */
    public void setContactTelList(List<String> contactTelList) {
        this.contactTelList = contactTelList;
    }

    /**
     * @return the billEmailList
     */
    public List<String> getBillEmailList() {
        return billEmailList;
    }
    
    /**
     * @param billEmailList the billEmailList to set
     */
    public void setBillEmailList(List<String> billEmailList) {
        this.billEmailList = billEmailList;
    }
    
    /**
     * @return the otherEmailList
     */
    public List<String> getOtherEmailList() {
        return otherEmailList;
    }

    /**
     * @param otherEmailList the otherEmailList to set
     */
    public void setOtherEmailList(List<String> otherEmailList) {
        this.otherEmailList = otherEmailList;
    }

    /**
     * @return the personalEmailList
     */
    public List<String> getPersonalEmailList() {
        return personalEmailList;
    }
    
    /**
     * @param personalEmailList the personalEmailList to set
     */
    public void setPersonalEmailList(List<String> personalEmailList) {
        this.personalEmailList = personalEmailList;
    }

    /**
     * @return the payerName
     */
    public String getPayerName() {
        return payerName;
    }
    
    /**
     * @param payerName the payerName to set
     */
    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }
    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * @return the promotionSMSIndicator
     */
    public String getPromotionSMSIndicator() {
        return promotionSMSIndicator;
    }
    
    /**
     * @param promotionSMSIndicator the promotionSMSIndicator to set
     */
    public void setPromotionSMSIndicator(String promotionSMSIndicator) {
        this.promotionSMSIndicator = promotionSMSIndicator;
    }

}
