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
import java.util.List;

/**
 * @author RichardHuang
 */
public class AddCIEMasterRtnVO {
    
    private String cieId;
    private String potentialContactIndicator;
    private String inOutIndicator;
    private String channelType;
    private String channelValue;
    private String fetContactMethodType;
    private String fetContactMethodValue;
    private String createdAgent;
    private String createdSystem;
    private Date startDate;
    private Date endDate;
    private Date createDate;
    private Date cieMasterLastUpdateDate;
    private String cieMasterLastUpdateUser;
    private String cieMasterLastUpdateTxId;
    private List<CIEDetailVO> cieDetailVOList;
    
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
     * @return the potentialContactIndicator
     */
    public String getPotentialContactIndicator() {
        return potentialContactIndicator;
    }
    
    /**
     * @param potentialContactIndicator the potentialContactIndicator to set
     */
    public void setPotentialContactIndicator(String potentialContactIndicator) {
        this.potentialContactIndicator = potentialContactIndicator;
    }
    
    /**
     * @return the inOutIndicator
     */
    public String getInOutIndicator() {
        return inOutIndicator;
    }
    
    /**
     * @param inOutIndicator the inOutIndicator to set
     */
    public void setInOutIndicator(String inOutIndicator) {
        this.inOutIndicator = inOutIndicator;
    }
    
    /**
     * @return the channelType
     */
    public String getChannelType() {
        return channelType;
    }
    
    /**
     * @param channelType the channelType to set
     */
    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }
    
    /**
     * @return the channelValue
     */
    public String getChannelValue() {
        return channelValue;
    }
    
    /**
     * @param channelValue the channelValue to set
     */
    public void setChannelValue(String channelValue) {
        this.channelValue = channelValue;
    }
    
    /**
     * @return the fetContactMethodType
     */
    public String getFetContactMethodType() {
        return fetContactMethodType;
    }
    
    /**
     * @param fetContactMethodType the fetContactMethodType to set
     */
    public void setFetContactMethodType(String fetContactMethodType) {
        this.fetContactMethodType = fetContactMethodType;
    }
    
    /**
     * @return the fetContactMethodValue
     */
    public String getFetContactMethodValue() {
        return fetContactMethodValue;
    }
    
    /**
     * @param fetContactMethodValue the fetContactMethodValue to set
     */
    public void setFetContactMethodValue(String fetContactMethodValue) {
        this.fetContactMethodValue = fetContactMethodValue;
    }
    
    /**
     * @return the createdAgent
     */
    public String getCreatedAgent() {
        return createdAgent;
    }
    
    /**
     * @param createdAgent the createdAgent to set
     */
    public void setCreatedAgent(String createdAgent) {
        this.createdAgent = createdAgent;
    }
    
    /**
     * @return the createdSystem
     */
    public String getCreatedSystem() {
        return createdSystem;
    }
    
    /**
     * @param createdSystem the createdSystem to set
     */
    public void setCreatedSystem(String createdSystem) {
        this.createdSystem = createdSystem;
    }
    
    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }
    
    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }
    
    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }
    
    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    /**
     * @return the cieMasterLastUpdateDate
     */
    public Date getCieMasterLastUpdateDate() {
        return cieMasterLastUpdateDate;
    }
    
    /**
     * @param cieMasterLastUpdateDate the cieMasterLastUpdateDate to set
     */
    public void setCieMasterLastUpdateDate(Date cieMasterLastUpdateDate) {
        this.cieMasterLastUpdateDate = cieMasterLastUpdateDate;
    }
    
    /**
     * @return the cieMasterLastUpdateUser
     */
    public String getCieMasterLastUpdateUser() {
        return cieMasterLastUpdateUser;
    }
    
    /**
     * @param cieMasterLastUpdateUser the cieMasterLastUpdateUser to set
     */
    public void setCieMasterLastUpdateUser(String cieMasterLastUpdateUser) {
        this.cieMasterLastUpdateUser = cieMasterLastUpdateUser;
    }
    
    /**
     * @return the cieMasterLastUpdateTxId
     */
    public String getCieMasterLastUpdateTxId() {
        return cieMasterLastUpdateTxId;
    }
    
    /**
     * @param cieMasterLastUpdateTxId the cieMasterLastUpdateTxId to set
     */
    public void setCieMasterLastUpdateTxId(String cieMasterLastUpdateTxId) {
        this.cieMasterLastUpdateTxId = cieMasterLastUpdateTxId;
    }
    
    /**
     * @return the cieDetailVOList
     */
    public List<CIEDetailVO> getCieDetailVOList() {
        return cieDetailVOList;
    }
    
    /**
     * @param cieDetailVOList the cieDetailVOList to set
     */
    public void setCieDetailVOList(List<CIEDetailVO> cieDetailVOList) {
        this.cieDetailVOList = cieDetailVOList;
    }
    
}
