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

import java.util.Date;
import java.util.Enumeration;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fet.crm.osp.platform.core.common.LogContext;
import com.fet.crm.osp.platform.core.facade.systeminfo.AccessLogFacade;
import com.fet.crm.osp.platform.core.vo.systeminfo.LogInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.UserInfoVO;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.logger.WebappLoggerFactory;

/**
 * 日誌 攔截器
 * 
 * 說明：
 *      此為基礎類別，寫入時間點交由子類別決定
 * 
 * @author LawrenceLai
 */
public class SimpleAccessLogInterceptor implements HandlerInterceptor {

    private static Logger logger = WebappLoggerFactory.getLogger(SimpleAccessLogInterceptor.class);
    
    @Autowired
    private AccessLogFacade accessLogFacade;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exp)
            throws Exception {
    	// nop
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
            throws Exception {
    	Enumeration<String> attributeNames = request.getAttributeNames();
    	
    	while(attributeNames.hasMoreElements()) {
    		String name = attributeNames.nextElement();
    		
    		// log排除spring框架套件
    		if (name.startsWith("org.springframework")) {
    			continue;
    		}
    		
    		logger.info(name + " = " + request.getAttribute(name));
    	}
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	initLogContext(request);
    	
    	Set<String> names = request.getParameterMap().keySet();
    	
    	for (String name : names) {
    		logger.info(name + " = "+ request.getParameter(name));
    	}
    	
        return true;
    }
    
    /**
     * 寫入存取日誌
     * 
     * @throws Exception
     */
    protected void writeAccessLog() throws Exception {
    	try {
    		UserInfoVO userInfo = HttpSessionHandler.getSessionInfo().getUserInfoVO();
    		String userId = userInfo.getUserId();
    		
    		LogContext logCtx = LogContext.getContext();
    		logCtx.setUserId(userId);
    		logCtx.setEndTime(System.currentTimeMillis());
    		
    		LogInfoVO logInfo = new LogInfoVO(LogContext.getContext());
    		
    		accessLogFacade.accessLog(logInfo);
    	} finally {
    		cleanLogContext();
    	}
    }
    
    /**
	 * 初使化「LogContext」物件
	 * 
	 * @param ai
	 */
	private void initLogContext(HttpServletRequest request) {
		// 取得發送 request ip 位址
		String ipAddress = request.getRemoteAddr();
	
		String requestContent = HttpRequestHandler.getJsonData();
		
		LogContext logCtx = LogContext.getContext();
		logCtx.setLogDate(new Date());
		logCtx.setIpAddress(ipAddress);
		logCtx.setStartTime(System.currentTimeMillis());
		logCtx.setRequestContent(requestContent);
		
		processClassify(request);
		
//		String operation = logCtx.getOperation();
//		String classify = logCtx.getClassify();
	}
	
	/**
	 * 清除「LogContext」 物件
	 */
	private void cleanLogContext() {
		LogContext.getContext().remove();
	}
    
	/**
	 * 取得 Action 上類別（Category）、動作（Operation）設定
	 * 
	 * @param ai
	 */
	private void processClassify(HttpServletRequest request) {
		LogContext logCtx = LogContext.getContext();
		
		String category = (String) request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingPattern");
		String operation = (String) request.getAttribute("org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping");;
		
		logCtx.setClassify(category);
		logCtx.setOperation(operation);
	}

}
