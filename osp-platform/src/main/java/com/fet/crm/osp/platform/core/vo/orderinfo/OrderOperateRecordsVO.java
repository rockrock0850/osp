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

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 * [案件操作歷程] 查詢結果 資料物件
 * 
 * @author LawrenceLai
 */
public class OrderOperateRecordsVO extends AbstractOspBaseVO {

	private String orderOpId;
	private String orderMId;
	private String msisdn;
	private String actionType;
	private Date executeTime;
	private String processResult;
	private String problemReason;
	private String actionContent;
	
	public OrderOperateRecordsVO() {
		;
	}
	
	public OrderOperateRecordsVO(OrderInfoVO infoVO, String actionType, String actionContent) {
		this(infoVO, actionType, actionContent, new OrderProcessVO());
	}
	
	public OrderOperateRecordsVO(OrderInfoVO infoVO, String actionType, String actionContent, OrderProcessVO orderProcessVO) {
		if (infoVO != null && orderProcessVO != null) {
			this.orderMId 		 	= infoVO.getOrderMId();
			this.msisdn		 		= infoVO.getMsisdn();
			this.actionContent 		= actionContent;
			this.actionType			= actionType;
			this.processResult 		= 
					StringUtils.isBlank(orderProcessVO.getProcessResult()) ? infoVO.getProcessResult() : orderProcessVO.getProcessResult();
			this.problemReason 		= 
					StringUtils.isBlank(orderProcessVO.getProcessReason()) ? infoVO.getProcessReason() : orderProcessVO.getProcessReason();
			this.createUser 		= 
					orderProcessVO.getUserId() == null ? infoVO.getCreateUser() : orderProcessVO.getUserId();
		}
	}

	public OrderOperateRecordsVO(OrderAssignVO assignVO) {
		if (assignVO != null) {
			this.orderMId 		 	= assignVO.getOrderMId();
			this.msisdn		 		= assignVO.getMsisdn();
			this.processResult 		= assignVO.getProcessResult();
			this.problemReason 		= assignVO.getProblemReason();
			this.actionContent 		= assignVO.getProcessUserId();
			this.actionType			= "U03";
			this.createUser 		= assignVO.getCreateUser();
		}
	}
	
	public String getOrderOpId() {
		return orderOpId;
	}

	public void setOrderOpId(String orderOpId) {
		this.orderOpId = orderOpId;
	}

	public String getOrderMId() {
		return orderMId;
	}

	public void setOrderMId(String orderMId) {
		this.orderMId = orderMId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	public String getProblemReason() {
		return problemReason;
	}

	public void setProblemReason(String problemReason) {
		this.problemReason = problemReason;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getActionContent() {
		return actionContent;
	}

	public void setActionContent(String actionContent) {
		this.actionContent = actionContent;
	}

}
