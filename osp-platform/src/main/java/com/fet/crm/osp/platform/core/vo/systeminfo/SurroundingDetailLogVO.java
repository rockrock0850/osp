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

import java.util.Date;

/**
 * 
 * @author LawrenceLai
 */
public class SurroundingDetailLogVO {

	private String surroundingDId;
	private String surroundingMId;
	private String contentId;
	private String contentName;
	private String link;
	private String parameter;
	private Date createDate;
	private String createUser;

	public String getSurroundingDId() {
		return surroundingDId;
	}

	public void setSurroundingDId(String surroundingDId) {
		this.surroundingDId = surroundingDId;
	}

	public String getSurroundingMId() {
		return surroundingMId;
	}

	public void setSurroundingMId(String surroundingMId) {
		this.surroundingMId = surroundingMId;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
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

}
