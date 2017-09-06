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

package com.fet.crm.osp.kernel.core.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderMainMetadata 操作用POJO物件. <br>
 * 
 * @author VJChou
 */
public class OrderMainMetadataPOJO {

    private String poolKey;
    private String sourceOrderId;
    private String teamGroup;
    private String sourceSysId;
    private String sourceProdTypeId;
    private String operateType;
    private String criticalFlag;
    private Date sourceCreateTime;
    private String msisdn;
    private String custName;
    private String imgIdApid;
    private String partentOrderId;
    private BigDecimal counts;
    private Date expectProcessTime;
    private Date custSpecifyDate;
    private String custId;
    private String salesId;
    private String ivrCode;
    private String promotionId;
    private String imgStorePath;
    private String imgStoreServer;
    private String cid;
    private String status;
    private String processUser;
    private String sysSyncBatchNo;
    private Date createDate;
    private String createUser;
    private Date updateDate;
    private String updateUser;

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

    public Date getSourceCreateTime() {
        return sourceCreateTime;
    }

    public void setSourceCreateTime(Date sourceCreateTime) {
        this.sourceCreateTime = sourceCreateTime;
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

    public BigDecimal getCounts() {
        return counts;
    }

    public void setCounts(BigDecimal counts) {
        this.counts = counts;
    }

    public Date getExpectProcessTime() {
        return expectProcessTime;
    }

    public void setExpectProcessTime(Date expectProcessTime) {
        this.expectProcessTime = expectProcessTime;
    }

    public Date getCustSpecifyDate() {
        return custSpecifyDate;
    }

    public void setCustSpecifyDate(Date custSpecifyDate) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessUser() {
        return processUser;
    }

    public void setProcessUser(String processUser) {
        this.processUser = processUser;
    }

    public String getSysSyncBatchNo() {
        return sysSyncBatchNo;
    }

    public void setSysSyncBatchNo(String sysSyncBatchNo) {
        this.sysSyncBatchNo = sysSyncBatchNo;
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
