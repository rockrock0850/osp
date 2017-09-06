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
package com.fet.crm.osp.common.vo.kernel.result.agentinfo;

import java.math.BigDecimal;

/**
 * @author RichardHuang
 */
public class StaffVO {
    
    String ntdomainAccountId;
    String amdocsId;
    String staffName;
    String emailAddress;
    BigDecimal groupNumber;
    String logname;
    String password;
    String areaId;
    BigDecimal staffId;
    
    /**
     * @return the ntdomainAccountId
     */
    public String getNtdomainAccountId() {
        return ntdomainAccountId;
    }
    
    /**
     * @param ntdomainAccountId the ntdomainAccountId to set
     */
    public void setNtdomainAccountId(String ntdomainAccountId) {
        this.ntdomainAccountId = ntdomainAccountId;
    }
    
    /**
     * @return the amdocsId
     */
    public String getAmdocsId() {
        return amdocsId;
    }
    
    /**
     * @param amdocsId the amdocsId to set
     */
    public void setAmdocsId(String amdocsId) {
        this.amdocsId = amdocsId;
    }
    
    /**
     * @return the staffName
     */
    public String getStaffName() {
        return staffName;
    }
    
    /**
     * @param staffName the staffName to set
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    
    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    
    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    /**
     * @return the groupNumber
     */
    public BigDecimal getGroupNumber() {
        return groupNumber;
    }
    
    /**
     * @param groupNumber the groupNumber to set
     */
    public void setGroupNumber(BigDecimal groupNumber) {
        this.groupNumber = groupNumber;
    }
    
    /**
     * @return the logname
     */
    public String getLogname() {
        return logname;
    }
    
    /**
     * @param logname the logname to set
     */
    public void setLogname(String logname) {
        this.logname = logname;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return the areaId
     */
    public String getAreaId() {
        return areaId;
    }
    
    /**
     * @param areaId the areaId to set
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
    
    /**
     * @return the staffId
     */
    public BigDecimal getStaffId() {
        return staffId;
    }
    
    /**
     * @param staffId the staffId to set
     */
    public void setStaffId(BigDecimal staffId) {
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        return "StaffVO [ntdomainAccountId=" + ntdomainAccountId + ", amdocsId=" + amdocsId + ", staffName=" + staffName
                + ", emailAddress=" + emailAddress + ", groupNumber=" + groupNumber + ", logname=" + logname
                + ", password=" + password + ", areaId=" + areaId + ", staffId=" + staffId + "]";
    }

}
