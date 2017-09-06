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

package com.fet.crm.osp.platform.core.vo.orderinfo.condition;

/**
 * [待處理] 查詢條件 資料物件
 * 
 * @author LawrenceLai
 */
public class TodoOrderCVO {

	private String orderMId; 			// OSP主要進件單號
	private String orderTypeId; 		// 案件類型代號
	private String sourceProdTypeId; 	// 進件來源產品代號
	private String msisdn; 				// 門號
	private String custName; 			// 客戶名稱
	private String sourceOrderId; 		// 來源系統單號
	private String processUserId;		// 處理人員編號

	public String getOrderMId() {
		return orderMId;
	}

	public void setOrderMId(String orderMId) {
		this.orderMId = orderMId;
	}

	public String getOrderTypeId() {
		return orderTypeId;
	}

	public void setOrderTypeId(String orderTypeId) {
		this.orderTypeId = orderTypeId;
	}

	public String getSourceProdTypeId() {
		return sourceProdTypeId;
	}

	public void setSourceProdTypeId(String sourceProdTypeId) {
		this.sourceProdTypeId = sourceProdTypeId;
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

	public String getSourceOrderId() {
		return sourceOrderId;
	}

	public void setSourceOrderId(String sourceOrderId) {
		this.sourceOrderId = sourceOrderId;
	}

	public String getProcessUserId() {
		return processUserId;
	}

	public void setProcessUserId(String processUserId) {
		this.processUserId = processUserId;
	}

}
