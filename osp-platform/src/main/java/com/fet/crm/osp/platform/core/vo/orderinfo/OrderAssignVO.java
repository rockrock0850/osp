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
 * @author LawrenceLai,AndrewLee
 */
public class OrderAssignVO extends AbstractOspBaseVO {

	private String orderMId; // OSP主要進件單號
	private String msisdn;
	private String processUserId; // 處理人員編號
	private String processUserName; // 處理人員姓名
	private String processResult;
	private String problemReason;

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

}
