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
public class StaffVO {

	String ntdomainAccountId;
	String amdocsId;
	String staffName;
	String emailAddress;
	BigDecimal groupNumber;
	String logname;
	String password;
	String areaId;
	BigDecimal staffId;

	public String getNtdomainAccountId() {
		return ntdomainAccountId;
	}

	public void setNtdomainAccountId(String ntdomainAccountId) {
		this.ntdomainAccountId = ntdomainAccountId;
	}

	public String getAmdocsId() {
		return amdocsId;
	}

	public void setAmdocsId(String amdocsId) {
		this.amdocsId = amdocsId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public BigDecimal getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(BigDecimal groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public BigDecimal getStaffId() {
		return staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

}
