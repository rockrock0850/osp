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

/**
 * [HrmMember] 數據存儲 封裝物件
 * 
 * @author Adam Yeh
 */
public class HrmMemberPOJO {

	private Date workDate;
	private String shiftTypeId;
	private String empno;
	private String dtYear;
	private String dtMonth;
	private String dtDay;
	private String empname;
	private String ntAccount;
	private Date createDate;
	private String createUser;
	private String teamGroup;

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getShiftTypeId() {
		return shiftTypeId;
	}

	public void setShiftTypeId(String shiftTypeId) {
		this.shiftTypeId = shiftTypeId;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getDtYear() {
		return dtYear;
	}

	public void setDtYear(String dtYear) {
		this.dtYear = dtYear;
	}

	public String getDtMonth() {
		return dtMonth;
	}

	public void setDtMonth(String dtMonth) {
		this.dtMonth = dtMonth;
	}

	public String getDtDay() {
		return dtDay;
	}

	public void setDtDay(String dtDay) {
		this.dtDay = dtDay;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getNtAccount() {
		return ntAccount;
	}

	public void setNtAccount(String ntAccount) {
		this.ntAccount = ntAccount;
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

	public String getTeamGroup() {
		return teamGroup;
	}

	public void setTeamGroup(String teamGroup) {
		this.teamGroup = teamGroup;
	}

}
