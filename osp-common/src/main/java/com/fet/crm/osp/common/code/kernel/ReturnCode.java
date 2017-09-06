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

package com.fet.crm.osp.common.code.kernel;

/**
 * OSP 網路服務 回傳代碼與訊息
 * 
 * @author RichardHuang
 */
public enum ReturnCode {

	SUCCESS("001", "Success"), // 執行成功
	NO_DATA("002", "No matching result was found"), // 不存在符合條件的資料
	DB_CONNECTION_ERROR("080", "Database connection failed"), // 資料庫連線失敗
	DB_SYS_ERROR("081", "Database system error"), // 資料庫系統異常
	DATA_DUPLICATE_KEY_ERROR("082", "Duplicate key error when insert data"), // 新增資料時發生 key 值重複的錯誤
	DATA_FORMAT_ERROR("090", "Data format error"), // 資料格式錯誤
	PARAM_ERROR("091", "Invalid parameter value"), // 輸入參數資料驗證失敗
	AUTH_ERROR("092", "Web Services authentication failed"), // 網路服務安全認證失敗
	ESB_CONNECTION_ERROR("093", "ESB Web Services connection failed"), // ESB 遠端網路服務連線失敗
	ESB_API_ERROR("094", "ESB Web Services API error"), // ESB 遠端網路服務執行錯誤
	HTTP_CONNECTION_ERROR("095", "Http web connection failed"), // Http 網路服務連線失敗
	ERROR("099", "Internal error"); // 其他非預期或需處理之錯誤
		
	private String code;
	private String message;

	private ReturnCode(String returnCode, String returnMessage) {
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
