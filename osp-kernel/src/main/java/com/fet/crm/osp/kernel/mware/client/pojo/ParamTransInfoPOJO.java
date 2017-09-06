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
public class ParamTransInfoPOJO {
	
	private String transChannel;
	
	private String transUser;
	
	private String transBPID;
	
	private String transPath;

	/**
	 * @return the transChannel
	 */
	public String getTransChannel() {
		return transChannel;
	}

	/**
	 * @param transChannel the transChannel to set
	 */
	public void setTransChannel(String transChannel) {
		this.transChannel = transChannel;
	}

	/**
	 * @return the transUser
	 */
	public String getTransUser() {
		return transUser;
	}

	/**
	 * @param transUser the transUser to set
	 */
	public void setTransUser(String transUser) {
		this.transUser = transUser;
	}

	/**
	 * @return the transBPID
	 */
	public String getTransBPID() {
		return transBPID;
	}

	/**
	 * @param transBPID the transBPID to set
	 */
	public void setTransBPID(String transBPID) {
		this.transBPID = transBPID;
	}

	/**
	 * @return the transPath
	 */
	public String getTransPath() {
		return transPath;
	}

	/**
	 * @param transPath the transPath to set
	 */
	public void setTransPath(String transPath) {
		this.transPath = transPath;
	}

}
