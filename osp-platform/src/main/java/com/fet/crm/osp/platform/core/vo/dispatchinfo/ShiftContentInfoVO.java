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

package com.fet.crm.osp.platform.core.vo.dispatchinfo;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 * [班表內容] 資料封裝物件
 * 
 * @author LawrenceLai
 */
public class ShiftContentInfoVO extends AbstractOspBaseVO {

	private String empNo;
	private String empName;
	private String ntAccount;
	private String shiftTypeId;
	private String workDate;
	private String dtYear;
	private String dtMonth;
	private String dtDay;

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

	public String getNtAccount() {
		return ntAccount;
	}

	public void setNtAccount(String ntAccount) {
		this.ntAccount = ntAccount;
	}

	public String getShiftTypeId() {
		return shiftTypeId;
	}

	public void setShiftTypeId(String shiftTypeId) {
		this.shiftTypeId = shiftTypeId;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
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

}
