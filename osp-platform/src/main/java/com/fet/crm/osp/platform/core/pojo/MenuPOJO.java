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

package com.fet.crm.osp.platform.core.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * [系統選單] POJO物件
 * 
 * @author LawrenceLai
 */
public class MenuPOJO {

	private String menuId;
	private String skillId;
	private BigDecimal sortSequence;
	private String menuText;
	private String menuType;
	private String menuOpenType;
	private String menuLev;
	private String menuPartent;
	private String menuLink;
	private String active;
	private String description;
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;
	private String menuIcon;

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

}
