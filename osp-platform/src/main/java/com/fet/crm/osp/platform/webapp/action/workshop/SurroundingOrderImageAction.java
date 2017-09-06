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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * 
 * @author LawrenceLai
 */
@Controller
@RequestMapping("/workshop/surrounding-image")
public class SurroundingOrderImageAction {
	
	@RequestMapping(value = "/faxserver", method = {RequestMethod.POST, RequestMethod.GET})
	public String serviceFaxServerImage(@RequestParam String apId, @RequestParam String ntAccount) {
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		
		System.out.println("=================================================================");
		System.out.println("userId = " + userId);
		System.out.println("imgIdAPID = " + apId);
		System.out.println("ntAccount = " + ntAccount);
		System.out.println("=================================================================");
		
		HttpRequestHandler.put("apId", apId);
		HttpRequestHandler.put("ntAccount", ntAccount);
		
		return ForwardUtil.APPPARTVIEW.getView();
	}

}
