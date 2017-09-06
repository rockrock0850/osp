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
package com.fet.crm.osp.kernel.core.pojo;

/**
 * @author RichardHuang
 */
public class CalendarInfoPOJO {

	private String dtDateStr;
	
	private String isHollday;

	/**
	 * @return the dtDateStr
	 */
	public String getDtDateStr() {
		return dtDateStr;
	}

	/**
	 * @param dtDateStr the dtDateStr to set
	 */
	public void setDtDateStr(String dtDateStr) {
		this.dtDateStr = dtDateStr;
	}

	/**
	 * @return the isHollday
	 */
	public String getIsHollday() {
		return isHollday;
	}

	/**
	 * @param isHollday the isHollday to set
	 */
	public void setIsHollday(String isHollday) {
		this.isHollday = isHollday;
	}
	
}
