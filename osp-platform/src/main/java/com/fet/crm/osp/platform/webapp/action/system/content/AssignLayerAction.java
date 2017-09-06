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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fet.crm.osp.platform.core.facade.orderinfo.OrderManageFacade;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderInfoVO;
import com.fet.crm.osp.platform.mware.client.restful.proxy.OSPKernelRESTClientProxy;
import com.fet.crm.osp.platform.mware.client.vo.AuthLevelInfoReturnVO;
import com.fet.crm.osp.platform.mware.client.vo.AuthLevelInfoVO;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.logger.WebappLoggerFactory;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;
import com.fet.crm.osp.platform.webapp.vo.session.SessionVO;

/**
 * [CONT0026_簽核層級] 前端控制器 ( 參考文件: 尚未製作文件 )<br>
 *
 * @author Adam Yeh
 */
@Controller
@RequestMapping("/flow/content")
public class AssignLayerAction extends AbstractOSPAction {

	private static Logger logger = WebappLoggerFactory.getLogger(AssignLayerAction.class);

	@Autowired
	private OrderManageFacade orderManageFacade;
	@Autowired
	private OSPKernelRESTClientProxy proxy;

    @RequestMapping(value = "/cont-0026", method = RequestMethod.POST)
    public String getContent0026() {
        return ForwardUtil.CONT0026.getView();
    }

    @RequestMapping(value = "/get-assign-level-info", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody AuthLevelInfoReturnVO getAssignLevelInfo() {
    	SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
    	String ntAccount = sessionVO.getUserInfoVO().getNtAccount();
    	String orderMId = HttpRequestHandler.getJsonData();
    	logger.info("getAssignLevelInfo input: " + orderMId);
    	
    	OrderInfoVO orderInfoVO = orderManageFacade.getOrderInfo(orderMId);
    	String ivrCode = orderInfoVO.getIvrCode();
    	String rocId = orderInfoVO.getCustId();
    	
    	if (StringUtils.isBlank(ivrCode) || StringUtils.isBlank(rocId)) {
    		return null;
    	}
    	
    	AuthLevelInfoVO paramVO = new AuthLevelInfoVO();
    	paramVO.setIvrCode(ivrCode);
    	AuthLevelInfoReturnVO vo = proxy.getAuthLevelInfo(paramVO, ntAccount);
    	logger.info("getAssignLevelInfo output: " + vo);

        return vo;
    }

}
