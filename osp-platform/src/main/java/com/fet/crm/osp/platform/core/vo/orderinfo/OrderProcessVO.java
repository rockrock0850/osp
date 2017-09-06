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

import java.util.List;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 * [案件內容] 資料物件
 * 
 * @author Adam Yeh
 */
public class OrderProcessVO extends AbstractOspBaseVO {

	private String orderMId;
	private String userId;
	private List<String> ospKeyList;
	private String processUserId;
	private String processUserName;
	private boolean deptFeatrue;

	// 共用按鈕: 更改案件類別
	private String orderTypeId;
	private String orderTypeName;

	// 共用按鈕: 有效件/無效件
	private String processReason;
	private String processResult;
	private String comment;

	// 共用按鈕: 暫離訊息
	private String msgContent;

	// 所有Flow Content資料
	private List<OrderBuzContentVO> content;

	public String getOrderMId() {
		return orderMId;
	}

	public void setOrderMId(String orderMId) {
		this.orderMId = orderMId;
	}

	public List<OrderBuzContentVO> getContent() {
		return content;
	}

	public void setContent(List<OrderBuzContentVO> content) {
		this.content = content;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getProcessReason() {
		return processReason;
	}

	public void setProcessReason(String processReason) {
		this.processReason = processReason;
	}

	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public List<String> getOspKeyList() {
		return ospKeyList;
	}

	public void setOspKeyList(List<String> ospKeyList) {
		this.ospKeyList = ospKeyList;
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

	public boolean isDeptFeatrue() {
		return deptFeatrue;
	}

	public void setDeptFeatrue(boolean deptFeatrue) {
		this.deptFeatrue = deptFeatrue;
	}

}