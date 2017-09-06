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

package com.fet.crm.osp.platform.webapp.action.system.content;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.orderinfo.OrderManageFacade;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderInfoVO;
import com.fet.crm.osp.platform.mware.client.restful.proxy.OSPKernelRESTClientProxy;
import com.fet.crm.osp.platform.mware.client.vo.CacheSubscriberInfoVO;
import com.fet.crm.osp.platform.mware.client.vo.NEInfoVO;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.logger.WebappLoggerFactory;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;
import com.fet.crm.osp.platform.webapp.vo.session.SessionVO;

/**
 * [CONT0019_查詢APN、IP設定結果] 前端控制器 ( 參考文件: 客製頁簽說明.docx )<br>
 *
 * @author Adam Yeh
 */
@Controller
@RequestMapping("/flow/content")
public class SearchAPNIPSettingResultAction extends AbstractOSPAction {
	
	private static Logger logger = WebappLoggerFactory.getLogger(SearchAPNIPSettingResultAction.class);
	
	@Autowired
	private OrderManageFacade orderManageFacade;
	@Autowired
	private OSPKernelRESTClientProxy proxy;

    @RequestMapping(value = "/cont-0019", method = RequestMethod.POST)
    public String getContent0019() {
        return ForwardUtil.CONT0019.getView();
    }

    @RequestMapping(value = "/get-cont-0019-msisdn", method = RequestMethod.POST)
    public @ResponseBody String getContent0019Msisdn() {
    	String requseData = HttpRequestHandler.getJsonData();
    	OrderInfoVO orderInfoVO = orderManageFacade.getOrderInfo(requseData);
    	
        return orderInfoVO.getMsisdn();
    }

    @RequestMapping(value = "/get-apn-id-result", method = RequestMethod.POST)
    public @ResponseBody String getApnIpResult() {
    	Map<String, Object> requseData = HttpRequestHandler.getJsonToMap();
    	SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
    	String ntAccount = sessionVO.getUserInfoVO().getNtAccount();
    	String msisdn = MapUtils.getString(requseData, "msisdn");
    	String accountName = sessionVO.getUserInfoVO().getNtAccount();
    	String isVolteSub = MapUtils.getString(requseData, "isVolteSub");
    	
    	CacheSubscriberInfoVO cacheSubscriberInfoVO = proxy.getCacheSubscriberInfoByMsisdn(msisdn, ntAccount);
    	String paymentCategory = null, subType = null; 
    	
    	if (cacheSubscriberInfoVO != null) {
    		paymentCategory = cacheSubscriberInfoVO.getPaymentCategory();
        	subType = cacheSubscriberInfoVO.getGenerationCode();
    	}
    	
    	NEInfoVO neInfoVO = new NEInfoVO();
    	neInfoVO.setPaymentCategory(paymentCategory);
    	neInfoVO.setAccountName(accountName);
    	neInfoVO.setMsisdn(msisdn);
    	neInfoVO.setIsVolteSub(isVolteSub);
    	neInfoVO.setSubType(subType);
    	
    	logger.info("Kernel getNEInfo API Input: " + JsonUtil.toJson(neInfoVO));
    	String result = proxy.getNEInfo(neInfoVO, ntAccount);
    	
        return result;
    }

}
