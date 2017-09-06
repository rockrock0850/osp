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

package com.fet.crm.osp.platform.core.vo.systeminfo.agent;

import java.math.BigDecimal;

/**
 * 
 * @author LawrenceLai
 */
public class GroupDefinitionVO {

	BigDecimal groupNumber;
	String groupName;
	BigDecimal managerId;
	BigDecimal privilegeLevel;
	BigDecimal defaultFlag;
	BigDecimal status;
	String groupDesc;

	public BigDecimal getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(BigDecimal groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public BigDecimal getManagerId() {
		return managerId;
	}

	public void setManagerId(BigDecimal managerId) {
		this.managerId = managerId;
	}

	public BigDecimal getPrivilegeLevel() {
		return privilegeLevel;
	}

	public void setPrivilegeLevel(BigDecimal privilegeLevel) {
		this.privilegeLevel = privilegeLevel;
	}

	public BigDecimal getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(BigDecimal defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public BigDecimal getStatus() {
		return status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

}
