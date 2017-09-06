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

package com.fet.crm.osp.platform.core.service.systeminfo;

import java.util.Map;

import com.fet.crm.osp.platform.core.vo.systeminfo.MenuInfoVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.menu.MenuContentVO;
import com.fet.crm.osp.platform.core.vo.systeminfo.menu.MenuItemVO;

/**
 * [OSP選單管理] 服務介面
 * 
 * @author LawrenceLai
 */
public interface IMenuService {
	
	/**
	 *  查詢 選單 資訊<br>
	 *  條件 : 選單ID
	 * 
	 * @param menuId
	 * @return MenuInfoVO
	 */
	MenuInfoVO queryMenuInfoById(String menuId);
	
	MenuContentVO getMenuContentByUserId(String userId, String ntAccount);
	
	Map<String, MenuItemVO> getBreadcrumbInfo();

}
