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
 * SPV案件指派詳細資續 封裝物件
 * 
 * @author JoeChiu
 */
public class OrderDetailSpvVO extends AbstractOspBaseVO {

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
	private String statusTime;
	private String requestEffectiveDate;
	private String requestExpirationDate;
	private String taskCreationDate;
	private String taskSeqNo;
	private String taskId;

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

	public void setCancelReason(String cancleReason) {
		this.cancelReason = cancleReason;
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

	public String getStatusTime() {
		return statusTime;
	}

	public void setStatusTime(String statusTime) {
		this.statusTime = statusTime;
	}

	public String getRequestEffectiveDate() {
		return requestEffectiveDate;
	}

	public void setRequestEffectiveDate(String requestEffectiveDate) {
		this.requestEffectiveDate = requestEffectiveDate;
	}

	public String getRequestExpirationDate() {
		return requestExpirationDate;
	}

	public void setRequestExpirationDate(String requestExpirationDate) {
		this.requestExpirationDate = requestExpirationDate;
	}

	public String getTaskCreationDate() {
		return taskCreationDate;
	}

	public void setTaskCreationDate(String taskCreationDate) {
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

}
