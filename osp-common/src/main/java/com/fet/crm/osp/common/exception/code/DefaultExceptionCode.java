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

package com.fet.crm.osp.common.exception.code;

/**
 * 預設例外錯誤代碼
 * 
 * @author PaulChen, RichardHuang
 */
public enum DefaultExceptionCode implements IExceptionCode {

	DB_001("DB_001", "資料庫異常"),
	DB_002("DB_002", "資料庫登入驗証異常"),
	DB_003("DB_003", "資料庫連線異常"),
	MW_001("MW_001", "網路服務異常"),
	MW_002("MW_002", "網路服務連線異常"),
	DATA_001("DATA_001", "資料違反鍵值唯一性限制");
	
	protected String errorCode;
	protected String errorMessage;
	
	private DefaultExceptionCode(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

}
