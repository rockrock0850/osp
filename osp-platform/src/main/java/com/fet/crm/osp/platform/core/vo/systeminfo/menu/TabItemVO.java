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

package com.fet.crm.osp.platform.core.vo.systeminfo.menu;

import java.util.List;

/**
 * 
 * @author LawrenceLai
 */
public class TabItemVO extends AbstractMenuBaseVO {

	private String tabId;
	private String tabText;
	private List<MenuItemVO> menuList;

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	public String getTabText() {
		return tabText;
	}

	public void setTabText(String tabText) {
		this.tabText = tabText;
	}

	public int getSortSequence() {
		return sortSequence;
	}

	public void setSortSequence(int sortSequence) {
		this.sortSequence = sortSequence;
	}

	public List<MenuItemVO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuItemVO> menuList) {
		this.menuList = menuList;
	}

}
