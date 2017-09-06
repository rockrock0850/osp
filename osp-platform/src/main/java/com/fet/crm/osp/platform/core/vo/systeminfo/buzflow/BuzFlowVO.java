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

package com.fet.crm.osp.platform.core.vo.systeminfo.buzflow;

import java.util.List;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzFlowStepVO;

/**
 * 頁面流程資訊 封裝物件
 * 
 * @author PaulChen, Adam Yeh
 */
public class BuzFlowVO extends AbstractOspBaseVO {

	private String flowId;
	private String flowNm;
	private String orderMId;
	private String btnTmpSave;
	private String btnTmpLeave;
	private String btnSuccess;
	private String btnFail;
	private String btnShowMessage;
	private String btnReply;
	private String btnShowSourceDoc;
	private String btnChangeOrderType;
	private String sourceSysId;
	private String orderTypeId;
	private String poolKey;
	private String orderStatus;

	private List<BuzFlowStepVO> buzFlowStepLs;

	public BuzFlowVO() {

	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public List<BuzFlowStepVO> getBuzFlowStepLs() {
		return buzFlowStepLs;
	}

	public void setBuzFlowStepLs(List<BuzFlowStepVO> buzFlowStepLs) {
		this.buzFlowStepLs = buzFlowStepLs;
	}

	public String getFlowNm() {
		return flowNm;
	}

	public void setFlowNm(String flowNm) {
		this.flowNm = flowNm;
	}

	public String getBtnTmpSave() {
		return btnTmpSave;
	}

	public void setBtnTmpSave(String btnTmpSave) {
		this.btnTmpSave = btnTmpSave;
	}

	public String getBtnTmpLeave() {
		return btnTmpLeave;
	}

	public void setBtnTmpLeave(String btnTmpLeave) {
		this.btnTmpLeave = btnTmpLeave;
	}

	public String getBtnSuccess() {
		return btnSuccess;
	}

	public void setBtnSuccess(String btnSuccess) {
		this.btnSuccess = btnSuccess;
	}

	public String getBtnFail() {
		return btnFail;
	}

	public void setBtnFail(String btnFail) {
		this.btnFail = btnFail;
	}

	public String getBtnShowMessage() {
		return btnShowMessage;
	}

	public void setBtnShowMessage(String btnShowMessage) {
		this.btnShowMessage = btnShowMessage;
	}

	public String getBtnReply() {
		return btnReply;
	}

	public void setBtnReply(String btnReply) {
		this.btnReply = btnReply;
	}

	public String getBtnShowSourceDoc() {
		return btnShowSourceDoc;
	}

	public void setBtnShowSourceDoc(String btnShowSourceDoc) {
		this.btnShowSourceDoc = btnShowSourceDoc;
	}

	public String getBtnChangeOrderType() {
		return btnChangeOrderType;
	}

	public void setBtnChangeOrderType(String btnChangeOrderType) {
		this.btnChangeOrderType = btnChangeOrderType;
	}

	public String getOrderMId() {
		return this.orderMId;
	}

	public void setOrderMId(String orderMId) {
		this.orderMId = orderMId;
	}

	public String getSourceSysId() {
		return sourceSysId;
	}

	public void setSourceSysId(String sourceSysId) {
		this.sourceSysId = sourceSysId;
	}

	public String getOrderTypeId() {
		return orderTypeId;
	}

	public void setOrderTypeId(String orderTypeId) {
		this.orderTypeId = orderTypeId;
	}

	public String getPoolKey() {
		return poolKey;
	}

	public void setPoolKey(String poolKey) {
		this.poolKey = poolKey;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
