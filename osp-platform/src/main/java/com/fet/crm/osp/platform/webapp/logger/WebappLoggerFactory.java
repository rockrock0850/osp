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

package com.fet.crm.osp.platform.webapp.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fet.crm.osp.platform.webapp.logger.WebappLoggerFactory;

/**
 * 
 * @author LawrenceLai
 */
public class WebappLoggerFactory {
	
	/**
	 * 
	 * @return Logger
	 */
	public static Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}

}
