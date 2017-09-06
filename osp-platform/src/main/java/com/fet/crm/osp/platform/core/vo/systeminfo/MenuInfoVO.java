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

package com.fet.crm.osp.platform.core.vo.systeminfo;

import java.math.BigDecimal;

import com.fet.crm.osp.common.vo.AbstractOspBaseVO;

/**
 * [OSP選單管理] 資訊封裝物件
 * 
 * @author LawrenceLai
 */
public class MenuInfoVO extends AbstractOspBaseVO {

	private String menuId;				// 選單代碼
	private String skillId;				// 技能代碼
	private BigDecimal sortSequence;	// 外顯排序
	private String menuText;			// 選單外顯文字
	private String menuType;			// 選單類型
	private String menuOpenType;		// 連結開啟方式
	private String menuLev;				// 選單階層
	private String menuPartent;			// 父類別選單代碼
	private String menuLink;			// 選單連結
	private String active;				// 是否有效
	private String description;			// 說明

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public BigDecimal getSortSequence() {
		return sortSequence;
	}

	public void setSortSequence(BigDecimal sortSequence) {
		this.sortSequence = sortSequence;
	}

	public String getMenuText() {
		return menuText;
	}

	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuOpenType() {
		return menuOpenType;
	}

	public void setMenuOpenType(String menuOpenType) {
		this.menuOpenType = menuOpenType;
	}

	public String getMenuLev() {
		return menuLev;
	}

	public void setMenuLev(String menuLev) {
		this.menuLev = menuLev;
	}

	public String getMenuPartent() {
		return menuPartent;
	}

	public void setMenuPartent(String menuPartent) {
		this.menuPartent = menuPartent;
	}

	public String getMenuLink() {
		return menuLink;
	}

	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
