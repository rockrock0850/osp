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

import com.fet.crm.osp.platform.core.excpetion.code.CoreExceptionCode;

/**
 * 
 * @author LawrenceLai
 */
public class ExcelException extends DataValidateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2695266432684669831L;

	public ExcelException(String message) {
		super(message);
		setCode(CoreExceptionCode.EXCEL_001);
	}
	
}
