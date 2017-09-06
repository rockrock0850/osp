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

package com.fet.crm.osp.common.vo.kernel.input.param;

/**
 * 案件分派 RESTful Web Services POST 請求參數資訊封裝物件. <br>
 * 
 * @author RichardHuang
 */
public class DispatchParamVO {

	private String empNo; // 員工編號
	
    private String teamGroup; // 員工所屬進件組別

	/**
	 * 取得員工編號
	 * 
	 * @return the empNo
	 */
	public String getEmpNo() {
		return empNo;
	}

	/**
	 * 設定員工編號
	 * 
	 * @param empNo the empNo to set
	 */
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	/**
	 * 取得員工所屬進件組別
	 * 
	 * @return the teamGroup
	 */
	public String getTeamGroup() {
		return teamGroup;
	}

	/**
	 * 設定員工所屬進件組別
	 * 
	 * @param teamGroup the teamGroup to set
	 */
	public void setTeamGroup(String teamGroup) {
		this.teamGroup = teamGroup;
	}
    
}
