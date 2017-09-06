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

package com.fet.crm.osp.kernel.core.exception;

import com.fet.crm.osp.common.exception.code.DefaultExceptionCode;

/**
 * 資料違反鍵值唯一性限制 例外封裝類別
 * 
 * @author RichardHuang
 */
public class DataDuplicateKeyException extends CoreException {

	private static final long serialVersionUID = -1533323588909337277L;

	public DataDuplicateKeyException(String message) {
		super(message);
		setCode(DefaultExceptionCode.DATA_001);
	}
	
	public DataDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
        setCode(DefaultExceptionCode.DATA_001);
    }
	
}
