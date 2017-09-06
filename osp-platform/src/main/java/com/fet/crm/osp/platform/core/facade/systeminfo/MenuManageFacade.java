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

package com.fet.crm.osp.platform.core.facade.systeminfo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fet.crm.osp.platform.core.service.systeminfo.IMenuService;
import com.fet.crm.osp.platform.core.vo.systeminfo.MenuInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.menu.MenuContentVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.menu.MenuItemVO;

/**
 * 「選單管理」總體服務窗口
 * 
 * @author LawrenceLai
 */
@Service
public class MenuManageFacade {

	@Autowired
	private IMenuService menuService;
	
	/**
	 *  查詢 選單 資訊<br>
	 *  條件 : 選單ID
	 * 
	 * @param menuId
	 * @return MenuInfoVO
	 */
	public MenuInfoVO getMenuInfoById(String menuId) {
		return menuService.queryMenuInfoById(menuId);
	}
	
	public MenuContentVO getMenuContentByUserId(String userId, String ntAccount) {
		return menuService.getMenuContentByUserId(userId, ntAccount);
	}
	
	public Map<String, MenuItemVO> getBreadcrumbInfo() {
		return menuService.getBreadcrumbInfo();
	}
	
}
