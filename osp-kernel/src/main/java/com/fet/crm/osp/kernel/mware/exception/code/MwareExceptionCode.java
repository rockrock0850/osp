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

package com.fet.crm.osp.kernel.mware.exception.code;

import com.fet.crm.osp.common.exception.code.IExceptionCode;

/**
 * OSP 中介階層 錯誤代碼
 * 
 * @author RichardHuang
 */
public enum MwareExceptionCode implements IExceptionCode {

	SYS_001("SYS_001", "系統異常"),
	CORE_001("CORE_001", "核心組件異常"),
	DB_001("DB_001", "資料庫異常"),
	DB_002("DB_002", "資料庫登入驗証異常"),
	DB_003("DB_003", "資料庫連線異常"),
    ESB_001("ESB_001", "ESB 遠端網路服務連線失敗"),
    ESB_002("ESB_002", "ESB 遠端網路服務執行錯誤"),
    CAR_001("CAR_001", "CAR 遠端網路服務執行失敗"),
    HTTP_001("HTTP_001", "HTTP 請求失敗"),
    DATA_001("DATA_001", "資料格式錯誤");
	
	protected String errorCode;
	protected String errorMessage;

	private MwareExceptionCode(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
