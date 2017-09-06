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

import java.util.Map;

/**
 * [特殊案件] 資料物件
 * 
 * @author Adam Yeh
 */
public class OperateManageVO {
	
	private String contentId;
	private String userId;
	private Map<String , Object> contentData;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<String, Object> getContentData() {
		return contentData;
	}

	public void setContentData(Map<String, Object> dataMap) {
		this.contentData = dataMap;
	}
	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

}


