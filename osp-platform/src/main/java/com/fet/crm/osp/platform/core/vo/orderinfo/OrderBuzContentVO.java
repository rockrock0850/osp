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

package com.fet.crm.osp.platform.core.vo.orderinfo;

import java.util.Map;

/**
 * [案件內容之子內容] 資料物件
 * 
 * @author Adam Yeh
 */
public class OrderBuzContentVO {
	
	private String contentId;
	private Map<String , Object> contentData;
	
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public Map<String, Object> getContentData() {
		return contentData;
	}
	public void setContentData(Map<String, Object> contentData) {
		this.contentData = contentData;
	}

}


