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
package com.fet.crm.osp.kernel.mware.client.pojo;

import java.util.List;

/**
 * @author RichardHuang
 */
public class AuthLevelInfoPOJO {
    
    private String authLevel;
    private List<ApproverInfoPOJO> approverList;
    
    /**
     * @return the authLevel
     */
    public String getAuthLevel() {
        return authLevel;
    }
    
    /**
     * @param authLevel the authLevel to set
     */
    public void setAuthLevel(String authLevel) {
        this.authLevel = authLevel;
    }
    
    /**
     * @return the approverList
     */
    public List<ApproverInfoPOJO> getApproverList() {
        return approverList;
    }
    
    /**
     * @param approverList the approverList to set
     */
    public void setApproverList(List<ApproverInfoPOJO> approverList) {
        this.approverList = approverList;
    }

    @Override
    public String toString() {
        return "AuthLevelInfoPOJO [authLevel=" + authLevel + ", approverList=" + approverList + "]";
    }
    
}
