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

package com.fet.crm.osp.kernel.mware.client.code;

/**
 * ESB DecisionDataService 網路服務 回傳代碼與訊息
 * 
 * @author RichardHuang
 */
public enum QueryTicketDataServiceReturnCode {

	SUCCESS("00", "成功"),
	EMPTY_PARAM_ERROR("01", "缺少必填欄位"),
	FORMAT_ERROR("02", "格式錯誤"),
	NO_DATA("03", "主工單資訊無法取得"),
	SYSTEM_ERROR("04", "系統錯誤"),
	AUTHORITY_ERROR("05", "無使用權限"),
	UNKNOWN_ERROR("09", "其他");
    
	private String code;
	private String message;

	private QueryTicketDataServiceReturnCode(String returnCode, String returnMessage) {
		this.code = returnCode;
		this.message = returnMessage;
	}

	/**
	 * 取得回傳代碼
	 * 
	 * @return String - 回傳代碼
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 取得回傳訊息
	 * 
	 * @return String - 回傳訊息
	 */
	public String getMessage() {
		return message;
	}
	
}
