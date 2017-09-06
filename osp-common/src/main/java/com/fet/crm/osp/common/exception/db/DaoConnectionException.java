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
package com.fet.crm.osp.common.exception.db;

import com.fet.crm.osp.common.exception.code.DefaultExceptionCode;

/**
 * Constructs a new exception with the specified detail message and cause
 * 
 * @author Jonathan
 */
public class DaoConnectionException extends DbException {

	private static final long serialVersionUID = -3155052134044633226L;

	public DaoConnectionException(String message, Throwable cause) {
		super(message, cause);
		
		setCode(DefaultExceptionCode.DB_003);
	}

}
