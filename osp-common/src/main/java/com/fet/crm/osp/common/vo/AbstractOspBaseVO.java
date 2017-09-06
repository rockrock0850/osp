/**
 * Copyright (c) 2015 Far Eastone Telecommunications Co., Ltd.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of 
 * Far Eastone Telecommunications Co., Ltd. ("Confidential Information"). 
 * 
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of license agreement you entered
 * into with Far Eastone Telecommunications Co., Ltd.
 */

package com.fet.crm.osp.common.vo;

import java.util.Date;

/**
 * 包覆物件 基礎共用類別
 * 
 * @author PaulChen
 */
public abstract class AbstractOspBaseVO {

	protected String createUser;
	protected Date createDate;
	protected Date updateDate;
	protected String updateUser;

	protected String createDateTxt;
	protected String updateDateTxt;

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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateDateTxt() {
		return createDateTxt;
	}

	public void setCreateDateTxt(String createDateTxt) {
		this.createDateTxt = createDateTxt;
	}

	public String getUpdateDateTxt() {
		return updateDateTxt;
	}

	public void setUpdateDateTxt(String updateDateTxt) {
		this.updateDateTxt = updateDateTxt;
	}

}
