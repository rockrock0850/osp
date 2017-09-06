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

package com.fet.crm.osp.platform.core.vo.systeminfo;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 * [員工] 資訊封裝物件
 * 
 * @author LawrenceLai
 */
public class MemberInfoVO extends AbstractOspBaseVO {

	private String empNo;		// 員工編號
	private String empName;		// 員工中文名字
	private String engName;		// 員工英文名字
	private String ntAccount;	// 登入帳號
	private String deptCode;	// 單位代號
	private String deptName;	// 單位名稱

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getNtAccount() {
		return ntAccount;
	}

	public void setNtAccount(String ntAccount) {
		this.ntAccount = ntAccount;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
