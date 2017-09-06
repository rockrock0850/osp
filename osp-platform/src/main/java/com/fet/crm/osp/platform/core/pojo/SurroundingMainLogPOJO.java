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
import java.util.List;

/**
 * 
 * @author LawrenceLai
 */
public class SurroundingMainLogPOJO {

	private String surroundingMId;
	private String orderMId;
	private String ownId;
	private String ownInfo;
	private String usingMsisdn;
	private String agentInfo;
	private String currUsingInfo;
	private String crmProfileIdviewInfo;
	private List<SurroundingDetailLogPOJO> detailLogList;
	private Date createDate;
	private String createUser;

	public String getSurroundingMId() {
		return surroundingMId;
	}

	public void setSurroundingMId(String surroundingMId) {
		this.surroundingMId = surroundingMId;
	}

	public String getOrderMId() {
		return orderMId;
	}

	public void setOrderMId(String orderMId) {
		this.orderMId = orderMId;
	}

	public String getOwnId() {
		return ownId;
	}

	public void setOwnId(String ownId) {
		this.ownId = ownId;
	}

	public String getOwnInfo() {
		return ownInfo;
	}

	public void setOwnInfo(String ownInfo) {
		this.ownInfo = ownInfo;
	}

	public String getUsingMsisdn() {
		return usingMsisdn;
	}

	public void setUsingMsisdn(String usingMsisdn) {
		this.usingMsisdn = usingMsisdn;
	}

	public String getAgentInfo() {
		return agentInfo;
	}

	public void setAgentInfo(String agentInfo) {
		this.agentInfo = agentInfo;
	}

	public String getCurrUsingInfo() {
		return currUsingInfo;
	}

	public void setCurrUsingInfo(String currUsingInfo) {
		this.currUsingInfo = currUsingInfo;
	}

	public String getCrmProfileIdviewInfo() {
		return crmProfileIdviewInfo;
	}

	public void setCrmProfileIdviewInfo(String crmProfileIdviewInfo) {
		this.crmProfileIdviewInfo = crmProfileIdviewInfo;
	}

	public List<SurroundingDetailLogPOJO> getDetailLogList() {
		return detailLogList;
	}

	public void setDetailLogList(List<SurroundingDetailLogPOJO> detailLogList) {
		this.detailLogList = detailLogList;
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

}
