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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.orderinfo.OrderManageFacade;
import com.fet.crm.osp.platform.core.vo.orderinfo.TodoOrderInfoVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.TodoOrderCVO;
import com.fet.crm.osp.platform.webapp.action.AbstractOSPAction;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * [我的待處理] 導頁控制器
 * 
 * @author LawrenceLaiO, Adam Yeh
 * @author Allen Chen at 2017-0503
 */
@Controller
@RequestMapping("/workshop/personal")
public class PersonalTodoAction extends AbstractOSPAction{
	
	@Autowired
	private OrderManageFacade orderManageFacade;
	
	/**
	 * 「我的待處理」服務頁面
	 * 
	 * @return String
	 */
	@RequestMapping(value="/service-personal-todo", method = RequestMethod.GET)
	public String servicePersonalTodo() {
		return ForwardUtil.OSPLV2001.getView();
	}

	/**
	 * 「查詢我的待處理資訊」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-personal-todo-info", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public @ResponseBody String getPersonalTodoInfo() {
		String requestData = HttpRequestHandler.getJsonData();

		TodoOrderCVO todoOrderCVO = JsonUtil.fromJson(requestData, TodoOrderCVO.class);
		
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		todoOrderCVO.setProcessUserId(userId);
		
		TodoOrderInfoVO todoOrderInfoVO = orderManageFacade.getPersonalTodoOrderInfo(todoOrderCVO);
		
		List<Map<String, Object>> todoList = todoOrderInfoVO.getTodoList();
    	int criticalOrderCount = todoOrderInfoVO.getCriticalOrderCount();
    	int overdueOrderCount = todoOrderInfoVO.getOverdueOrderCount();
		
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("voList", todoList);
		dataMap.put("criticalCount", criticalOrderCount);
		dataMap.put("overdueCount", overdueOrderCount);
		
		String responseData = JsonUtil.toJson(dataMap);
		
		return responseData;
	}

	/**
	 * 「查詢案件清單資訊」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-oder-user-info-by-user-id", method = RequestMethod.POST)
	public String getOrderSimpleInfoByUserId() {
		String userId = HttpSessionHandler.getSessionInfo().getUserId();
		List<Map<String, Object>> voList = orderManageFacade.getOrderSimpleInfoByUserId(userId);
		
		String responseData = JsonUtil.toJson(voList);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}

}
