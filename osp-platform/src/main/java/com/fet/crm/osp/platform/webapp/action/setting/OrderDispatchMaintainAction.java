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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.dispatchinfo.DispatchManageFacade;
import com.fet.crm.osp.platform.core.facade.systeminfo.HRManageFacade;
import com.fet.crm.osp.platform.core.vo.orderinfo.OrderDispatchVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.PauseDispatchVO;
import com.fet.crm.osp.platform.core.vo.orderinfo.condition.OrderDispatchCVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.MemberInfoVO;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;

/**
 * [暫停人員分派維護作業] 導頁控制器
 * 
 * @author LawrenceLai, Adam Yeh
 */
@Controller
@RequestMapping("/setting/dispatch")
public class OrderDispatchMaintainAction {

	@Autowired
	private DispatchManageFacade dispatchManageFacade;
	@Autowired
	private HRManageFacade hrManageFacade;
	
	/**
	 * 「暫停人員分派維護作業」服務頁面
	 * 
	 * @return String
	 */
	@RequestMapping(value="/service-order-dispatch", method = RequestMethod.GET)
	public String orderDispatchMaintain() {
		return ForwardUtil.OSPLV2007.getView();
	}
	
	/**
	 * 「查詢暫停人員分派維護作業」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-order-dispatch-info", method = RequestMethod.POST)
	public String getOrderDispatchInfo() {
		String requestData = HttpRequestHandler.getJsonData();
		
		OrderDispatchCVO vo = JsonUtil.fromJson(requestData, OrderDispatchCVO.class);
		List<OrderDispatchVO> voList = dispatchManageFacade.getDispatchInfo(vo);
		
		String responseData = JsonUtil.toJson(voList);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 「查詢分派人選名單」開窗頁面
	 * 
	 * @return String
	 */
	@RequestMapping(value="/order-dispatch-window", method = RequestMethod.POST)
	public String orderDispatchWindow() {
	    List<MemberInfoVO> voList = hrManageFacade.getMemberInfoBy7331Team();
	
		String responseData = JsonUtil.toJson(voList);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}
	
	/**
	 * 「查詢暫停人員分派維護作業」頁面
	 * 
	 * @return String
	 */
	@RequestMapping(value="/add-order-dispatch", method = RequestMethod.POST)
	public String addOrderDispatch() {
		String requestData = HttpRequestHandler.getJsonData();
	    String userId = HttpSessionHandler.getSessionInfo().getUserId();
		
		List<PauseDispatchVO> voList = JsonUtil.fromJsonToList(requestData, PauseDispatchVO.class);
	    for (PauseDispatchVO vo : voList) {
			vo.setCreateUser(userId);
			vo.setUpdateUser(userId);
		}
	    
		boolean dataMap = dispatchManageFacade.excutePauseDispatch(voList);
		
		String responseData = JsonUtil.toJson(dataMap);
		HttpRequestHandler.put("responseData", responseData);
		
		return ForwardUtil.JSON.getView();
	}

}
