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

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fet.crm.osp.platform.SpringTest;
import com.fet.crm.osp.platform.core.common.util.JsonUtil;
import com.fet.crm.osp.platform.core.vo.systeminfo.MenuInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.menu.MenuContentVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.menu.MenuItemVO;

/**
 * [選單管理] 總體服務窗口 測試單元
 * 
 * @author LawrenceLai
 */
public class MenuManageFacadeTest extends SpringTest {
	
	@Autowired
	private MenuManageFacade facade;

	@Test
	public void testGetMenuInfoById() {
		String menuId = "OSPL2006";
		
		MenuInfoVO vo = facade.getMenuInfoById(menuId);
		
		print2Json(vo);
	}
	
	@Test
	public void testGetMenuContentByUserId() {
		String userId = "65196";
		String ntAccount = "pcyang";
		
		MenuContentVO vo = facade.getMenuContentByUserId(userId, ntAccount);
		
		print2Json(vo);
	}
	
	@Test
	public void testGetBreadcrumbByMenuId() {
		Map<String, MenuItemVO> map = facade.getBreadcrumbInfo();
		
		for (String key : map.keySet()) {
			System.out.println("==================================================");
			System.out.println(key);
			System.out.println(JsonUtil.toJson(map.get(key)));
			System.out.println("==================================================");
		}
	}

}
