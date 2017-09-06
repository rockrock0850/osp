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

package com.fet.crm.osp.platform.webapp.handler;

import javax.servlet.http.HttpSession;

import com.fet.crm.osp.platform.core.common.Context;
import com.fet.crm.osp.platform.webapp.common.ActionContext;
import com.fet.crm.osp.platform.webapp.vo.session.SessionVO;

/**
 * 
 * @author LawrenceLai
 */
public class HttpSessionHandler {
	
	public static HttpSession getHttpSession() {
		return ActionContext.getContext().getSession();
	}
	
	public static SessionVO getSessionInfo() {
		return (SessionVO) get(Context.S_SESSION_KEY);
	}
	
	public static void putSessionInfo(SessionVO sVO) {
		put(Context.S_SESSION_KEY, sVO);
	}
	
	public static Object get(String key) {
		return getHttpSession().getAttribute(key);
	}
	
	public static void put(String key, Object obj) {
		getHttpSession().setAttribute(key, obj);
	}
	
	public static void destory() {
		getHttpSession().invalidate();
	}

}
