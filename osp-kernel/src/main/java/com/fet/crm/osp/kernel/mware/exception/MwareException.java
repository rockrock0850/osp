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

package com.fet.crm.osp.kernel.mware.exception;

import com.fet.crm.osp.common.exception.OSPException;
import com.fet.crm.osp.kernel.mware.exception.code.MwareExceptionCode;

/**
 * @author RichardHuang
 */
public class MwareException extends OSPException {

	private static final long serialVersionUID = 3659657547055852360L;
	
	public MwareException(String message) {
        super(message);
        
        super.code = MwareExceptionCode.CORE_001;
    }

	public MwareException(String message, Throwable cause) {
    	super(message, cause);
		
		super.code = MwareExceptionCode.CORE_001;
    }

}
