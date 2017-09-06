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

import java.util.Date;

/**
 * OrderDetailSpv POJO物件
 * 
 * @author JoeChiu
 *
 */
public class OrderDetailSpvPOJO {
	
	private String orderDId;
	private String poolKey;
	private String sourceOrderId;
	private String accountId;
	private String msisdn;
	private String cycleCode;
	private String cancelReason;
	private String opId;
	private String channel;
	private String serviceName;
	private String offerId;
	private String offerName;
	private String taskStatus;
    private Date statusTime;
    private Date requestEffectiveDate;
    private Date requestExpirationDate;
    private Date taskCreationDate;
	private String taskSeqNo;
	private String taskId;
	private String createUser;
	private Date createDate;
	private Date updateDate;
	private String updateUser;
	
	public String getOrderDId() {
		return orderDId;
	}
	public void setOrderDId(String orderDId) {
		this.orderDId = orderDId;
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
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getCycleCode() {
		return cycleCode;
	}
	public void setCycleCode(String cycleCode) {
		this.cycleCode = cycleCode;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public Date getStatusTime() {
		return statusTime;
	}
	public void setStatusTime(Date statusTime) {
		this.statusTime = statusTime;
	}
	public Date getRequestEffectiveDate() {
		return requestEffectiveDate;
	}
	public void setRequestEffectiveDate(Date requestEffectiveDate) {
		this.requestEffectiveDate = requestEffectiveDate;
	}
	public Date getRequestExpirationDate() {
		return requestExpirationDate;
	}
	public void setRequestExpirationDate(Date requestExpirationDate) {
		this.requestExpirationDate = requestExpirationDate;
	}
	public Date getTaskCreationDate() {
		return taskCreationDate;
	}
	public void setTaskCreationDate(Date taskCreationDate) {
		this.taskCreationDate = taskCreationDate;
	}
	public String getTaskSeqNo() {
		return taskSeqNo;
	}
	public void setTaskSeqNo(String taskSeqNo) {
		this.taskSeqNo = taskSeqNo;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
