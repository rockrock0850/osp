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

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 使用者資訊封裝物件
 * 
 * @author V.J CHOU
 */
public class UserInfoVO {

	private String userId; 				// 使用者代碼
	private String userNm; 				// 使用者名稱
	private String ntAccount; 			// NT ACCTOUNT
	private String mail; 				// 使用者登入電子郵件
	private String shiftType;			// 班別
	private List<RoleInfoVO> roleList;  // 角色資訊清單
	
	private boolean isPass = false; // 是否LDAP驗証成功

	public UserInfoVO() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public boolean isPass() {
		return isPass;
	}

	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}

	public String getNtAccount() {
		return ntAccount;
	}

	public void setNtAccount(String ntAccount) {
		this.ntAccount = ntAccount;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getShiftType() {
		return shiftType;
	}

	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}

	public List<RoleInfoVO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleInfoVO> roleList) {
		this.roleList = roleList;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
