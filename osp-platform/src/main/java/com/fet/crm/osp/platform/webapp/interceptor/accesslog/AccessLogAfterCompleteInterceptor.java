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
public class AccessLogAfterCompleteInterceptor extends SimpleAccessLogInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exp)
			throws Exception {
		super.writeAccessLog();
		super.afterCompletion(request, response, handler, exp);
	}
	
}
