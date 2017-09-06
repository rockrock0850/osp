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

package com.fet.crm.osp.platform.webapp.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fet.crm.osp.platform.core.common.util.IdentifierIdUtil;

/**
 * 
 * @author LawrenceLai
 */
public class ActionContext {

	private static ActionContextThreadLocal context = new ActionContextThreadLocal();

	private String threadId;
	private HttpSession session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private ActionContext() {
		;
	}

	public static ActionContext getContext() {
		ActionContext actionCtx = context.get();

		return actionCtx;
	}
	
	/**
	 * 清除 ActionContext 物件
	 */
	public void remove() {
		context.remove();
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	private static class ActionContextThreadLocal extends ThreadLocal<ActionContext> {

		@Override
		protected ActionContext initialValue() {
			ActionContext actionCtx = new ActionContext();
			actionCtx.threadId = IdentifierIdUtil.getUuid();

			return actionCtx;
		}

	}

}
