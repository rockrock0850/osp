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

package com.fet.crm.osp.common.exception;

import com.fet.crm.osp.common.exception.code.IExceptionCode;

/**
 * OSP 系統基礎例外類別
 * 
 * @author PaulChen, RichardHuang
 */
public class OSPException extends RuntimeException {

	private static final long serialVersionUID = 8681279324264472593L;
	
	protected IExceptionCode code;
	
	protected OSPException(String message) {
        super(message);
    }
	
	protected OSPException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 設定錯誤代碼
	 * 
	 * @param code
	 */
	public void setCode(IExceptionCode code) {
		this.code = code;
	}

	/**
	 * 取得錯誤代碼
	 * 
	 * @return String
	 */
	public String getErrorCode() {
		return code.getErrorCode();
	}
	
	/**
	 * 取得錯誤訊息
	 * 
	 * @return String
	 */
	public String getErrorMessage() {
		return code.getErrorMessage();
	}
	
}
