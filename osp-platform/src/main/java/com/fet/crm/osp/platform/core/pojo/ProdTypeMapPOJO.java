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

import java.util.Date;

/**
 * [SysProdTypeMap] POJO物件
 * 
 * @author LawrenceLai
 */
public class ProdTypeMapPOJO {

	private String sourceSysId;
	private String sourceProdTypeId;
	private String sourceProdTypeName;
	private String description;
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;

	public String getSourceSysId() {
		return sourceSysId;
	}

	public void setSourceSysId(String sourceSysId) {
		this.sourceSysId = sourceSysId;
	}

	public String getSourceProdTypeId() {
		return sourceProdTypeId;
	}

	public void setSourceProdTypeId(String sourceProdTypeId) {
		this.sourceProdTypeId = sourceProdTypeId;
	}

	public String getSourceProdTypeName() {
		return sourceProdTypeName;
	}

	public void setSourceProdTypeName(String sourceProdTypeName) {
		this.sourceProdTypeName = sourceProdTypeName;
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

}
