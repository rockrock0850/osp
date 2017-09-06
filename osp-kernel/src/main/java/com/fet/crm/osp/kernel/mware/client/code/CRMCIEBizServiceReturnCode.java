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
 * ESB CRMCIEBizService 網路服務 回傳代碼與訊息
 * 
 * @author RichardHuang
 */
public enum CRMCIEBizServiceReturnCode {

	SUCCESS("ESB-006-028-02000", "SUCCESS"), // 執行成功
    INPUT_VALUE_ERROR("ESB-006-029-02090", "Error input value"); // 無效的輸入參數
		
	private String code;
	private String message;

	private CRMCIEBizServiceReturnCode(String returnCode, String returnMessage) {
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
