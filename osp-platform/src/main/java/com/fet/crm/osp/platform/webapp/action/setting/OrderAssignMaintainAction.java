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

package com.fet.crm.osp.platform.webapp.action.setting;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.orderinfo.OrderManageFacade;
import com.fet.crm.osp.platform.core.facade.systeminfo.HRManageFacade;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderAssignVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.OrderInfoCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.MemberInfoVO;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * [案件指派維護] 導頁控制器
 * 
 * @author LawrenceLaiO, Adam Yeh
 */
@Controller
@RequestMapping("/setting/assign")
public class OrderAssignMaintainAction {
	
	@Autowired
	private OrderManageFacade assignManageFacade;
	@Autowired
	private HRManageFacade hrManageFacade;
	
	/**
	 * 「案件指派維護」服務頁面
	 * 
	 * @return String
	 */
	@RequestMapping(value="/service-order-assign", method = RequestMethod.GET)
	public String serviceOrderAssign() {
		return ForwardUtil.OSPLV2006.getView();
	}
	
	/**
	 * 「查詢案件指派資訊」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-order-assign-info", method = RequestMethod.POST)
	public String getOrderAssignInfo() {
		String requestData = HttpRequestHandler.getJsonData();
		
		OrderInfoCVO vo = JsonUtil.fromJson(requestData, OrderInfoCVO.class);
		List<Map<String, Object>> voList = assignManageFacade.getAssignOrderInfo(vo);
		
		String responseData = JsonUtil.toJson(voList);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 「指派人員名單」開窗頁面
	 * 
	 * @return String
	 */
	@RequestMapping(value="/assign-memeber-window", method = RequestMethod.POST)
	public String assignMemeberWindow() {
	    List<MemberInfoVO> voList = hrManageFacade.getMemberInfoBy7331Team();
		
	    String responseData = JsonUtil.toJson(voList);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 「查詢案件指派資訊」頁面
	 * 
	 * @return String
	 */
	@RequestMapping(value="/add-order-assign", method = {RequestMethod.POST})
	public String addOrderAssign() {
		String requestData = HttpRequestHandler.getJsonData();
	    String userId = HttpSessionHandler.getSessionInfo().getUserId();
		
		List<OrderAssignVO> voList = JsonUtil.fromJsonToList(requestData, OrderAssignVO.class);
	    for (OrderAssignVO vo : voList) {
			vo.setCreateUser(userId);
			vo.setUpdateUser(userId);
		}

	    boolean result = assignManageFacade.excuteOrderAssign(voList);
		
		String responseData = JsonUtil.toJson(result);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
}
