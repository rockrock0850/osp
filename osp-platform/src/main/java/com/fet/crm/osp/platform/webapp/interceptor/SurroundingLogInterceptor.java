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

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fet.crm.nsp.generic.model.cache.Agent;
import com.fet.crm.nsp.generic.model.cache.OwnInfo;
import com.fet.crm.nsp.generic.model.cache.defined.UsingMsisdn;
import com.fet.crm.nsp.generic.model.cache.profile.CRMProfileIdViewInfo;
import com.fet.crm.nsp.generic.util.NSPCacheUtil;
import com.fet.crm.osp.platform.core.common.util.IdentifierIdUtil;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.systeminfo.AccessLogFacade;
import com.fet.crm.osp.platform.core.vo.systeminfo.SurroundingDetailLogVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.SurroundingMainLogVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.buzflow.component.BuzStepPageVO;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.logger.WebappLoggerFactory;

/**
 * 
 * @author LawrenceLai
 */
public class SurroundingLogInterceptor implements HandlerInterceptor {
	
    private static Logger logger = WebappLoggerFactory.getLogger(SurroundingLogInterceptor.class);
	
	@Autowired
	private AccessLogFacade accessLogFacade;

	@SuppressWarnings("unchecked")
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String usserId = HttpSessionHandler.getSessionInfo().getUserId();
		String surroundingMId = IdentifierIdUtil.getUuid();
		Date sysDate = new Date();
		
		SurroundingMainLogVO mainLogVO = new SurroundingMainLogVO();
		mainLogVO.setSurroundingMId(surroundingMId);
		mainLogVO.setCreateUser(usserId);
		mainLogVO.setCreateDate(sysDate);
		
		retrieveDataCache(mainLogVO);
		
		Enumeration<String> attributeNames = request.getAttributeNames();
    	
    	while(attributeNames.hasMoreElements()) {
    		String name = attributeNames.nextElement();
    		
    		if (name.equals("orderMId")) {
    			String orderMId = (String) request.getAttribute(name);
    			mainLogVO.setOrderMId(orderMId);
    		}
    		
    		// log排除spring框架套件
    		if (name.equals("stepPageLs")) {
    			List<BuzStepPageVO> stepPageLs = (List<BuzStepPageVO>) request.getAttribute(name);
    			
    			if (CollectionUtils.isNotEmpty(stepPageLs)) {
    				List<SurroundingDetailLogVO> detailLogList = new ArrayList<>();
    				
    				for (BuzStepPageVO stepPageVO : stepPageLs) {
    		    		String surroundingDId = IdentifierIdUtil.getUuid();
    		    		String contentId = stepPageVO.getContentId();
    		    		String contentName = stepPageVO.getContentNm();
    		    		String link = stepPageVO.getLink();
    		    		String parameter = stepPageVO.getParameter();
    		    		
    		    		SurroundingDetailLogVO detailLogVO = new SurroundingDetailLogVO();
    		    		detailLogVO.setSurroundingMId(surroundingMId);
    		    		detailLogVO.setSurroundingDId(surroundingDId);
    		    		detailLogVO.setContentId(contentId);
    		    		detailLogVO.setContentName(contentName);
    		    		detailLogVO.setLink(link);
    		    		detailLogVO.setParameter(parameter);
    		    		detailLogVO.setCreateUser(usserId);
    		    		detailLogVO.setCreateDate(sysDate);
    		    		
    		    		detailLogList.add(detailLogVO);
        			}
    				
    				mainLogVO.setDetailLogList(detailLogList);
    			}
    		}
    	}
    	
		accessLogFacade.createSurroundingLog(mainLogVO);
		
		logger.info("==================================================");
		logger.info(JsonUtil.toJson(mainLogVO));
		logger.info("==================================================");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	private void retrieveDataCache(SurroundingMainLogVO mainLogVO) {
		String ownId = HttpSessionHandler.getSessionInfo().getOwnId();
		String headId, agentId = null;
		
		OwnInfo ownInfo = NSPCacheUtil.getOwnInfo(ownId);
		headId = ownInfo.getHeadId();
		agentId = ownInfo.getAgentId();
		
		mainLogVO.setOwnId(ownId);
		mainLogVO.setOwnInfo(JsonUtil.toJson(ownInfo));
		
		if (StringUtils.isNotBlank(agentId)) {
			Agent agnet = NSPCacheUtil.getAgent(agentId);
			
			mainLogVO.setAgentInfo(JsonUtil.toJson(agnet));
		}
		if (StringUtils.isNotBlank(headId)) {
			UsingMsisdn usingMsisdn = NSPCacheUtil.getUsingMsisdn(headId);
			CRMProfileIdViewInfo profileIdViewInfo = NSPCacheUtil.getCRMProfile(headId).getCrmProfileIdViewInfo();
			
			mainLogVO.setUsingMsisdn(JsonUtil.toJson(usingMsisdn));
			mainLogVO.setCrmProfileIdviewInfo(JsonUtil.toJson(profileIdViewInfo));
		}
		
	}
	
}
