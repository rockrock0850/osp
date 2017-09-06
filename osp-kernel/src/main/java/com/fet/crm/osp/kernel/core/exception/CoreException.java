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

import com.fet.crm.osp.common.exception.OSPException;
import com.fet.crm.osp.kernel.core.exception.code.CoreExceptionCode;

/**
 * 核心組件例外封裝類別
 * 
 * @author PaulChen, RichardHuang
 */
public class CoreException extends OSPException {

	private static final long serialVersionUID = -1533323588909337277L;
	
	public CoreException(String message) {
        super(message);
        setCode(CoreExceptionCode.CORE_001);
    }

	public CoreException(String message, Throwable cause) {
		super(message, cause);
		setCode(CoreExceptionCode.CORE_001);
	}
	
}
