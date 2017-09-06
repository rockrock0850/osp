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

package com.fet.crm.osp.platform.core.vo.dispatchinfo;

import java.math.BigDecimal;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 * 
 * @author LawrenceLai,AndrewLee
 * @author AllenChen at 2017-0508
 */
public class FlowSourceMapVO extends AbstractOspBaseVO {

    private String sourceSysId;
    private String flowId;
    private String sourceProdTypeId;
    private String sourceProdTypeName;
    private BigDecimal upperLimitCounts;
    private BigDecimal lowerLimitCounts;
    private String orderTypeId;
    private String orderTypeName;
    private String isDefault;

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

	public String getSourceProdTypeName() {
		return sourceProdTypeName;
	}

	public void setSourceProdTypeName(String sourceProdTypeName) {
		this.sourceProdTypeName = sourceProdTypeName;
	}
    
}
