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

package com.fet.crm.osp.platform.core.excpetion.validate;

import com.fet.crm.osp.common.exception.OSPException;
import com.fet.crm.osp.common.exception.code.IExceptionCode;

/**
 * 
 * @author LawrenceLai
 */
public abstract class DataValidateException extends OSPException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3918552834162225388L;

	protected DataValidateException(String errorMessage) {
		super(errorMessage);
	}
	
	protected DataValidateException(String message, Throwable cause) {
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
		return super.getErrorCode();
	}
	
	/**
	 * 取得錯誤訊息
	 * 
	 * @return String
	 */
	public String getErrorMessage() {
		String message =  this.getMessage();
		
		if (message == null && "".equals(message)) {
			message = super.getErrorMessage();
		}
		
		return message;
	}
	
}
