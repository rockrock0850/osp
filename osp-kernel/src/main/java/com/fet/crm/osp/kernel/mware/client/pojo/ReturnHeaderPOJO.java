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
package com.fet.crm.osp.kernel.mware.client.pojo;

/**
 * @author RichardHuang
 */
public class ReturnHeaderPOJO {

	private String returnCode;
	
	private String returnMesg;
	
	private String reserved1;
	
	/**
	 * @return the returnCode
	 */
	public String getReturnCode() {
		return returnCode;
	}

	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	/**
	 * @return the returnMesg
	 */
	public String getReturnMesg() {
		return returnMesg;
	}

	/**
	 * @param returnMesg the returnMesg to set
	 */
	public void setReturnMesg(String returnMesg) {
		this.returnMesg = returnMesg;
	}

	/**
	 * @return the reserved1
	 */
	public String getReserved1() {
		return reserved1;
	}

	/**
	 * @param reserved1 the reserved1 to set
	 */
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

}
