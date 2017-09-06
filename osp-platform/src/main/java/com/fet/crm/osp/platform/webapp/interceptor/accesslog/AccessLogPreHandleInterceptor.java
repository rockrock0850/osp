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

package com.fet.crm.osp.platform.webapp.interceptor.accesslog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日誌 攔截器
 * 
 * 說明：
 *     在「preHandle」階段寫入日誌（access log）
 * 
 * @author PaulChen
 */
public class AccessLogPreHandleInterceptor extends SimpleAccessLogInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean isContienu = super.preHandle(request, response, handler); 
		// 寫入存取日誌
		super.writeAccessLog();
		
		return isContienu;
	}
	
}
