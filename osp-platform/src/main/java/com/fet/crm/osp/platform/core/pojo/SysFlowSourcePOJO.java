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

package com.fet.crm.osp.platform.core.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SysFlowSource POJO物件
 *
 * @author AndrewLee
 */
public class SysFlowSourcePOJO {

    private String sourceSysId;
    private String flowId;
    private String sourceProdTypeId;
    private BigDecimal upperLimitCounts;
    private BigDecimal lowerLimitCounts;
    private String orderTypeId;
    private String orderTypeName;
    private String isDefault;
    private Date createDate;
    private String createUser;
    private Date updateDate;
    private String updateUser;

    public String getSourceSysId() {
        return sourceSysId;
    }

    public void setSourceSysId(String sourceSysId) {
        this.sourceSysId = sourceSysId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getSourceProdTypeId() {
        return sourceProdTypeId;
    }

    public void setSourceProdTypeId(String sourceProdTypeId) {
        this.sourceProdTypeId = sourceProdTypeId;
    }

    public BigDecimal getUpperLimitCounts() {
        return upperLimitCounts;
    }

    public void setUpperLimitCounts(BigDecimal upperLimitCounts) {
        this.upperLimitCounts = upperLimitCounts;
    }

    public BigDecimal getLowerLimitCounts() {
        return lowerLimitCounts;
    }

    public void setLowerLimitCounts(BigDecimal lowerLimitCounts) {
        this.lowerLimitCounts = lowerLimitCounts;
    }

    public String getOrderTypeId() {
        return orderTypeId;
    }

    public void setOrderTypeId(String orderTypeId) {
        this.orderTypeId = orderTypeId;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

}
