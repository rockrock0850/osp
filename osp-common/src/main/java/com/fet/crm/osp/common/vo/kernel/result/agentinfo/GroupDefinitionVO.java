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
public class GroupDefinitionVO {
    
    BigDecimal groupNumber;
    String groupName;
    BigDecimal managerId;
    BigDecimal privilegeLevel;
    BigDecimal defaultFlag;
    BigDecimal status;
    String groupDesc;
    
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
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }
    
    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    /**
     * @return the managerId
     */
    public BigDecimal getManagerId() {
        return managerId;
    }
    
    /**
     * @param managerId the managerId to set
     */
    public void setManagerId(BigDecimal managerId) {
        this.managerId = managerId;
    }
    
    /**
     * @return the privilegeLevel
     */
    public BigDecimal getPrivilegeLevel() {
        return privilegeLevel;
    }
    
    /**
     * @param privilegeLevel the privilegeLevel to set
     */
    public void setPrivilegeLevel(BigDecimal privilegeLevel) {
        this.privilegeLevel = privilegeLevel;
    }
    
    /**
     * @return the defaultFlag
     */
    public BigDecimal getDefaultFlag() {
        return defaultFlag;
    }
    
    /**
     * @param defaultFlag the defaultFlag to set
     */
    public void setDefaultFlag(BigDecimal defaultFlag) {
        this.defaultFlag = defaultFlag;
    }
    
    /**
     * @return the status
     */
    public BigDecimal getStatus() {
        return status;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(BigDecimal status) {
        this.status = status;
    }
    
    /**
     * @return the groupDesc
     */
    public String getGroupDesc() {
        return groupDesc;
    }
    
    /**
     * @param groupDesc the groupDesc to set
     */
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    @Override
    public String toString() {
        return "GroupDefinitionVO [groupNumber=" + groupNumber + ", groupName=" + groupName + ", managerId=" + managerId
                + ", privilegeLevel=" + privilegeLevel + ", defaultFlag=" + defaultFlag + ", status=" + status
                + ", groupDesc=" + groupDesc + "]";
    }
    
}
