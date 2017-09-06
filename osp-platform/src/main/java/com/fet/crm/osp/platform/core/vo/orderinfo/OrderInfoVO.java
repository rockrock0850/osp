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

package com.fet.crm.osp.platform.core.vo.orderinfo;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 * 案件指派資訊 封裝物件
 * 
 * @author LawrenceLai
 */
public class OrderInfoVO extends AbstractOspBaseVO {

    private String orderMId;
    private String poolKey;
    private String sourceOrderId;
    private String teamGroup;
    private String sourceSysId;
    private String sourceProdTypeId;
    private String sourceProdTypeName;
    private String operateType;
    private String criticalFlag;
    private String sourceCreateTime;
    private String ospCreateTime;
    private String msisdn;
    private String custName;
    private String imgIdApid;
    private String partentOrderId;
    private String counts;
    private String expectProcessTime;
    private String expectCompleteTime;
    private String custSpecifyDate;
    private String custId;
    private String salesId;
    private String ivrCode;
    private String promotionId;
    private String imgStorePath;
    private String imgStoreServer;
    private String cid;
    private String processUserId;
    private String processUserName;
    private String orderTypeId;
    private String orderTypeName;
    private String orderStatus;
    private String processResult;
    private String processReason;
    private String flowId;
    private String commment;
    private String isNoticeSales;
    private String custType;
    private String corpPicTaxid;

    public String getOrderMId() {
        return orderMId;
    }

    public void setOrderMId(String orderMId) {
        this.orderMId = orderMId;
    }

    public String getPoolKey() {
        return poolKey;
    }

    public void setPoolKey(String poolKey) {
        this.poolKey = poolKey;
    }

    public String getSourceOrderId() {
        return sourceOrderId;
    }

    public void setSourceOrderId(String sourceOrderId) {
        this.sourceOrderId = sourceOrderId;
    }

    public String getTeamGroup() {
        return teamGroup;
    }

    public void setTeamGroup(String teamGroup) {
        this.teamGroup = teamGroup;
    }

    public String getSourceSysId() {
        return sourceSysId;
    }

    public void setSourceSysId(String sourceSysId) {
        this.sourceSysId = sourceSysId;
    }

    public String getSourceProdTypeId() {
        return sourceProdTypeId;
    }

    public void setSourceProdTypeId(String sourceProdTypeId) {
        this.sourceProdTypeId = sourceProdTypeId;
    }

    public String getSourceProdTypeName() {
        return sourceProdTypeName;
    }

    public void setSourceProdTypeName(String sourceProdTypeName) {
        this.sourceProdTypeName = sourceProdTypeName;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getCriticalFlag() {
        return criticalFlag;
    }

    public void setCriticalFlag(String criticalFlag) {
        this.criticalFlag = criticalFlag;
    }

    public String getSourceCreateTime() {
        return sourceCreateTime;
    }

    public void setSourceCreateTime(String sourceCreateTime) {
        this.sourceCreateTime = sourceCreateTime;
    }

    public String getOspCreateTime() {
        return ospCreateTime;
    }

    public void setOspCreateTime(String ospCreateTime) {
        this.ospCreateTime = ospCreateTime;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getImgIdApid() {
        return imgIdApid;
    }

    public void setImgIdApid(String imgIdApid) {
        this.imgIdApid = imgIdApid;
    }

    public String getPartentOrderId() {
        return partentOrderId;
    }

    public void setPartentOrderId(String partentOrderId) {
        this.partentOrderId = partentOrderId;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public String getExpectProcessTime() {
        return expectProcessTime;
    }

    public void setExpectProcessTime(String expectProcessTime) {
        this.expectProcessTime = expectProcessTime;
    }

    public String getExpectCompleteTime() {
        return expectCompleteTime;
    }

    public void setExpectCompleteTime(String expectCompleteTime) {
        this.expectCompleteTime = expectCompleteTime;
    }

    public String getCustSpecifyDate() {
        return custSpecifyDate;
    }

    public void setCustSpecifyDate(String custSpecifyDate) {
        this.custSpecifyDate = custSpecifyDate;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getIvrCode() {
        return ivrCode;
    }

    public void setIvrCode(String ivrCode) {
        this.ivrCode = ivrCode;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getImgStorePath() {
        return imgStorePath;
    }

    public void setImgStorePath(String imgStorePath) {
        this.imgStorePath = imgStorePath;
    }

    public String getImgStoreServer() {
        return imgStoreServer;
    }

    public void setImgStoreServer(String imgStoreServer) {
        this.imgStoreServer = imgStoreServer;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getProcessUserId() {
        return processUserId;
    }

    public void setProcessUserId(String processUserId) {
        this.processUserId = processUserId;
    }

    public String getProcessUserName() {
        return processUserName;
    }

    public void setProcessUserName(String processUserName) {
        this.processUserName = processUserName;
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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProcessResult() {
        return processResult;
    }

    public void setProcessResult(String processResult) {
        this.processResult = processResult;
    }

    public String getProcessReason() {
        return processReason;
    }

    public void setProcessReason(String processReason) {
        this.processReason = processReason;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getCommment() {
        return commment;
    }

    public void setCommment(String commment) {
        this.commment = commment;
    }

    public String getIsNoticeSales() {
        return isNoticeSales;
    }

    public void setIsNoticeSales(String isNoticeSales) {
        this.isNoticeSales = isNoticeSales;
    }

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCorpPicTaxid() {
		return corpPicTaxid;
	}

	public void setCorpPicTaxid(String corpPicTaxid) {
		this.corpPicTaxid = corpPicTaxid;
	}

}
