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

package com.fet.crm.osp.platform.webapp.vo;

import java.util.List;
import java.util.Map;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 * 
 * @author Adam
 */
public class NotifyOtherSalesVO extends AbstractOspBaseVO {

	private String orderMId;
	private String orderTypeId;
	private String orderTypeName;
	private String ospCreateTime;
	private String msisdn;
	private String orderStatus;
	private String orderStatusText;
	private String processReason;
	private String commment;
	private String processReasonText;
	private String processUserId;
	private String comboValue;
	private String comboContent;
	private List<Map<String, Object>> msisdnList;

	public String getProcessUserId() {
		return processUserId;
	}

	public void setProcessUserId(String processUserId) {
		this.processUserId = processUserId;
	}

	public String getOrderTypeId() {
		return orderTypeId;
	}

	public void setOrderTypeId(String orderTypeId) {
		this.orderTypeId = orderTypeId;
	}

	public String getOrderMId() {
		return orderMId;
	}

	public void setOrderMId(String orderMId) {
		this.orderMId = orderMId;
	}

	public String getOrderTypeName() {
		return orderTypeName;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public String getOspCreateTime() {
		return ospCreateTime;
	}

	public void setOspCreateTime(String ospCreateTime) {
		this.ospCreateTime = ospCreateTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getProcessReason() {
		return processReason;
	}

	public void setProcessReason(String processReason) {
		this.processReason = processReason;
	}

	public String getCommment() {
		return commment;
	}

	public void setCommment(String commment) {
		this.commment = commment;
	}

	public String getOrderStatusText() {
		return orderStatusText;
	}

	public void setOrderStatusText(String orderStatusText) {
		this.orderStatusText = orderStatusText;
	}

	public String getProcessReasonText() {
		return processReasonText;
	}

	public void setProcessReasonText(String processReasonText) {
		this.processReasonText = processReasonText;
	}

	public String getComboContent() {
		return comboContent;
	}

	public void setComboContent(String comboContent) {
		this.comboContent = comboContent;
	}

	public String getComboValue() {
		return comboValue;
	}

	public void setComboValue(String comboValue) {
		this.comboValue = comboValue;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public List<Map<String, Object>> getMsisdnList() {
		return msisdnList;
	}

	public void setMsisdnList(List<Map<String, Object>> msisdnList) {
		this.msisdnList = msisdnList;
	}
	
}


