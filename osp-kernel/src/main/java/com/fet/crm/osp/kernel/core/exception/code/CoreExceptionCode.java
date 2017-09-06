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

package com.fet.crm.osp.kernel.core.exception.code;

import com.fet.crm.osp.common.exception.code.IExceptionCode;

/**
 * OSP 核心階層 錯誤代碼
 * 
 * @author PaulChen, RichardHuang
 */
public enum CoreExceptionCode implements IExceptionCode {

	SYS_001("SYS_001", "系統異常"),
	CORE_001("CORE_001", "核心組件異常");
	
	protected String errorCode;
	protected String errorMessage;

	private CoreExceptionCode(String errorCode, String errorMessage) {
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
