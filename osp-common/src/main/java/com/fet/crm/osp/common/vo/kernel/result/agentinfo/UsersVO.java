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
public class UsersVO {
    
    BigDecimal userId;
    String userLoginName;
    String userPassword;
    String userEmplId;
    
    /**
     * @return the userId
     */
    public BigDecimal getUserId() {
        return userId;
    }
    
    /**
     * @param userId the userId to set
     */
    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }
    
    /**
     * @return the userLoginName
     */
    public String getUserLoginName() {
        return userLoginName;
    }
    
    /**
     * @param userLoginName the userLoginName to set
     */
    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }
    
    /**
     * @return the userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }
    
    /**
     * @param userPassword the userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    /**
     * @return the userEmplId
     */
    public String getUserEmplId() {
        return userEmplId;
    }
    
    /**
     * @param userEmplId the userEmplId to set
     */
    public void setUserEmplId(String userEmplId) {
        this.userEmplId = userEmplId;
    }

    @Override
    public String toString() {
        return "UsersVO [userId=" + userId + ", userLoginName=" + userLoginName + ", userPassword=" + userPassword
                + ", userEmplId=" + userEmplId + "]";
    }
    
}
