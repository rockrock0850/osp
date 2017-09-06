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

package com.fet.crm.osp.platform.webapp.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fet.crm.osp.platform.webapp.common.ActionContext;

/**
 * 
 * @author LawrenceLai
 */
public class ScopeUtil {
	
	public static HttpSession getHttpSession() {
		return ActionContext.getContext().getSession();
	}
	
	public static HttpServletRequest getHttpRequest() {
		return ActionContext.getContext().getRequest();
	}
	
	public static HttpServletResponse getHttpResponse() {
		return ActionContext.getContext().getResponse();
	}

}
