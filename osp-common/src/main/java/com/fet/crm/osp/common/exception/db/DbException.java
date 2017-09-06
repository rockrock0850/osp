/**
 * Copyright (c) 2014 Far Eastone Telecommunications Co., Ltd.
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

import com.fet.crm.osp.common.exception.OSPException;
import com.fet.crm.osp.common.exception.code.DefaultExceptionCode;

/**
 * The Class AbstractDbException.
 *
 * @author PaulChen, RichardHuang
 */
public abstract class DbException extends OSPException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8365712491357467658L;
	
	public DbException(String message) {
        super(message);
        setCode(DefaultExceptionCode.DB_001);
    }
	
	/**
	 * Instantiates a new abstract db exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DbException(String message, Throwable cause) {
		super(message, cause);
		setCode(DefaultExceptionCode.DB_001);
	}
	
}
