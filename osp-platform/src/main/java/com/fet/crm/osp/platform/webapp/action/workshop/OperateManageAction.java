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

package com.fet.crm.osp.platform.webapp.action.workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.orderinfo.OperateManageFacade;
import com.fet.crm.osp.platform.core.vo.systeminfo.OperateManageVO;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;

/**
 * [特殊案件] 導頁控制器
 * 
 * @author LawrenceLai, Adam Yeh
 */
@Controller
@RequestMapping("/workshop/special")
public class OperateManageAction extends AbstractOSPAction{
	
	@Autowired
	private OperateManageFacade specialOrderProcessfacade;
	
	/**
	 * 「特殊案件」服務頁面
	 * 
	 * @return String
	 */
	@RequestMapping(value="/service-set-special-order-process", method = RequestMethod.POST)
	public @ResponseBody boolean serviceSetSpecialOrderProcess() {
		String requestData = HttpRequestHandler.getJsonData();
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		
		OperateManageVO vo = JsonUtil.fromJson(requestData, OperateManageVO.class);
		vo.setUserId(userId);
		boolean result = specialOrderProcessfacade.createOperateContent(vo);
		
		return result;
	}

}
