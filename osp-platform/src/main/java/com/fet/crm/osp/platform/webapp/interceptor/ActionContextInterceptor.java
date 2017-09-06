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

package com.fet.crm.osp.platform.webapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fet.crm.osp.platform.webapp.common.ActionContext;

/**
 * ActionContext 攔截器
 * 
 * @author LawrenceLai
 */
public class ActionContextInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		cleanActionContext();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		initActionContext(request, response);

		return true;
	}

	/**
	 * 初使化「ActionContext」物件
	 * 
	 * @param req
	 * @param resp
	 */
	private void initActionContext(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		ActionContext actionCtx = ActionContext.getContext();
		actionCtx.setSession(session);
		actionCtx.setRequest(request);
		actionCtx.setResponse(response);
	}
	
	/**
	 * 清除「ActionContext」 物件
	 */
	private void cleanActionContext() {
		ActionContext.getContext().remove();
	}

}
