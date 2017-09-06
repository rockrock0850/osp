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
package com.fet.crm.osp.platform.mware.client.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Adam
 * @create date: May 31, 2017
 *
 */
public class OrderLoadVO {
    
	private String poolKey;
	private String sourceOrderId;
	private String teamGroup;
	private String sourceSysId;
	private String criticalFlag;
	private Date sourceCreateTime;
	private BigDecimal counts;
	private Date expectProcessTime;
	private String status;
	private String processUser;

	private String msisdn;
	private String custName;
	private String custId;
	
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
	public String getCriticalFlag() {
		return criticalFlag;
	}
	public void setCriticalFlag(String criticalFlag) {
		this.criticalFlag = criticalFlag;
	}
	public Date getSourceCreateTime() {
		return sourceCreateTime;
	}
	public void setSourceCreateTime(Date date) {
		this.sourceCreateTime = date;
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
	public void setExpectProcessTime(Date date) {
		this.expectProcessTime = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPoolKey() {
		return poolKey;
	}
	public void setPoolKey(String poolKey) {
		this.poolKey = poolKey;
	}
	public String getProcessUser() {
		return processUser;
	}
	public void setProcessUser(String processUser) {
		this.processUser = processUser;
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
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
    
}
