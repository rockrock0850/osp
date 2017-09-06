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

package com.fet.crm.osp.platform.webapp.action.system;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.facade.orderinfo.OrderManageFacade;
import com.fet.crm.osp.platform.core.facade.systeminfo.MenuManageFacade;
import com.fet.crm.osp.platform.core.vo.systeminfo.MenuInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.UserInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.menu.MenuContentVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.menu.MenuItemVO;
import com.fet.crm.osp.platform.webapp.handler.HttpRequestHandler;
import com.fet.crm.osp.platform.webapp.handler.HttpSessionHandler;
import com.fet.crm.osp.platform.webapp.result.ForwardUtil;
import com.fet.crm.osp.platform.webapp.vo.session.SessionVO;

/**
 * 
 * @author LawrenceLai
 */
@Controller
@RequestMapping("/")
public class ConsoleAction {
	
	@Autowired
	MenuManageFacade menuManageFacade;

	@Autowired
	private OrderManageFacade orderManageFacade;
	
	@RequestMapping(value="/console-index", method = RequestMethod.GET)
	public String serviceConsoleView() {
		SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
		UserInfoVO userInfoVO = sessionVO.getUserInfoVO();
		
		String userId = userInfoVO.getUserId();
		String ntAccount = userInfoVO.getNtAccount();
		
		MenuContentVO menuContentVO = menuManageFacade.getMenuContentByUserId(userId, ntAccount);
		
		HttpRequestHandler.put("menuContent", menuContentVO);
		
		// 暫時將首頁導至[我的待處理]功能頁面
		indexToOSPL2001();

		return "tiles.console.index";
	}
	
	@RequestMapping(value="/console-panel", method = RequestMethod.GET)
	public String servicePanelContent() {
		SessionVO sessionVO = HttpSessionHandler.getSessionInfo();
		UserInfoVO userInfoVO = sessionVO.getUserInfoVO();
		
		String userId = userInfoVO.getUserId();
		String ntAccount = userInfoVO.getNtAccount();
		
		Map<String, Object> paramMap = HttpRequestHandler.getJsonToMap();
		String menuId = MapUtils.getString(paramMap, "menuId", "");
		
		MenuContentVO menuContentVO = menuManageFacade.getMenuContentByUserId(userId, ntAccount);
		
		MenuInfoVO menuInfo = menuManageFacade.getMenuInfoById(menuId);
		
		HttpRequestHandler.put("menuContent", menuContentVO);
		HttpRequestHandler.put("menuInfo", menuInfo);
		
		tearBreadcrumb(menuId);
		
		return "tiles.console.index";
	}
	
	/**
	 * 「提醒新派件 gritter」
	 * 
	 * @return String
	 */
	@RequestMapping(value="/get-remind-flag", method = RequestMethod.GET)
	public String getRemindFlag() {
		String userId = HttpSessionHandler.getSessionInfo().getUserInfoVO().getUserId();

		boolean flag = orderManageFacade.getDispatchNotify(userId);

        String responseData = JsonUtil.toJson(flag);

        HttpRequestHandler.put("responseData", responseData);

        return ForwardUtil.JSON.getView();
	}
	
	private void indexToOSPL2001() {
		String menuId = "OSPL2001";
		
		MenuInfoVO menuInfo = menuManageFacade.getMenuInfoById(menuId);
		
		HttpRequestHandler.put("menuInfo", menuInfo);
		
		tearBreadcrumb(menuId);
	}
	
	/**
	 * 設定頁面麵包屑資訊
	 * 
	 * @param menuId
	 */
	private void tearBreadcrumb(String menuId) {
		MenuItemVO breadcrumbInfo = menuManageFacade.getBreadcrumbInfo().get(menuId);
		
		HttpRequestHandler.put("breadcrumbInfo", breadcrumbInfo);
	}
	
}
