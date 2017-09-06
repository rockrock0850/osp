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

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.webapp.common.util.ScopeUtil;

/**
 * 
 * @author LawrenceLai
 */
public class HttpRequestHandler {
	
	public static String getRequestURL() {
		HttpServletRequest req = ScopeUtil.getHttpRequest();
	 	StringBuffer sb = req.getRequestURL(); 
		return sb.toString();
	}
	
	public static boolean isAjaxReq() {
		HttpServletRequest req = ScopeUtil.getHttpRequest();
		String headerVal = req.getHeader("X-Requested-With");
		// 若有值，則為 ajax request.
		return StringUtils.isNotBlank(headerVal);
	}
	
	public static String getJsonData() {
		HttpServletRequest request = ScopeUtil.getHttpRequest();
		
		return request.getParameter("jsonData");
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getJsonToMap() {
		HttpServletRequest request = ScopeUtil.getHttpRequest();
		String jsonData = request.getParameter("jsonData");
		
		if (StringUtils.isNotBlank(jsonData)) {
			return JsonUtil.fromJson(jsonData, Map.class);
		}
			
		return Collections.EMPTY_MAP;
	}
	
	public static void put(String key, Object value) {
		HttpServletRequest request = ScopeUtil.getHttpRequest();
		request.setAttribute(key, value);
	}
	
}
