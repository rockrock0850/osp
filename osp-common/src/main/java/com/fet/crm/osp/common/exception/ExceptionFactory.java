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

import org.springframework.dao.DataAccessException;

import com.fet.crm.osp.common.exception.db.DaoAuthenticationException;
import com.fet.crm.osp.common.exception.db.DaoConnectionException;
import com.fet.crm.osp.common.exception.db.DaoDataException;
import com.fet.crm.osp.common.exception.db.DaoSchemaException;
import com.fet.crm.osp.common.exception.db.DbException;
import com.fet.crm.osp.common.exception.db.DefaultDaoException;

/**
 * 核心組件例外轉換類別
 * 
 * @author PaulChen
 */
public class ExceptionFactory {

	public ExceptionFactory() {
		;
	}

	/**
	 * 將「DataAccessException」轉換為可明確得知資料庫異常訊息
	 * 
	 * @param dae
	 *            Spring 封裝例外
	 * @return DbException
	 */
	public DbException getException(DataAccessException dae) {
		String message = dae.getMessage();
		Throwable cause = dae;

		DbException dbException = null;

		if (message != null && message.length() != 0) {
			if (-1 != message.indexOf("ORA-01017")) {
				dbException = new DaoAuthenticationException(message, cause);
			} else if (-1 != message.indexOf("ORA-00904") || -1 != message.indexOf("ORA-00942")) {
				dbException = new DaoSchemaException(message, cause);
			} else if (-1 != message.indexOf("The Network Adapter could not establish the connection")) {
				dbException = new DaoConnectionException(message, cause);
			} else if (-1 != message.indexOf("ORA-12899")) {
				dbException = new DaoDataException(message, cause);
			}
		}

		if (dbException == null) {
			dbException = new DefaultDaoException(message, cause);
		}

		return dbException;
	}

}
