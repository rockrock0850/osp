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

package com.fet.crm.osp.platform.core.excpetion;

import org.springframework.stereotype.Component;

import com.fet.crm.osp.common.exception.ExceptionFactory;
import com.fet.crm.osp.platform.core.excpetion.system.CoreException;

/**
 * 核心組件例外轉換類別
 * 
 * @author PaulChen
 */
@Component
public class PlatformExceptionFactory extends ExceptionFactory {

	/**
	 * 轉換成「CoreException」
	 * 
	 * 補充： 為非預期之例外
	 * 
	 * @param ex
	 *            發生Exception 類別
	 * @return CoreException
	 */
	public CoreException getException(Throwable ex) {
		String message = ex.getMessage();
		Throwable cause = ex;

		CoreException coreEx = new CoreException(message, cause);
		
		return coreEx;
	}
	
}
